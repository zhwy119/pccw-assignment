package com.derek.user.controller;


import com.derek.user.domain.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class DemoController {



    @GetMapping
    public String test(){
        Person person = new Person("derek", 39, "远洋天地");
        return "hello ";
    }

//    class Person {
//        String name;
//        int age;
//        String addr;
//
//        Person(String name, int age, String addr){
//            this.name = name;
//            this.age = age;
//            this.addr = addr;
//
//        }
//
//    }
}
