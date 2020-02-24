package spring.security.loginandregtemplate.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import spring.security.loginandregtemplate.service.MyUserDetailsService;

@Configuration
@Profile("debug")
public class SecurityCOnfigurationDebug extends WebSecurityConfigurerAdapter {

    BCryptPasswordEncoder bCryptPasswordEncoder;
    MyUserDetailsService myUserDetailsService;

    @Autowired
    public SecurityCOnfigurationDebug(BCryptPasswordEncoder bCryptPasswordEncoder, MyUserDetailsService myUserDetailsService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.myUserDetailsService = myUserDetailsService;
    }

    public SecurityCOnfigurationDebug(boolean disableDefaults, BCryptPasswordEncoder bCryptPasswordEncoder, MyUserDetailsService myUserDetailsService) {
        super(disableDefaults);
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .csrf().disable();
    }
}
