package org.gfg.JBDL49L10.controller;


import ExceptionHandlers.BadRequest;
import org.gfg.JBDL49L10.model.Myperson;
import org.gfg.JBDL49L10.model.Person;
import org.gfg.JBDL49L10.requests.PersonRequestData;
import org.gfg.JBDL49L10.service.PersonJPAService;
import org.gfg.JBDL49L10.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;


//real app
@RestController
@RequestMapping("/v2")
public class PersonController2 {

    @Autowired
    private PersonJPAService personJPAService;

    @Autowired
    private PersonService personService;

    private static Logger logger = LoggerFactory.getLogger("PersonController2");

    @PostMapping("/addPersonData")
    public Myperson addPerson(@RequestBody @Valid PersonRequestData person) throws SQLException {

        logger.info("Inside our controller with person name as {}" , person.getFirstName());


        // store data to db
        // validations inside our controller
        // if validations fail -> handle the exception
        // light controller
//        if(person.getFirstName() == null || person.getFirstName() == ""){
//            return  null;
//        }
//        if(person.getDob() ==  null || person.getDob() ==""){
//            return null;
//        }
        // in appropriate case
//        service class
        Myperson p = personJPAService.createPerson(person);
//        ResponseEntity responseEntity = new ResponseEntity(person,);
        return p;

    }

//    person ->  what i want to get stored in db
//    person -> what i am getting from frontend

    @GetMapping("/getPersonData")
    public Person getPersonData(@RequestParam("id") @Valid String pId) throws BadRequest, SQLException {
        if(pId == null || pId == ""){
            throw new BadRequest("pass id as request param");
        }
        logger.info("Inside the get Person data for some person id , {} " , pId);
        Person person = personService.getPerson(pId);
        return person;
    }

    @GetMapping("/getAllPersonData")
    public List<Person> getAllPersonData() throws SQLException {
        List<Person> list = personService.getAllPersonData();
        return list;
    }

    @DeleteMapping("/deletePerson")
    public Person deletePerson(@RequestParam("id") String id) throws SQLException {
        Person person = personService.deletePersonWithId(id);
        return person;
    }
    @PutMapping("/updatePerson")
    public Person updatePersonById(@RequestBody PersonRequestData personRequestData, @RequestParam String id) throws SQLException {
        Person person = personService.updatePersonWithId(personRequestData, id);
        return person;
    }


}
