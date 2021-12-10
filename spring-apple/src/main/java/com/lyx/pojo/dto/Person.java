package com.lyx.pojo.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author lyx
 * @date 2021/9/16,11:17
 **/
@ConfigurationProperties(prefix = "person")
@ToString
@Component
@Data
public class Person {

    private String userName;

    private Boolean boss;

    private Date birth;

    private Integer age;

    private Pet pet;

    private String[] interests;

    private List<String> animal;

    private Map<String,Integer> score;

    private Set<Double> salarys;

    private Map<String , List<Pet>> allPets;

}
