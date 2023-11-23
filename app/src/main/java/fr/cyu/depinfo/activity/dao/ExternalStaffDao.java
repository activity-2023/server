package fr.cyu.depinfo.activity.dao;

import fr.cyu.depinfo.activity.model.ExternalStaff;
import org.hibernate.SessionFactory;

public class ExternalStaffDao extends AbstractDao<ExternalStaff, Integer> {
    public ExternalStaffDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public ExternalStaff read(Integer id) {
        return super.read(ExternalStaff.class, id);
    }

    public void delete(Integer id) {
        super.delete(ExternalStaff.class, id);
    }
}
