package fr.cyu.depinfo.activity.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {
    private static final Logger logger = LogManager.getLogger();

    private static AtomicInteger nbThreads = new AtomicInteger(0);

    private ThreadGroup g;
    private NetworkThreadFactory ntFactory;
    private ExecutorService service;

    private int port = 54321;
    private InetAddress addr = InetAddress.getByName("127.0.0.1");

    private SessionFactory sessionFactory;

    {
        g = new ThreadGroup("NetworkThreads");
        ntFactory = new NetworkThreadFactory(g);
        service = Executors.newCachedThreadPool(ntFactory);

        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public Server() throws UnknownHostException {}

    public Server(String addr, int port) throws UnknownHostException {
        this.addr = InetAddress.getByName(addr);
        this.port = port;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(this.port, 50, this.addr)) {
            logger.info("Server listening on {}:{}", this.addr.getHostAddress(), this.port);

            while (true) {
                this.service.submit(new NetworkProcess(serverSocket.accept(), sessionFactory));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
