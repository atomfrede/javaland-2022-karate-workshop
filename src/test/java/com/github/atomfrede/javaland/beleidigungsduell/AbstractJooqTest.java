package com.github.atomfrede.javaland.beleidigungsduell;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.testcontainers.containers.PostgreSQLContainer;

public abstract class AbstractJooqTest {

    static final PostgreSQLContainer POSTGRES = new PostgreSQLContainer("postgres:14")
            .withDatabaseName("beleidigungsduell")
            .withUsername("beleidigungsduell")
            .withPassword("beleidigungsduell");

    protected static DSLContext jooq;

    static {
        POSTGRES.start();

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(POSTGRES.getJdbcUrl());
        hikariConfig.setUsername("beleidigungsduell");
        hikariConfig.setPassword("beleidigungsduell");
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.migrate();

        jooq = DSL.using(dataSource, SQLDialect.POSTGRES);
    }
}
