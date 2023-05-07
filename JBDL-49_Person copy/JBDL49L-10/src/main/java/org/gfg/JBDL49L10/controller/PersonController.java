package org.gfg.JBDL49L10.controller;

import org.gfg.JBDL49L10.model.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
public class PersonController {
    // db -> persist some data permanently
    // temporary db

    Map<Integer, Person> personMap = new HashMap<>();
    @PostMapping("/addPersonData")
    public ResponseEntity createPerson(@RequestBody Person person){
        // id, name, dob, age
        Person person1 = Person.builder().firstName(person.getFirstName()).id(person.getId()).lastName(person.getLastName()).dob(person.getDob()).build();
        personMap.put(person.getId(), person1);
        ResponseEntity response = new ResponseEntity(person1, HttpStatus.OK);
        return  response;
    }

    @GetMapping("/getPersonData")
    //path variable or request param
    public ModelAndView passPersonName(@RequestParam("id") int personId){
        ModelAndView modelAndView = new ModelAndView();
        Person person = personMap.get(personId);
        modelAndView.addObject("person", person.getFirstName());
        modelAndView.setViewName("personPage");
        return modelAndView;
    }
    // update // delete




}
