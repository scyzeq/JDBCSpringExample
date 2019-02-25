/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jdbcspring;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author AJuda
 */
@Repository
public class PersonRepository implements RowMapper<Person>
{
    @Override
    public Person mapRow(ResultSet rs, int i) throws SQLException 
    {
        Person person = new Person();
        person.setId(rs.getLong("id"));
        person.setFirstName(rs.getString("first_Name"));
        person.setLastName(rs.getString("last_Name"));
        return person;
    }
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private NamedParameterJdbcTemplate namedJDBC;
    
    private static final String selectIDSql = "SELECT * FROM person WHERE id = ?";
    private static final String deleteIDSql = "DELETE FROM person WHERE id = ?";
    private static final String updateIDSql = "UPDATE person SET first_Name = ?,"
            + " last_Name = ? WHERE id = ?";
    private static final String selectAllSql = "SELECT * FROM person";
    private static final String createPersonSql = "INSERT INTO person "
            + "(first_Name, last_Name) values(?, ?)";
    private static final String insertPersonIDSql = 
            "INSERT INTO person (id, first_Name, last_Name)" 
                + "values(?, ?, ?)";
    private static final String personFirstLettersSql = "SELECT * FROM person "
            + "WHERE first_Name LIKE ? OR first_Name LIKE ?";
    private static final String personFirstLettersNamedSql = "SELECT * FROM "
            + "person WHERE first_Name LIKE :firstNameLetter AND last_Name "
            + "LIKE :lastNameLetter";
    
    public Person findPersonById(long id)
    {
        return jdbcTemplate.queryForObject(selectIDSql, new Object[]{id},
                new PersonRepository());
    }
    
    public List<Person> findAll() throws SQLException
    {
        return namedJDBC.getJdbcTemplate().query(selectAllSql, new PersonRepository());
//        return jdbcTemplate.query(selectAllSql, new PersonRepository());
    }
    
    public int insertPerson(Person person)
    {
        return jdbcTemplate.update(insertPersonIDSql, new Object[]
                {
                    person.getId(), 
                    person.getFirstName(), 
                    person.getLastName()
                });
    }
    
    public List<Person> enterByJDBCTemplate(String letterOne, String letterTwo)
    {
        letterOne+="%";
        letterTwo+="%";
        List<Person> persons = jdbcTemplate.query(personFirstLettersSql, 
                new PersonRepository(), letterOne, letterTwo);
        return persons;
    }
    
    public List <Person> enterByNamedJDBCTemplate(String firstLetter
            , String lastLetter)
    {        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("firstNameLetter", firstLetter+"%");
        params.put("lastNameLetter", lastLetter+"%");
        
        return namedJDBC.query(personFirstLettersNamedSql, params, 
                new PersonRepository());
    }
    
    public void vievByJDBCTemplate(String letterOne, String letterTwo)
    {
        letterOne+="%";
        letterTwo+="%";
        
        String sql = "CREATE VIEW Temp AS SELECT * FROM person WHERE first_Name "
                + "LIKE '" + letterOne 
                + "' OR first_Name LIKE '" + letterTwo + "'";
        
        jdbcTemplate.execute(sql);
    }
    
//    Alternative version of create person without Object[] implementation
//    public int createPerson(Person person)
//    {
//        return jdbcTemplate.update(createPersonSql, person.getFirstName(), person.getLastName());
//    }
    
    public int createPerson(Person person)
    {
        return jdbcTemplate.update(createPersonSql, 
                new Object[]
                {
                    person.getFirstName(), person.getLastName()
                });
    }
    
    public int deletePersonByID(long id)
    {
        return jdbcTemplate.update(deleteIDSql, new Object[] {id}); 
    }
    
    public int updatePerson(Person person)
    {
        return jdbcTemplate.update(updateIDSql,
                new Object[]
                {
                    person.getFirstName(),
                    person.getLastName(),
                    person.getId()
                });
    }
}