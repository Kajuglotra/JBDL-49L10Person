package org.gfg.JBDL49L10.repository;

import org.gfg.JBDL49L10.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository {
    @Autowired
    private  Connection connection;
//    Setter dependency injection
    public PersonRepository(Connection connection){
        this.connection =connection;
        try {
            createPersonTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private static Logger logger = LoggerFactory.getLogger("PersonRepository.class");
    public void createPerson(Person person) throws SQLException {
        //1)  connection
        // statements-> commands what i want to run on db
        // executed it
        // mysql -u root -p password
        // inserting the data into db


        // java object
        // database
        // parse java object to database row -> mapping
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO person(firstName, lastName, dob, age) VALUES(?,?,?,?)");
//        preparedStatement.setInt(1, person.getId()); // frontend
        preparedStatement.setString(1, person.getFirstName());
        preparedStatement.setString(2, person.getLastName());
        preparedStatement.setString(3, person.getDob());
        preparedStatement.setInt(4, person.getAge());
        preparedStatement.execute();
//        Statement statement = connection.createStatement();
        // prepared statement "?"
//        Boolean result = statement.execute("insert into person( firstName, lastName, dob, age) VALUES('fname', 'lname', '1990-01-01' ,23)");
//        logger.info("result of the insertion statemnet is {}"  , result);

    }
    public void createPersonTable() throws SQLException {
        Statement statement = connection.createStatement();
        boolean result = statement.execute("CREATE TABLE IF NOT EXISTS person (id int NOT NULL auto_increment, firstName varchar(30) ,lastName varchar(30), dob varchar(15), age int , PRIMARY KEY(id))");
        logger.info("create table has been executed with the result {}" ,result);
    }

    public Person getPersonDataFromId(String pId) throws SQLException {
        //
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from person where id = ?");
        preparedStatement.setInt(1, Integer.valueOf(pId));
        ResultSet resultSet = preparedStatement.executeQuery();
        // parse this result set into person
        if(resultSet.next()){
            return getPersonDataFromRow(resultSet);
        }
        return null;

    }

    public List<Person> getAllPersonData() throws SQLException {
        List<Person> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT  * from person"); // 1lac
        while (resultSet.next()){
            Person person = getPersonDataFromRow(resultSet);
            list.add(person);
        }
        return list;
    }

    private Person getPersonDataFromRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String fName  =  resultSet.getString("firstName");
        String lName = resultSet.getString("lastName");
        String dob = resultSet.getString(4);
        int age = resultSet.getInt("age");
        Person person = Person.builder().id(id).firstName(fName).lastName(lName).dob(dob).age(age).build();
        return person;
    }

    public Person deletePersonWithId(String id) throws SQLException {
        Person person = getPersonDataFromId(id);
        if(person != null){
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE  from person where id = ?");
            preparedStatement.setInt(1, Integer.valueOf(id));
            preparedStatement.execute();
        }
        return person;
    }

}


//JPA
//interfaces and some classes

// jpa -> guidlines , standardization

// hibernate -> implementation

// mapping java object with sql rows manually

// jpa interface -> rowmapper ,
// spring boot -> tomcat embedded server
// jpa embedded hibernate

// save()  -> jpa
// save can be provided by hibernate

// save is jpa
//jpa break save into multiple parts
// we have different function  -> hibernate


