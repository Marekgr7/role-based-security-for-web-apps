package spring.security.loginandregtemplate.restController;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.AnnotatedResolverBuilder;
import io.leangen.graphql.metadata.strategy.query.PublicResolverBuilder;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.security.loginandregtemplate.model.User;
import spring.security.loginandregtemplate.service.UserServiceImpl;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private GraphQL graphQL;
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;

        GraphQLSchema schema = new GraphQLSchemaGenerator()
                .withResolverBuilders(
                        new AnnotatedResolverBuilder(),
                        new PublicResolverBuilder("spring"))
                .withOperationsFromSingleton(userService, UserServiceImpl.class)
                .withValueMapperFactory(new JacksonValueMapperFactory())
                .generate();
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    @GetMapping
    @ResponseBody
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/email")
    @ResponseBody
    public User getUserByEmail(@RequestParam String email) {
        return userService.getUserEmailById(email);
    }

    @GetMapping("/all/email")
    @ResponseBody
    public Map<String, Object> getAllUsersByEmail() {
        ExecutionResult result = graphQL.execute("{ users { email } }");
        return result.toSpecification();
    }


//    @GetMapping(path = "/all/{pathQuery}")
//    public Map<String, Object> getAll(@PathVariable String pathQuery) {
//        pathQuery = pathQuery.replaceAll("\\W", " ");
//
//        if(pathQuery.equals("password")) return null;
//
//        ExecutionResult result = graphQL.execute(" { users { " + pathQuery +" }} ");
//        return result.toSpecification();
//    }

    @GetMapping(path = "/user/{id}")
    public Map<String, Object> getDes(@PathVariable String id) {
        ExecutionResult result = graphQL.execute(
                "{ user(id: " + id + "){ id email username role { name } }}"
        );
        return result.toSpecification();
    }
}
