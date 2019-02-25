/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jdbcspring;


/**
 *
 * @author AJuda
 */
public class Person 
{
    private long id;
    private String firstName;
    private String lastName;
    
    public Person() 
    {
        super();
    }
    
    public Person(String firstName, String lastName) 
    {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public Person(long id, String firstName, String lastName) 
    {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    @Override
    public String toString() {
        return "\nPerson{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + '}';
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}