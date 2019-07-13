package pl.sda.jp.miniblogwa16.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString (exclude =  {"password"})  //w taki sposób nie dajemy do toString pola password dlatego trzeba podać exclude dla password
public class RegisterForm {

    @NotNull(message = "Pole imię musi byc wypełnione")
    private String firstName;
    @NotBlank(message = "Nazwisko nie może byc puste")  //nieczysty string czli przynajmniej jeden niebiały znak
    private String lastName;
    @Pattern(regexp = "[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-z]{2,3}", message = "Popraw email")
    private String email;
    @Size(min = 5, max = 10)
    private String password;

}
