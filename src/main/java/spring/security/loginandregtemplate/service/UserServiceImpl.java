package spring.security.loginandregtemplate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import spring.security.loginandregtemplate.model.Role;
import spring.security.loginandregtemplate.model.User;
import spring.security.loginandregtemplate.repository.RoleRepository;
import spring.security.loginandregtemplate.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    UserRepository userRepository;

    RoleRepository roleRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public List<User> fetchAllUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(Long id){

        try {
            userRepository.deleteById(id);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addUser(User user){
//        Role role = roleRepository.getOne(2L);
//        List<Role> roleList = new ArrayList(){};
//        roleList.add(role);
//        user.setRoles(roleList);
        System.out.println(user.toString());
        try{
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
