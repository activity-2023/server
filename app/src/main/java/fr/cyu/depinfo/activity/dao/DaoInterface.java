package fr.cyu.depinfo.activity.dao;

public interface DaoInterface<E, I> {
    void create(E entity);
    E read(Class<E> entityClass, I id);
    void update(E entity);
    void delete(Class<E> entityClass, I id);
}
