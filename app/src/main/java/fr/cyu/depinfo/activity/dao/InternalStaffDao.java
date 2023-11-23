package fr.cyu.depinfo.activity.dao;

import fr.cyu.depinfo.activity.model.InternalStaff;
import org.hibernate.SessionFactory;

public class InternalStaffDao extends AbstractDao<InternalStaff, Integer> {
    public InternalStaffDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public InternalStaff read(Integer id) {
        return super.read(InternalStaff.class, id);
    }

    public void delete(Integer id) {
        super.delete(InternalStaff.class, id);
    }
}
