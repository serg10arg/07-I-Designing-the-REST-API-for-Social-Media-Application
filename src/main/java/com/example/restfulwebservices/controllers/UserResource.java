package com.example.restfulwebservices.controllers;

import com.example.restfulwebservices.dao.UserDaoService;
import com.example.restfulwebservices.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserResource {

    private UserDaoService service;

    public UserResource(UserDaoService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public User retrieveUser(@PathVariable int id) {
        return service.findOne(id);
    }
}
