///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.jdbcspring;
//
//import javax.sql.DataSource;
//import org.apache.commons.dbcp.BasicDataSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
//import org.springframework.jndi.JndiObjectFactoryBean;
//
///**
// *
// * @author AJuda
// */
//@Configuration
//public class DBConfiguration 
//{
//    @Bean
//    NamedParameterJdbcTemplate namedJDBC(DataSource dataSource)
//    {
//        return new NamedParameterJdbcTemplate(dataSource);
//    }
//    
//    @Bean
//    JdbcTemplate jdbcTemplate(DataSource dataSource)
//    {
//        return new JdbcTemplate(dataSource);
//    }
//    
//    @Profile("development")
//    @Bean
//    public DataSource embeddedDataSource()
//    {
//        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
//                .addScript("classpath:data.spl")
//                .addScript("classpath:schema.spl")
//                .build();
//    }
//    
//    @Profile("qa")
//    @Bean
//    public DataSource dataSource()
//    {
//        BasicDataSource bds = new BasicDataSource();
//        bds.setDriverClassName("org.h2.Driver");
//        bds.setUrl("jdbc:h2:tcp://localhost/~/spitter");
//        bds.setUsername("sa");
//        bds.setPassword("");
//        bds.setInitialSize(5);
//        bds.setMaxActive(10);
//        return bds;
//    }
//    
//    @Profile("production")
//    @Bean
//    public DataSource dataSource2()
//    {
//        JndiObjectFactoryBean jndiObject = new JndiObjectFactoryBean();
//        jndiObject.setJndiName("jdbc/SpittrDS");
//        jndiObject.setResourceRef(true);
//        jndiObject.setProxyInterface(javax.sql.DataSource.class);
//        return (DataSource) jndiObject.getObject();
//    }
//}
