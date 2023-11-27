package fr.cyu.depinfo.activity.server;

import fr.cyu.depinfo.activity.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static final Logger logger = LogManager.getLogger();

    private static final SessionFactory SESSION_FACTORY;

    static {
        try {
            SESSION_FACTORY = new MetadataSources(
                    new StandardServiceRegistryBuilder(
                            new BootstrapServiceRegistryBuilder()
                                    .build()
                    )
                            .build())
                    .addAnnotatedClass(Person.class)
                    .addAnnotatedClass(UserAccount.class)
                    .addAnnotatedClass(Parent.class)
                    .addAnnotatedClass(Child.class)
                    .addAnnotatedClass(Staff.class)
                    .addAnnotatedClass(ExternalStaff.class)
                    .addAnnotatedClass(InternalError.class)
                    .addAnnotatedClass(Activity.class)
                    .addAnnotatedClass(Building.class)
                    .addAnnotatedClass(Room.class)
                    .addAnnotatedClass(Event.class)
                    .addAnnotatedClass(StaffPresence.class)
                    .addAnnotatedClass(BuildingLog.class)
                    .addAnnotatedClass(RoomLog.class)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Throwable e) {
            logger.error("An error occurred when initializing the SessionFactory", e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}
