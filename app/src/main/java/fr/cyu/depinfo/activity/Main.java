package fr.cyu.depinfo.activity;

import fr.cyu.depinfo.activity.cli.ServerStart;
import fr.cyu.depinfo.activity.model.User;
import fr.cyu.depinfo.activity.server.HibernateUtil;
import fr.cyu.depinfo.activity.server.Server;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.tomlj.Toml;
import org.tomlj.TomlParseResult;
import picocli.CommandLine;

import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String... args) {
        int exitCode = new CommandLine(new ServerStart()).execute(args);
        System.exit(exitCode);
    }
}
