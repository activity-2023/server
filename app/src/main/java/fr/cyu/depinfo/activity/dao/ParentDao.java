package fr.cyu.depinfo.activity.dao;

import fr.cyu.depinfo.activity.model.Parent;
import org.hibernate.SessionFactory;

public class ParentDao extends AbstractDao<Parent, Integer> {
    public ParentDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Parent read(Integer id) {
        return super.read(Parent.class, id);
    }

    public void delete(Integer id) {
        super.delete(Parent.class, id);
    }
}
