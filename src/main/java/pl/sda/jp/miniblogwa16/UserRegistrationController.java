package pl.sda.jp.miniblogwa16;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.sda.jp.miniblogwa16.user.RegisterForm;
import pl.sda.jp.miniblogwa16.user.UserAlreadyExistsException;
import pl.sda.jp.miniblogwa16.user.UserRegistrationService;
import pl.sda.jp.miniblogwa16.user.UserRepository;

import javax.validation.Valid;

@Controller
public class UserRegistrationController {

    private UserRegistrationService userRegistrationService;

    @Autowired
    public UserRegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @Autowired
    UserRepository userRepository;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerForm", new RegisterForm());

        return "registerForm";
    }

    @PostMapping("/register")
    public String handleRegisterForm(
            // @RequestParam (name = "firstName") String Imie
            // @RequestParam String firstName;

            @ModelAttribute @Valid RegisterForm registerForm,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes  //dzieki temu bedziemy stanie przekazac komunikat np. dzieki za rejestracj
    ) {


        if (bindingResult.hasErrors()) {
            //model.addAttribute("registerForm", registerForm);
            return "registerForm";
        }
        try {
            userRegistrationService.registerUser(registerForm);
        } catch (UserAlreadyExistsException e) {
            bindingResult.rejectValue("email", "email-duplicate", "duplikat maila");
            return "registerForm";
        }
        //System.out.println(firstName);
        // System.out.println(registerForm);

        // userRegistrationService.registerUser(registerForm);
       redirectAttributes.addFlashAttribute("msg", "Dzięki za rejestracje");
        return "redirect:/";
    }

    @GetMapping("/login")
    public String showLoginForm(){

        return "logForm";
    }


}
