package pl.sda.jp.miniblogwa16.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

import static pl.sda.jp.miniblogwa16.config.Roles.ADMIN;
import static pl.sda.jp.miniblogwa16.config.Roles.USER;

@Configuration
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired //baza danych wskazujemy wstrzykujemy
    private DataSource dataSource;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").hasAnyRole(ADMIN.getRoleName(), USER.getRoleName())
                .antMatchers("/admin/**")

                    //.hasAuthority("ROLE_ADMIN")
                    .hasRole("ADMIN")
                    .antMatchers("/post/**").hasAnyRole("USER","ADMIN")
                    //.hasRole("USER"
                    .anyRequest().permitAll()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .loginProcessingUrl("/loginBySpring")
                    .defaultSuccessUrl("/")
                    .failureUrl("/login?status=error")
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                ;
    }

    @Override   // to metodka do baz danych do przeładowania
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.inMemoryAuthentication()
               .withUser("admin@localhost.com")
               .password(passwordEncoder.encode("admin"))
               .roles(ADMIN.getRoleName());
       //jeśli enum to w enum ADMIN("ADMIN"), USER("USER") to tutaj trzeba - w ogóle z enumem jest problem lepiej nie robić w tym przypadku

        auth.jdbcAuthentication()
                .dataSource(dataSource)  //wskazuje baze danych
                .usersByUsernameQuery("select u.email, u.password, 1 from user u where u.email = ?")  //daj mi użytkowników po user query
                .authoritiesByUsernameQuery("select u.email, r.role_name " +
                        "from user u " +
                        "left join user_role ur on (u.id=ur.user_id) " +
                        "left join role r on (r.id=ur.roles_id) " +
                        "where u.email = ?")   //zwróci liste par user i role
                .passwordEncoder(passwordEncoder);  //sprawdza hasło industrial standard dostarcza spring security
    }
}
