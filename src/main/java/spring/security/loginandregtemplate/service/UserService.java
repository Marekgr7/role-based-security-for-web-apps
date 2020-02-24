package spring.security.loginandregtemplate.service;

import spring.security.loginandregtemplate.model.Role;
import spring.security.loginandregtemplate.model.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {

    public List<User> getAllUsers();

    public void deleteUser(Long id);

    public void saveUser(User user,String roleName);

}
