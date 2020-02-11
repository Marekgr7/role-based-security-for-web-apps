package spring.security.loginandregtemplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.security.loginandregtemplate.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
