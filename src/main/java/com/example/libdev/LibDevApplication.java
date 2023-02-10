package com.example.libdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

//@SpringBootApplication
//@AutoConfiguration
@Configuration
@ComponentScan
@EntityScan
@EnableJpaRepositories
public class LibDevApplication {
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
//            EntityManagerFactoryBuilder builder,
//            DataSource dataSource
//    ) {
//        Map<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.hbm2ddl.auto", "update");
//
//        return builder
//                .dataSource(dataSource)
//                .properties(properties)
//                .build();
//    }

//    @PostConstruct
//    public void doThings(){
//        System.out.println("fuckk");
//        System.setProperty("liquibase.secureParsing", "false");
//
//    }


}
