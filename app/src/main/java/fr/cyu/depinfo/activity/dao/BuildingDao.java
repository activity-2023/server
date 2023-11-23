package fr.cyu.depinfo.activity.dao;

import fr.cyu.depinfo.activity.model.Building;
import org.hibernate.SessionFactory;

public class BuildingDao extends AbstractDao<Building, Integer> {
    public BuildingDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Building read(Integer id) {
        return super.read(Building.class, id);
    }

    public void delete(Integer id) {
        super.delete(Building.class, id);
    }
}
