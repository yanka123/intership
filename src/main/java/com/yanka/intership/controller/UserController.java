package com.yanka.intership.controller;

import com.yanka.intership.model.User;
import com.yanka.intership.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getUsers(User user){
        return userService.getUsers(user);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteUser(@RequestParam Integer userId){
        userService.deleteUser(userId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void editUser(@RequestBody User user){
        userService.editUser(user);
    }
}
