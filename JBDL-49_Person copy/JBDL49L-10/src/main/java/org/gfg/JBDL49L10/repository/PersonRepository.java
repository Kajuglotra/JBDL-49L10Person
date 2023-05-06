package org.gfg.JBDL49L10.repository;

import org.gfg.JBDL49L10.model.Person;
import org.gfg.JBDL49L10.requests.PersonRequestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
//        Statement statement = connection.createStatement();
//        Boolean result = statement.execute("insert into jbdl_49.person(id, firstName, lastName, dob, age) VALUES(1,'fname', 'lname', '1990-01-01' ,23)");
//        logger.info("result of the insertion statemnet is {}"  , result);

        PreparedStatement preparedStatement  =connection.prepareStatement("INSERT INTO person(firstName,lastName,dob,age) VALUES(?,?,?,?)");
//        preparedStatement.setInt(1, new Random().nextInt(100));
        preparedStatement.setString(1,person.getFirstName());
        preparedStatement.setString(2, person.getLastName());
        preparedStatement.setString(3,person.getDob());
        preparedStatement.setInt(4, person.getAge());

        Boolean b = preparedStatement.execute();
        logger.info("result of the insertion Statement is {} ", b);

    }
    public void createPersonTable() throws SQLException {
        Statement statement = connection.createStatement();
        boolean result = statement.execute("CREATE TABLE IF NOT EXISTS jbdl_49.person(id int NOT NULL auto_increment , firstName varchar(30) ,lastName varchar(30) ,dob varchar(15), age int , PRIMARY KEY(id))");
        logger.info("create table has been executed with the result {}" ,result);
    }

    public Person getPersonData(String pId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from person where id = ?");
        preparedStatement.setString(1, pId);
        ResultSet resultSet = preparedStatement.executeQuery();
        // local variables
        while(resultSet.next()){
            Person person = getPersonDataFromTable(resultSet);
            return person;
        }
        return null;
    }

    public List<Person> getAllPersonsData() throws SQLException {
        List<Person> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from person");
        while (resultSet.next()){
            Person person = getPersonDataFromTable(resultSet);
            list.add(person);
        }
        return list;
    }

    private Person getPersonDataFromTable(ResultSet resultSet) throws SQLException {
            String fName = resultSet.getString("firstName");
            String lName = resultSet.getString("lastName");
            String dob = resultSet.getString("dob");
            int age = resultSet.getInt("age");
            return  Person.builder().firstName(fName).lastName(lName).dob(dob).age(age).build();
    }

    public Person deletePersonWithId(String id) throws SQLException {
        Person person = getPersonData(id);
        PreparedStatement preparedStatement = connection.prepareStatement("delete from person where id = ?");
        preparedStatement.setInt(1,Integer.valueOf(id));
        preparedStatement.execute();
        return person;
    }

    public Person updatePersonWithId(PersonRequestData personRequestData , String id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update person set firstName = ? , lastName = ? ,dob =  ? where id= ?");
        preparedStatement.setString(1 , personRequestData.getFirstName());
        preparedStatement.setString(2, personRequestData.getLastName());
        preparedStatement.setString(3, personRequestData.getDob());
        preparedStatement.setInt(4, Integer.valueOf(id));
        preparedStatement.execute();
        Person person = getPersonData(id);
        return person;
    }
}
