package fr.cyu.depinfo.activity.server;

import java.util.concurrent.ThreadFactory;

public class NetworkThreadFactory implements ThreadFactory {
    private ThreadGroup g;

    public NetworkThreadFactory(ThreadGroup g) {
        this.g = g;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new NetworkThread(g, r);
    }
}
