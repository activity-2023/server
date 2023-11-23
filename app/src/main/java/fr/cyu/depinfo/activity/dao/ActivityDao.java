package fr.cyu.depinfo.activity.dao;

import fr.cyu.depinfo.activity.model.Activity;
import org.hibernate.SessionFactory;

public class ActivityDao extends AbstractDao<Activity, Integer> {
    public ActivityDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Activity read(Integer id) {
        return super.read(Activity.class, id);
    }

    public void delete(Integer id) {
        super.delete(Activity.class, id);
    }
}
