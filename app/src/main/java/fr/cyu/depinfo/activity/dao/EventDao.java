package fr.cyu.depinfo.activity.dao;

import fr.cyu.depinfo.activity.model.Event;
import org.hibernate.SessionFactory;

public class EventDao extends AbstractDao<Event, Integer> {
    public EventDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Event read(Integer id) {
        return super.read(Event.class, id);
    }

    public void delete(Integer id) {
        super.delete(Event.class, id);
    }
}
