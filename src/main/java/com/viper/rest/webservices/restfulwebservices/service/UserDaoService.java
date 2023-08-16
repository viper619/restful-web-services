package com.viper.rest.webservices.restfulwebservices.service;


import com.viper.rest.webservices.restfulwebservices.entity.User;
import com.viper.rest.webservices.restfulwebservices.exception.ResourceNotFound;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    // Dummy users
    static {
        users.add(new User(1, "Vishal", LocalDate.now().minusYears(28)));
        users.add(new User(2, "Rinkal", LocalDate.now().minusYears(27)));
        users.add(new User(3, "Abhi", LocalDate.now().minusYears(25)));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        users.add(user);
        return user;
    }

    public User findById(int id) throws ResourceNotFound {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElseThrow(ResourceNotFound::new);
    }
}
