package pl.sda.jp.miniblogwa16.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.jp.miniblogwa16.user.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository <Role, Integer>  {

Optional<Role> findByRoleName(String roleName);
}
