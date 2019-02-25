/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jdbcspring;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * @author AJuda
 */
@SpringBootApplication
public class Application implements CommandLineRunner 
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    PersonRepository repository;
    
    public static void main(String[] args) 
    {
        @SuppressWarnings("UnusedAssignment")
        String i = null;
        
        ConfigurableApplicationContext ctx = SpringApplication
                .run(Application.class, args);
        
        Scanner in = new Scanner(System.in);
        System.out.print("Exit? ");
        
        i = in.nextLine();
        if(i != null)
            ctx.close();
    }
    
    @Override
    public void run(String...args) throws Exception 
    {
        logger.info("\nStudent id 10001 -> {}", repository.findPersonById
        (10001L));
        
        logger.info("\nAll users -> {}", repository.findAll());
        
        logger.info("\nInserting -> {}", repository.insertPerson
        (new Person(10010L, "John", "A1234657")));
        
        logger.info("\nInserting -> {}", repository.createPerson
        (new Person("John", "A1234657")));
        
        logger.info("\nInserting -> {}", repository.createPerson
        (new Person("John", "A1234657")));
        
        Scanner in = new Scanner(System.in);
        
        repository.vievByJDBCTemplate("R", "S");
        
        String name;
        String name2;
        System.out.print("\nEnter first name ");
        name=in.nextLine();
        System.out.print("\nEnter last name ");
        name2=in.nextLine();
        repository.createPerson(new Person(name, name2));
        
//        logger.info("\nUpdate 10001 -> {}", repository.updatePerson
//        (new Person(10001L, "First-Name-Updated", "Last-Name-Updated")));
        
//        logger.info("\nBy JDBCTemplate -> {}"
//                ,repository.enterByJDBCTemplate("R", "S"));
        
//        logger.info("\nBy NamedJDBCTemplate -> {}", repository
//                .enterByNamedJDBCTemplate("R","D"));
        
//        logger.info("\nAll users after-> {}", repository.findAll());        
        
//        repository.deletePersonByID(10002L);

        logger.info("\n\nAll users 2 -> {}", repository.findAll());
    }
}
