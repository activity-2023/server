package fr.cyu.depinfo.activity.server;

public class NetworkThread extends Thread {
    public NetworkThread(ThreadGroup g, Runnable task) {
        super(task);
        this.setName(this.genThreadName(g));
    }

    private String genThreadName(ThreadGroup g) {
        return g.getName() + "-" + g.activeCount() + 1;
    }
}
