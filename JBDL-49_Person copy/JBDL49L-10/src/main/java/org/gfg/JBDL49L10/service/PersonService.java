package org.gfg.JBDL49L10.service;

import org.gfg.JBDL49L10.model.MyPerson;
import org.gfg.JBDL49L10.model.Person;
import org.gfg.JBDL49L10.repository.PersonJpaRepository;
import org.gfg.JBDL49L10.repository.PersonRepository;
import org.gfg.JBDL49L10.requests.PersonRequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonJpaRepository personJpaRepository;
    private Integer calculateAge(String dob){
        if(dob == null){
            return  0;
        }
        // see todays date
        // see his dob
        // diff
        // year
        LocalDate dobDate = LocalDate.parse(dob);
        LocalDate date= LocalDate.now();
        return Period.between(dobDate, date).getYears();
    }
    public Person createPerson(PersonRequestData personRequestData) throws SQLException {
        // add this object to my db
        // add some business logic

        // if i wanted needed to hit 3rd party apis
        // age from dob
        Person p = personRequestData.to();
        int age =0;
        if(p.getAge() == null){
            age = calculateAge(personRequestData.getDob());
            p.setAge(age);
        }
        // i wanted to save it
        // i should be going to repo
        personRepository.createPerson(p);
        return p;

    }

    public Person getPersonDataFromId(String pId) throws SQLException {
        Person person = personRepository.getPersonDataFromId(pId);
        return person;
    }

    public List<Person> getAllPersonData() throws SQLException {
       return personRepository.getAllPersonData();

    }

    public Person deletePersonWithId(String id) throws SQLException {
        return personRepository.deletePersonWithId(id);
    }

    public MyPerson addMyPerson(PersonRequestData p){
        MyPerson myPerson = p.toMyPerson();
        int age =0;
        if(myPerson.getAge() == null){
            age = calculateAge(myPerson.getDob());
            myPerson.setAge(age);
        }
        personJpaRepository.save(myPerson);
        return myPerson;
    }
}

// mysql query -> parsed and it got executed
