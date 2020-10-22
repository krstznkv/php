package com.example.demo.controllers;

import com.example.demo.domain.Tour;
import com.example.demo.domain.User;
import com.example.demo.services.TourService;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user) {

        return ResponseEntity.ok(userService.updateUser(user));
    }
    @PostMapping("createUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {

        return ResponseEntity.ok(userService.saveUser(user));
    }

    @GetMapping("findAllUsers")
    public ResponseEntity<List<User>> findAllTour() {
        return ResponseEntity.ok(userService.findAll());
    }
    @DeleteMapping("deleteUser/{id}")
    public void deleteUser(@PathVariable String id){
        userService.deleteUser(id);
    }


}
