package spring.security.loginandregtemplate.service;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import spring.security.loginandregtemplate.model.Role;
import spring.security.loginandregtemplate.model.User;
import spring.security.loginandregtemplate.repository.RoleRepository;
import spring.security.loginandregtemplate.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GraphQLQuery(name = "users", description = "Fetch all Users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GraphQLQuery(name = "user")
    public User getUser(@GraphQLArgument(name = "id") Long id) {
        return userRepository.getOne(id);
    }

    @GraphQLQuery(name = "userEmail")
    public User getUserEmailById(@GraphQLArgument(name = "email") String email) {
        return userRepository.getUserByEmail(email);
    }

    @GraphQLMutation
    public void deleteUser(@GraphQLArgument(name = "id") Long id){
        try {
            userRepository.deleteById(id);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void saveUser(User user, String roleName){

        try{
            String roleToSet;

            if (roleName.equals("Admin")) {
                roleToSet = "ROLE_ADMIN";
            } else if (roleName.equals("Employee")) {
                roleToSet = "ROLE_EMPLOYEE";
            } else {
                roleToSet = "ROLE_CUSTOMER";
            }

            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            System.out.println("Role to set to " + roleToSet);
            user.getRoles().add(roleRepository.findByName(roleToSet));
            userRepository.save(user);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
