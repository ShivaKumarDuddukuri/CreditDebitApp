package com.shiva.config.jdbc;

import com.googlecode.flyway.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.LinkedList;
import java.util.List;

@Configuration
@EnableTransactionManagement
public class JDBCDaoSpringConfig {

    private static final String DB_SCRIPTS_LOCATION = "db_scripts/mysql//";
    private static final String SQL_MIGRATION_PREFIX = "transactions_v";
    private static final String SCHEMA = "transactions";

    @Value("${com.shiva.transaction.driverClassName}")
    private String driverClassName;

    @Value("${com.shiva.transaction.url}")
    private String url;

    @Value("${com.shiva.transaction.userName}")
    private String userName;

    @Value("${com.shiva.transaction.password}")
    private String password;


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }

    @DependsOn("dbMigrator")
    @Bean
    public DataSourceTransactionManager gatewayTransactionManager() throws IllegalArgumentException, NamingException {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }

    @Bean
    public Flyway dbMigrator() throws IllegalArgumentException, NamingException {

        Flyway flyway = new Flyway();
        flyway.setInitOnMigrate(true);
        flyway.setPlaceholderPrefix("#{");
        flyway.setPlaceholderSuffix("}");
        flyway.setDataSource(dataSource());
        flyway.setLocations(DB_SCRIPTS_LOCATION);
        flyway.setSqlMigrationPrefix(SQL_MIGRATION_PREFIX);
        List<String> schemaNamesList = new LinkedList<String>();
        schemaNamesList.add(SCHEMA);
        flyway.setSchemas(listToStringArray(schemaNamesList));
        return flyway;
    }

    private String[] listToStringArray(List<String> listObjects) {
        if (listObjects != null && !listObjects.isEmpty()) {
            String[] strArry = new String[listObjects.size()];
            int i = 0;
            for (String s : listObjects) {
                strArry[i++] = s;
            }
            return strArry;
        }
        return null;
    }

}
