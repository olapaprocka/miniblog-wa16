package pl.sda.jp.miniblogwa16.exampletes;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.jp.miniblogwa16.user.RegisterForm;
import pl.sda.jp.miniblogwa16.user.UserRegistrationService;
import pl.sda.jp.miniblogwa16.user.UserRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FulContextTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Test
    public void shouldCreaterUser() throws EmailAlreadyExistsException {
        RegisterForm registerForm = new RegisterForm();
        registerForm.setEmail("kowalski@gmail.com");
    }

}
