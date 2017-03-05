package com.yanka.intership.dao;

import com.yanka.intership.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {

    public List<User> getUsers() {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("from User");
//            Query query = session.createSQLQuery("select * from User");

            List queryList = query.list();
            tx.commit();
            if (queryList == null || queryList.isEmpty()) {
                return new ArrayList<>();
            }
            return queryList;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            session.close();
        }
    }

    public void deleteUserById(Integer id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateConnector.getInstance().getSession();

//            method 1
//            Query query = (Query) session.load("from User", id);

//            method 2
//            User user = session.get(User.class, id);
//            session.delete(user);

//            method 3
//            Query query = session.createSQLQuery("select * from User");

//            method 4
            tx = session.beginTransaction();
            tx.begin();
            Query query = session.createQuery("delete User where id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void addUser(User user) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            tx = session.beginTransaction();
            tx.begin();
            session.save(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void editUser(User user) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            tx = session.beginTransaction();
            tx.begin();
            session.update(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
