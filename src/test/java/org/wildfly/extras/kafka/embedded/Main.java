package org.wildfly.extras.kafka.embedded;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author <a href="mailto:kabir.khan@jboss.com">Kabir Khan</a>
 */
public class Main {
    public static void main(String[] args) throws Exception {

        Path path = Paths.get(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        while (!path.getFileName().toString().equals("target")) {
            path = path.getParent();
        }
        Path target = path;
        Path kafkaDir = Files.createTempDirectory(target, "kafka");

        Files.createDirectories(kafkaDir);

        EmbeddedKafkaBroker broker = new EmbeddedKafkaBroker(kafkaDir, 1, true)
                .zkPort(2181)
                .kafkaPorts(9092);

        try {
            broker.start();
            System.out.println("Broker should be started");
            System.in.read();
        } finally {
            System.out.println("Stopping broker");
            broker.stop();
            System.out.println("Stopped broker");
        }
    }
}
