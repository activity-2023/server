package fr.cyu.depinfo.activity;

import fr.cyu.depinfo.activity.model.User;
import fr.cyu.depinfo.activity.server.HibernateUtil;
import fr.cyu.depinfo.activity.server.Server;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;

import java.net.UnknownHostException;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String... args) throws UnknownHostException {
        Server server = new Server("127.0.0.1", 54321);
        server.start();
    }
}
