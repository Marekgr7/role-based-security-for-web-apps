package spring.security.loginandregtemplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.security.loginandregtemplate.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

//    @Query(value = "SELECT * FROM loginandreg.user where username = :USERNAME", nativeQuery = true)
    public Optional<User> findUserByUsername(String username);
}
