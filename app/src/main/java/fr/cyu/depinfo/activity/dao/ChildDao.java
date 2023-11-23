package fr.cyu.depinfo.activity.dao;

import fr.cyu.depinfo.activity.model.Child;
import org.hibernate.SessionFactory;

public class ChildDao extends AbstractDao<Child, Integer> {
    public ChildDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Child read(Integer id) {
        return super.read(Child.class, id);
    }

    public void delete(Integer id) {
        super.delete(Child.class, id);
    }
}
