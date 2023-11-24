package fr.cyu.depinfo.activity.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {
    private static final Logger logger = LogManager.getLogger();

    private static final AtomicInteger nbThreads = new AtomicInteger(0);

    public static InetAddress SERVER_ADDRESS;
    public static int SERVER_PORT;

    private boolean portRetry = false;

    private ThreadGroup g;
    private NetworkThreadFactory ntFactory;
    private ExecutorService service;

    private SessionFactory sessionFactory;

    static {
        setDefaultConfig();
    }

    {
        g = new ThreadGroup("NetworkThreads");
        ntFactory = new NetworkThreadFactory(g);
        service = Executors.newCachedThreadPool(ntFactory);

        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void start() throws RuntimeException {
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT, 50, SERVER_ADDRESS)) {
            logger.info("Server listening on {}:{}", SERVER_ADDRESS, serverSocket.getLocalPort());

            while (true) {
                try {
                    this.service.submit(new NetworkProcess(serverSocket.accept(), sessionFactory));
                } catch (RuntimeException e) {
                    logger.error("An error occurred when trying to connect to a client.", e);
                }
            }
        } catch (BindException e) {
            if (!portRetry) {
                portRetry = true;
                logger.error("An error occurred when starting the server. Trying another port...", e);
                SERVER_PORT = 0;
                start();
            }
        } catch (IOException e) {
            throw new RuntimeException("An error occurred when trying to start the server.", e);
        }
    }

    public static void setDefaultConfig() throws RuntimeException {
        try {
            SERVER_ADDRESS = InetAddress.getByName("127.0.0.1");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        SERVER_PORT = 54321;
    }
}
