package pl.sda.jp.miniblogwa16;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.jp.miniblogwa16.post.PostForm;
import pl.sda.jp.miniblogwa16.post.PostRepository;
import pl.sda.jp.miniblogwa16.post.PostService;


@Controller
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {

        this.postService = postService;
    }

    @Autowired
    PostRepository postRepository;

    @GetMapping("/post/add")
    //adnotacja PathVarialbe pomaga sparsować wyciąga ze ścieżki czyli koniecznie musi byc wskazane w nawiasach{postId}

    public String showSinglePost(Model model) {
        model.addAttribute("postForm", new PostForm());
return "postForm";
        //return "post/singlePost";
    }

    @PostMapping("/post/add")
    public String addPost(PostForm postForm, Model model) {
//        PostDTO postDTO = new PostDTO(Long.valueOf(postId), "Kolejny post");
//        model.addAttribute("kolejny post", postDTO);
        return "postForm";

    }

}
