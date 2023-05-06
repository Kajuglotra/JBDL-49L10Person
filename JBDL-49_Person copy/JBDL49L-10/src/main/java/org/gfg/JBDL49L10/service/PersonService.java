package org.gfg.JBDL49L10.service;

import org.gfg.JBDL49L10.model.Person;
import org.gfg.JBDL49L10.repository.PersonRepository;
import org.gfg.JBDL49L10.requests.PersonRequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;
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
        personRepository.createPerson(p);
        return p;
        // i wanted to save it
        // i should be going to repo
    }
    public Person getPerson(String pId) throws SQLException {
        return personRepository.getPersonData(pId);
    }

    public List<Person> getAllPersonData() throws SQLException {
        return personRepository.getAllPersonsData();
    }

    public Person deletePersonWithId(String id) throws SQLException {
        return personRepository.deletePersonWithId(id);
    }

    public Person updatePersonWithId(PersonRequestData personRequestData , String id) throws SQLException {
        return personRepository.updatePersonWithId(personRequestData, id);
    }
}
