package pl.sda.jp.miniblogwa16.post;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class PostForm {

    @NotBlank(message = "Post musi mieć tytuł")
    private String title;
    @NotBlank(message = "Post nie może być pusty")
    private String post;
    private LocalDateTime dataAdd;

}
