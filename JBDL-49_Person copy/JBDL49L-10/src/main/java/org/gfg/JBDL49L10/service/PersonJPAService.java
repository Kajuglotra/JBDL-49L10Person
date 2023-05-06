package org.gfg.JBDL49L10.service;

import org.gfg.JBDL49L10.model.Myperson;
import org.gfg.JBDL49L10.repository.PersonJpaRepository;
import org.gfg.JBDL49L10.requests.PersonRequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

@Service
public class PersonJPAService {
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
    public Myperson createPerson(PersonRequestData personRequestData) throws SQLException {
        Myperson myPerson =personRequestData.toMyPerson();
        Integer age = calculateAge(myPerson.getDob());
        myPerson.setAge(age);
        return personJpaRepository.save(myPerson);
    }

}
