package com.viper.rest.webservices.restfulwebservices.controller;

import com.viper.rest.webservices.restfulwebservices.entity.User;
import com.viper.rest.webservices.restfulwebservices.exception.ResourceNotFoundException;
import com.viper.rest.webservices.restfulwebservices.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDaoService userDaoService;

    @GetMapping("/users")
    public List<User> fetchUsers() {
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public User fetchUser(@PathVariable int id) {
        User user = userDaoService.findById(id);
        if(user==null)
            throw new ResourceNotFoundException(String.format("User with id=%s not found." , id));
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User savedUser = userDaoService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).body(savedUser);
    }
}
