package com.lyx.controller;

import com.lyx.pojo.dto.Person;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lyx
 * @date 2021/9/16,14:09
 **/
@Slf4j
@RestController
@RequestMapping("/person")
public class PersonController {


    @Autowired
    private Person person;

    @GetMapping("/getPerson")
    private Person getPerson(){

        return person;
    }

}
