package spring.security.loginandregtemplate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.leangen.graphql.annotations.GraphQLIgnore;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Data;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.*;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    @Email
    private String email;

    @JsonIgnore
    @GraphQLIgnore
    private String password;

    private boolean isEnabled;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    public User(){}

    public User(String username, String email, String password) {
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

    @GraphQLQuery(name = "id", description = "User ID number")
    public Long getId() {
        return id;
    }

    @GraphQLQuery(name = "username", description = "Username")
    public String getUserName() {
        return username;
    }

    @GraphQLQuery(name = "email", description = "User email")
    public String getEmail() {
        return email;
    }

    @GraphQLQuery(name = "enabled", description = "User enabled")
    public boolean getEnabled() {
        return isEnabled;
    }

    @GraphQLQuery(name = "role", description = "User roles")
    public Set<Role> getRoles() {
        return roles;
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
