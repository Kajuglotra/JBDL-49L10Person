package org.gfg.demo.service;

import org.gfg.demo.Model.Person;
import org.gfg.demo.Requests.PersonRequest;
import org.gfg.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;


@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    private Integer calculatePersonAge(String dob){
        if(dob == null){
            return  null;
        }
        LocalDate dobDate =LocalDate.parse(dob);
        LocalDate date  = LocalDate.now();

        return Period.between(date,dobDate).getYears();
    }
    public void createPerson(PersonRequest personRequest){
        Person person = personRequest.to();
        int age = 0;
        if(person.getAge() == null){
            age = calculatePersonAge(person.getDob());
        }
        person.setAge(age);
        personRepository.createPerson(person);
    }

}
