package pl.sda.jp.miniblogwa16.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import pl.sda.jp.miniblogwa16.config.Roles;

import java.lang.reflect.Array;
import java.util.Arrays;

import static pl.sda.jp.miniblogwa16.config.Roles.ADMIN;
import static pl.sda.jp.miniblogwa16.config.Roles.USER;

@Service
//@Scope("session")
@SessionScope
public class PermissionService {
    public boolean isAdmin(){
       return isUserInRole(ADMIN);
    }

    public boolean isUser(){
        return isUserInRole(USER);
    }

    public boolean isUserInRole(Roles roles){
        Authentication userAuthentication =
                SecurityContextHolder.getContext().getAuthentication();
        if (userAuthentication == null){
            return  false;
        }
        return userAuthentication
                .getAuthorities()
                .stream()
                .anyMatch(a->((GrantedAuthority) a).getAuthority().equalsIgnoreCase(roles.getAuthorityName()));
    }
public boolean isUserInAnyRole(String ... role){
    return Arrays.stream(role)
            .map(roleAsString->Roles.valueOf(roleAsString.toUpperCase()))
            .anyMatch(this::isUserInRole);

}
    public String getCurrentUserName(){
        Authentication userAuthentication =
                SecurityContextHolder.getContext().getAuthentication();
        if (userAuthentication == null){
            return null;
        } return userAuthentication.getName();

    }
}
