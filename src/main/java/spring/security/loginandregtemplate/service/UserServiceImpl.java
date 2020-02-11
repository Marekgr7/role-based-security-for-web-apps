package spring.security.loginandregtemplate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.security.loginandregtemplate.model.Role;
import spring.security.loginandregtemplate.model.User;
import spring.security.loginandregtemplate.repository.RoleRepository;
import spring.security.loginandregtemplate.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public List<User> fetchAllUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public void addUser(User user){
        Role role = new Role("ROLE_USER");
        List<Role> roleList = new ArrayList(){};
        roleList.add(role);
        user.setRoles(roleList);

        userRepository.save(user);
    }
}
