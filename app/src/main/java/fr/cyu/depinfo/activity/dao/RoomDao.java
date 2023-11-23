package fr.cyu.depinfo.activity.dao;

import fr.cyu.depinfo.activity.model.Room;
import org.hibernate.SessionFactory;

public class RoomDao extends AbstractDao<Room, Integer> {
    public RoomDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Room read(Integer id) {
        return super.read(Room.class, id);
    }

    public void delete(Integer id) {
        super.delete(Room.class, id);
    }
}
