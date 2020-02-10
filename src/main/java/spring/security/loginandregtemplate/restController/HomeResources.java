package spring.security.loginandregtemplate.restController;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResources {

    @GetMapping("/")
    public String hello(){
        return "hello";
    }

    @GetMapping("/admin")
    public String helloAdmin(){
        return "admin";
    }

    @GetMapping("/user")
    public String helloUser(){
        return "user";
    }

    @GetMapping("/role")
    public void showRoles(){
                for(GrantedAuthority authority : SecurityContextHolder.getContext().getAuthentication().getAuthorities()){
            String userRole = authority.getAuthority();
            System.out.println("Role to " + userRole);
        }
    }
}
