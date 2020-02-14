package spring.security.loginandregtemplate.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import spring.security.loginandregtemplate.service.MyUserDetailsService;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    BCryptPasswordEncoder bCryptPasswordEncoder;

    MyUserDetailsService myUserDetailsService;

    @Autowired
    public SecurityConfiguration(BCryptPasswordEncoder bCryptPasswordEncoder, MyUserDetailsService myUserDetailsService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.myUserDetailsService = myUserDetailsService;
    }

    public SecurityConfiguration(boolean disableDefaults, BCryptPasswordEncoder bCryptPasswordEncoder, MyUserDetailsService myUserDetailsService) {
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
                .antMatchers("/css/signin.css").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login-error")
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest().permitAll()
//                .and()
//                .csrf().disable();
//    }
}
