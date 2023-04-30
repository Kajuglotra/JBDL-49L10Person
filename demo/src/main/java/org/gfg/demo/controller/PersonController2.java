package org.gfg.demo.controller;

import org.gfg.demo.Requests.PersonRequest;
import org.gfg.demo.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@ResponseBody
@RequestMapping("/v2")
public class PersonController2 {

    @Autowired
    PersonService personService;
    private static Logger logger = LoggerFactory.getLogger("PersonController2");
    @PostMapping("/addPersonData")
    public PersonRequest addPersonData(@RequestBody @Valid PersonRequest personRequest){
        logger.info("Inside the controller With person Name {}",personRequest.getFirstName());
//        if(personRequest.getFirstName() == null || personRequest.getFirstName() == ""){
//            return  null;
//        }
//        if(personRequest.getDob() == null || personRequest.getDob() == ""){
//            return null;
//        }
        personService.createPerson(personRequest);
        return personRequest;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<String> error = fieldErrors.stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList());
        return  new ResponseEntity<>(error, HttpStatus.BAD_REQUEST );
    }


}
