package spring.security.loginandregtemplate.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import spring.security.loginandregtemplate.model.Role;
import spring.security.loginandregtemplate.model.User;
import spring.security.loginandregtemplate.repository.RoleRepository;
import spring.security.loginandregtemplate.service.UserServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/registration")
public class RegisterController {

    UserServiceImpl userServiceImpl;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    public RegisterController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    //Test controller - to be deleted
//    @GetMapping("/role")
//    public Optional<Role> getRoleById(@RequestParam Long id){
//        return roleRepository.findById(id);
//    }
//
//    @GetMapping("/role-name")
//    public Role getRoleByName(@RequestParam String name){
//        return roleRepository.findByName(name);
//    }

    @GetMapping
    @Secured("ROLE_ADMIN")
    public List<User> fetchAllUsers(){
        return userServiceImpl.getAllUsers();
    }

    @DeleteMapping
    @Secured("ROLE_ADMIN")
    public void deleteUser(@RequestParam int id){
        userServiceImpl.deleteUser((long) id);
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public void saveUser(@RequestBody User user, @RequestParam String roleName){
        user.setEnabled(true);
        userServiceImpl.saveUser(user,roleName);
    }
}
