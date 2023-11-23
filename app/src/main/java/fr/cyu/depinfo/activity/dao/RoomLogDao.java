package fr.cyu.depinfo.activity.dao;

import fr.cyu.depinfo.activity.model.RoomLog;
import org.hibernate.SessionFactory;

public class RoomLogDao extends AbstractDao<RoomLog, Integer> {
    public RoomLogDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public RoomLog read(Integer id) {
        return super.read(RoomLog.class, id);
    }

    public void delete(Integer id) {
        super.delete(RoomLog.class, id);
    }
}
