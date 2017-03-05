package com.yanka.intership.dao;

import com.yanka.intership.model.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration(locations = {})
@Transactional
public class UserDaoTest {

    private UserDao userDao;

    @Before
    public void setUp() {
        userDao = new UserDao();
    }

    @Test
    public void testGetUsers() throws Exception {
        List<User> users = userDao.getUsers();
        assertTrue(users.size() > 0);
    }

//    @Test
    public void testDeleteUserById() throws Exception {
        int indexToRemove = 0;

        List<User> users = userDao.getUsers();
        assertTrue(users.size() > indexToRemove);

        userDao.deleteUserById(users.get(indexToRemove).getId());
        List<User> usersAfterDelete = userDao.getUsers();

        assertEquals(users.size(), usersAfterDelete.size() + 1);
        for (int i = 0; i < usersAfterDelete.size(); i++) {
            assertNotSame(usersAfterDelete.get(i).getId(), users.get(indexToRemove).getId());
        }
    }
}
