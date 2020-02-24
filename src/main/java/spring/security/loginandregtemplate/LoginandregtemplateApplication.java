package spring.security.loginandregtemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import spring.security.loginandregtemplate.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class LoginandregtemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginandregtemplateApplication.class, args);
    }

}
