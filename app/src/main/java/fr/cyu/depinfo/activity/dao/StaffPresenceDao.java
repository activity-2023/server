package fr.cyu.depinfo.activity.dao;

import fr.cyu.depinfo.activity.model.StaffPresence;
import org.hibernate.SessionFactory;

public class StaffPresenceDao extends AbstractDao<StaffPresence, Integer> {
    public StaffPresenceDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public StaffPresence read(Integer id) {
        return super.read(StaffPresence.class, id);
    }

    public void delete(Integer id) {
        super.delete(StaffPresence.class, id);
    }
}
