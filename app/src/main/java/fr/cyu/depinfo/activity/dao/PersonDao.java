package fr.cyu.depinfo.activity.dao;

import fr.cyu.depinfo.activity.model.Person;
import org.hibernate.SessionFactory;

public class PersonDao extends AbstractDao<Person, Integer> {
    public PersonDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Person read(Integer id) {
        return super.read(Person.class, id);
    }

    public void delete(Integer id) {
        super.delete(Person.class, id);
    }
}
