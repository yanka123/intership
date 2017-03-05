package com.yanka.intership.service;

import com.yanka.intership.dao.UserDao;
import com.yanka.intership.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private List<User> users;

    @Autowired
    private UserDao userDao;

    public UserService() {
        users = new ArrayList<>();
        users.add(new User(11078632, "Yanka", 25, true, new Timestamp(2017, 2, 2, 20, 23, 0, 0)));
        users.add(new User(30198622, "Zbigniew", 31, false, new Timestamp(2014, 11, 13, 15, 0, 0, 0)));
    }

    public List<User> getUsers() {
//        return users;
        return userDao.getUsers();
    }

    public void deleteUser(Integer userId) {
//        List<User> newUsers = new ArrayList<>();
//        for (User user : users) {
//            if (!user.getId().equals(userId)) {
//                newUsers.add(user);
//            }
//        }
//        users = newUsers;
        userDao.deleteUserById(userId);
    }

    public void addUser(User user) {
        userDao.addUser(user);
    }

    public void editUser(User user) {
        userDao.editUser(user);
    }
}
