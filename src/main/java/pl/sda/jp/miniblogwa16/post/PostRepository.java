package pl.sda.jp.miniblogwa16.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository <PostDTO, Long> {

    //boolean existsById (Long id);
}
