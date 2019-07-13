package pl.sda.jp.miniblogwa16.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserRegistrationService {

    private static final String ROLE_USER = "ROLE_USER";
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserRegistrationService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder; //przypisanie referencji argumentu pola do pola klasy
    }


    public void registerUser(RegisterForm dto) throws UserAlreadyExistsException {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new UserAlreadyExistsException("Duplicate email");
        }


//        Optional<Role> defaultRole = roleRepository.findByRoleName("ROLE_USER");
//        if(! defaultRole.isPresent()){
//            roleRepository.save(new Role (ROLE_USER);
//        }


        Role defaultRole = roleRepository.findByRoleName(ROLE_USER)
                .orElseGet(() -> roleRepository.save(new Role(ROLE_USER)));
        // tak nie robimy !!!  .orElse(roleRepository.save(new Role(ROLE_USER)));


        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
//user.setId(dto.hashCode());

//        Set<Role> roleSet = new HashSet<>();
//        roleSet.add(defaultRole);
//        user.setRoles(roleSet);
        user.addRole(defaultRole);

        userRepository.save(user);


    }

    @PostConstruct //często śe używa tego bean  to
        // po to aby każdym uruchomieniem aplikacji wykonało się coś
    void init(){
        System.out.println("User service after init!");
    }


}
