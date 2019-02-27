package org.kd.selfserivce;

import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.ServerAcl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class HsqlDataSource {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(HsqlDataSource.class);
        applicationContext.start();
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server hsqlServer() throws IOException, ServerAcl.AclFormatException {
        Server server = new Server();
        server.setProperties(hsqlProperties());
        return server;
    }

    @Bean
    public HsqlProperties hsqlProperties() {
        HsqlProperties hsqlProperties = new HsqlProperties();

        Properties properties = new Properties();
        properties.setProperty("server.database.0", "res:/hsql/static-data");
        properties.setProperty("server.dbname.0", "static-data-db");
        properties.setProperty("server.prot", "9760");
        hsqlProperties.addProperties(properties);

        return hsqlProperties;
    }

}
