package pl.sda.jp.miniblogwa16.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.jp.miniblogwa16.user.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    boolean existsByEmail(String email);

}
