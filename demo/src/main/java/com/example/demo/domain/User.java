package com.example.demo.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class User {
   private String name;
    private int age;
    private String number;
    @Id
    private String id;
    private  String email;

 public User(String name, int age, String number,String email) {
  this.name = name;
  this.age = age;
  this.number = number;

  this.email = email;
 }
}
