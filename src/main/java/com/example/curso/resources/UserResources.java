package com.example.curso.resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.curso.entities.User;

@RestController
@RequestMapping(value = "/users")
public class UserResources {

    @GetMapping
    public ResponseEntity<User> listAllUsers() {
        User u = new User.UserBuilder().withId(1L).withName("Nata").withEmail("<noren777@gmail.com>").withPassword("4124142vasco").withPhone("999999999").build();
        return ResponseEntity.ok().body(u);
    }
}
