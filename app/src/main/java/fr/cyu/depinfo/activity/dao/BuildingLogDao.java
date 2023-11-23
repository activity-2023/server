package fr.cyu.depinfo.activity.dao;

import fr.cyu.depinfo.activity.model.BuildingLog;
import org.hibernate.SessionFactory;

public class BuildingLogDao extends AbstractDao<BuildingLog, Integer> {
    public BuildingLogDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public BuildingLog read(Integer id) {
        return super.read(BuildingLog.class, id);
    }

    public void delete(Integer id) {
        super.delete(BuildingLog.class, id);
    }
}
