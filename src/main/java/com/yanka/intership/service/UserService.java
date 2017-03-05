package com.yanka.intership.service;

import com.yanka.intership.dao.UserDao;
import com.yanka.intership.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> getUsers(User user) {
        return userDao.getUsers(user);
    }

    public void deleteUser(Integer userId) {
        userDao.deleteUserById(userId);
    }

    public void addUser(User user) {
        userDao.addUser(user);
    }

    public void editUser(User user) {
        userDao.editUser(user);
    }
}
