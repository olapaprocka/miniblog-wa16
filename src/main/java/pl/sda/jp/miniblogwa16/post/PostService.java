package pl.sda.jp.miniblogwa16.post;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private PostRepository postRepository;


    @Autowired
    public PostService(PostRepository postRepository) {

        this.postRepository = postRepository;
    }

    public void registerPost(PostForm postForm) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(postDTO.getId());
        postDTO.setTitle(postDTO.getTitle());
        postDTO.setPost(postDTO.getPost());
        postDTO.setDataAdd(postDTO.getDataAdd());


        postRepository.save(postDTO);
    }
}