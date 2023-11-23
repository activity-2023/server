package fr.cyu.depinfo.activity.dao;

import org.hibernate.SessionFactory;

import java.util.concurrent.atomic.AtomicReference;

public abstract class AbstractDao<E, I> implements DaoInterface<E, I> {
    private final SessionFactory sessionFactory;

    public AbstractDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(E entity) {
        sessionFactory.inTransaction(session -> session.persist(entity));
    }

    @Override
    public E read(Class<E> entityClass, I id) {
        AtomicReference<E> entity = new AtomicReference<>();
        sessionFactory.inSession(session -> entity.set(session.find(entityClass, id)));
        return entity.get();
    }

    @Override
    public void update(E entity) {
        sessionFactory.inTransaction(session -> session.merge(entity));
    }

    @Override
    public void delete(Class<E> entityClass, I id) {
        sessionFactory.inTransaction(session -> session.remove(session.find(entityClass, id)));
    }
}
