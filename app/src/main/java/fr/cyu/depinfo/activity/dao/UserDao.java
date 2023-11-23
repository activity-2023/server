package fr.cyu.depinfo.activity.dao;

import fr.cyu.depinfo.activity.model.User;
import org.hibernate.SessionFactory;

public class UserDao extends AbstractDao<User, Integer> {
    public UserDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public User read(Integer id) {
        return super.read(User.class, id);
    }

    public void delete(Integer id) {
        super.delete(User.class, id);
    }
}
