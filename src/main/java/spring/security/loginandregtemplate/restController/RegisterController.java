package spring.security.loginandregtemplate.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import spring.security.loginandregtemplate.model.User;
import spring.security.loginandregtemplate.service.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("api/registration")
public class RegisterController {

    UserServiceImpl userServiceImpl;

    @Autowired
    public RegisterController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping
    @Secured("ROLE_ADMIN")
    public List<User> fetchAllUsers(){
        return userServiceImpl.fetchAllUsers();
    }

    @DeleteMapping
    @Secured("ROLE_ADMIN")
    public void deleteUser(@RequestParam int id){
        userServiceImpl.deleteUser((long) id);
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public void saveUser(@RequestBody User user){
        user.setEnabled(true);
        userServiceImpl.addUser(user);
    }
}
