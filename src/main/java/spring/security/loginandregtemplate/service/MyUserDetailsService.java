package spring.security.loginandregtemplate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.security.loginandregtemplate.model.User;
import spring.security.loginandregtemplate.model.UserDetailsImpl;
import spring.security.loginandregtemplate.repository.UserRepository;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findUserByUsername(username);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found " + username));
        spring.security.loginandregtemplate.model.UserDetailsImpl userDetails = new spring.security.loginandregtemplate.model.UserDetailsImpl(user.get());

        return userDetails;
    }
}
