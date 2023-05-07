package org.gfg.JBDL49L10.controller;


import org.gfg.JBDL49L10.model.MyPerson;
import org.gfg.JBDL49L10.model.Person;
import org.gfg.JBDL49L10.requests.PersonRequestData;
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
    private PersonService personService;

    private static Logger logger = LoggerFactory.getLogger("PersonController2");

    @PostMapping("/addPersonData")
    public Person addPerson(@RequestBody @Valid PersonRequestData person) throws SQLException {

        logger.info("Inside our controller with person name as {}" , person.getFirstName());


        Person p = personService.createPerson(person);
//        ResponseEntity responseEntity = new ResponseEntity(person, HttpStatus.BAD_REQUEST);
        return p;

    }

    @GetMapping("/getPersonData")
    public Person getPerson(@RequestParam("id") String pId) throws SQLException {
       return personService.getPersonDataFromId(pId);
    }

    @GetMapping("/getAllPersonData")
    public List<Person> getPerson() throws SQLException {
        return personService.getAllPersonData();
    }

    @DeleteMapping("/deletePersonWithId")
    public Person deletePersonWithId(@RequestParam("id") String id) throws SQLException {
        return personService.deletePersonWithId(id);
    }

    @PostMapping("/createMyPerson")
    public MyPerson addMyPerson(@RequestBody PersonRequestData person ) {
        return personService.addMyPerson(person);
    }

//    person ->  what i want to get stored in db
//            person -> what i am getting from frontend


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrorList =bindingResult.getFieldErrors();
        List<String> error = fieldErrorList.stream().map(x->x.getDefaultMessage()).collect(Collectors.toList());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }





}
