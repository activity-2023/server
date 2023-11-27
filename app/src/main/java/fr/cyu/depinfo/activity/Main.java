package fr.cyu.depinfo.activity;

import fr.cyu.depinfo.activity.cli.ServerStart;
import picocli.CommandLine;

public class Main {
    public static void main(String... args) {
        int exitCode = new CommandLine(new ServerStart()).execute(args);
        System.exit(exitCode);
    }
}
