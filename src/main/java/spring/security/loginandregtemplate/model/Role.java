package spring.security.loginandregtemplate.model;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Role(){}

    public Role(String name){
        this.name = name;
    }

    @GraphQLQuery(name = "id", description = "Role ID number")
    public Long getId() {
        return id;
    }

    @GraphQLQuery(name = "name", description = "Role name")
    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
