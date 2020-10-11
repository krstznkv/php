package com.example.demo.domain;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Data
@Document
public class Tour {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private Double price;
    private String date;
    private String time;
    private String description;
    private String guide;
   // @DBRef
    private List<User> members=new ArrayList();

    public Tour(String name, Double price, String date, String time, String description, String guide) {
        this.name = name;
        this.price = price;
        this.date = date;
        this.time = time;
        this.description = description;
        this.guide = guide;
    }
    public User findMemberById(String id){
        for (User u:members
             ) {if(u.getId().equals(id))
            return u;
        }
        throw new NoSuchElementException();
    }
    public User updateMember(User user){
        User mem=this.findMemberById(user.getId());
        this.members.remove(mem);
        this.members.add(user);
        return user;
    }
    public void deleteMember(String id){
        User user=findMemberById(id);
        this.members.remove(user);
    }
    public void saveMember(User user){
        if(members.contains(user))
            throw new IllegalArgumentException("user exist");
        members.add(user);
    }

}
