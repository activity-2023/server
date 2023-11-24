package fr.cyu.depinfo.activity.cli;

import fr.cyu.depinfo.activity.server.Server;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.tomlj.Toml;
import org.tomlj.TomlParseResult;
import org.tomlj.TomlTable;
import picocli.CommandLine;

import java.net.InetAddress;
import java.nio.file.Path;
import java.nio.file.Paths;

@CommandLine.Command(name = "ANS (Activity Net Server)", description = "Start the network server.", version = "1.0.0", mixinStandardHelpOptions = true)
public class ServerStart implements Runnable {
    private static final Logger logger = LogManager.getLogger();

    @CommandLine.ArgGroup()
    ConfigChoice configChoice;

    @Override
    public void run() {
        Server server = new Server();

        try {
            if (configChoice != null) {
                InetAddress address = null;
                Integer port = null;

                if (configChoice.cliConfig == null) {
                    logger.info("Loading configuration from {}", configChoice.configSource.toRealPath());
                    TomlParseResult globalConfig = Toml.parse(configChoice.configSource);
                    TomlTable networkConfig = globalConfig.getTable("network");

                    try {
                        address = InetAddress.getByName(networkConfig.getString("address"));
                    } catch (Exception ignored) {
                    }

                    try {
                        port = networkConfig.getLong("port").intValue();
                    } catch (Exception ignored) {
                    }
                } else {
                    address = configChoice.cliConfig.serverAddress;
                    port = configChoice.cliConfig.serverPort;
                }

                if (address != null) {
                    Server.SERVER_ADDRESS = address;
                } else {
                    logger.info("The address was not set, server will start with the default.");
                }

                if (port != null) {
                    if (port > 1024 && port < 65535) {
                        Server.SERVER_PORT = port;
                    } else {
                        logger.error("Incorrect port number, server will start with the default.");
                    }
                } else {
                    logger.info("The port was not set, server will start with the default.");
                }

                server.start();
            } else {
                logger.info("Start the server with default configuration.");
                server.start();
            }
        } catch (Exception e) {
            logger.error("An error occurred when trying to get the custom configuration. Starting the server with default values.", e);
            Server.setDefaultConfig();
            server.start();
        }
    }

    static class ConfigChoice {
        @CommandLine.ArgGroup(exclusive = false)
        CliConfig cliConfig;

        @CommandLine.Option(names = "--config")
        Path configSource;
    }

    static class CliConfig {
        @CommandLine.Option(names = "--host", paramLabel = "SERVER_ADDRESS")
        InetAddress serverAddress;

        @CommandLine.Option(names = {"-p", "--port"}, paramLabel = "SERVER_PORT")
        Integer serverPort;
    }
}
