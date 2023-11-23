package fr.cyu.depinfo.activity.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

public class NetworkThread extends Thread {
    private static final Logger logger = LogManager.getLogger();

    public NetworkThread(ThreadGroup g, Runnable task) {
        super(task);
        this.setName(this.genThreadName(g));
    }

    private String genThreadName(ThreadGroup g) {
        AtomicInteger nbThreads = null;
        try {
            Field f = Server.class.getDeclaredField("nbThreads");
            f.setAccessible(true);
            nbThreads = (AtomicInteger) f.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            logger.error("An error occurred when trying to access the field nbThreads.", e);
        }

        int nbt = 0;

        if (nbThreads != null) {
            nbt = nbThreads.get();
        }

        return g.getName() + "-" + nbt;
    }
}
