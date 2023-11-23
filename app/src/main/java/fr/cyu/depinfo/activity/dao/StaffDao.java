package fr.cyu.depinfo.activity.dao;

import fr.cyu.depinfo.activity.model.Staff;
import org.hibernate.SessionFactory;

public class StaffDao extends AbstractDao<Staff, Integer> {
    public StaffDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Staff read(Integer id) {
        return super.read(Staff.class, id);
    }

    public void delete(Integer id) {
        super.delete(Staff.class, id);
    }
}
