package spring.security.loginandregtemplate.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String email;

    private String password;

    private boolean isEnabled;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    public User(){}

    public User(long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String email, String password, boolean isEnabled, Set<Role> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isEnabled = isEnabled;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isEnabled=" + isEnabled +
                ", roles=" + roles +
                '}';
    }

    public void addRole(Role role){
        roles.add(role);
    }
}
