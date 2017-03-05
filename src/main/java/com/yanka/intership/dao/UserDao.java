package com.yanka.intership.dao;

import com.yanka.intership.model.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {

    public List<User> getUsers(User user) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(User.class, "u");
            if (user != null) {
                if (user.getName() != null) {
                    criteria.add(Restrictions.ilike("u.name", "%" + user.getName() + "%"));
                }
                if (user.getAge() != null) {
                    criteria.add(Restrictions.eq("u.age", user.getAge()));
                }
                if (user.getIsAdmin() != null) {
                    criteria.add(Restrictions.eq("u.isAdmin", user.getIsAdmin()));
                }
                if (user.getCreatedDate() != null) {
                    criteria.add(Restrictions.ge("u.createdDate", user.getCreatedDate()));
                }
            }

            List queryList = criteria.list();
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
