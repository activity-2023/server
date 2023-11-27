package fr.cyu.depinfo.activity.dao;

import fr.cyu.depinfo.activity.model.UserAccount;
import org.hibernate.SessionFactory;

public class UserAccountDao extends AbstractDao<UserAccount, Integer> {
    public UserAccountDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public UserAccount read(Integer id) {
        return super.read(UserAccount.class, id);
    }

    public void delete(Integer id) {
        super.delete(UserAccount.class, id);
    }
}
