package org.gfg.demo.repository;

import org.gfg.demo.Model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class PersonRepository {

    @Autowired
    Connection connection;

    public PersonRepository(Connection connection) throws SQLException {
        this.connection = connection;
        createTable();
    }

    private static Logger logger = LoggerFactory.getLogger("PersonRepository.class");

    public void createPerson(Person person){

        try {
            Statement statement = connection.createStatement();
            Boolean result  = statement.execute("insert into AMCARE.createperson(id, name, dob, age) VALUES(1,'name', '2022-23-2' , 32)");
            logger.info("result of the insertion of table is {}" , result);
        }catch (Exception e){
            logger.error("Exception happened" + e);
        }


    }

    public void createTable() throws SQLException {
        Statement statement = connection.createStatement();
        boolean result =statement.execute("CREATE  TABLE AMCARE.createperson (id int NOT NULL , name VARCHAR(30), dob VARCHAR(10) , AGE int, PRIMARY KEY (id))");
        logger.info("result of the creation of table is {}" , result);
    }
}
