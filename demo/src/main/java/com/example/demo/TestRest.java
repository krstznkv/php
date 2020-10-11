package com.example.demo;

import com.example.demo.domain.Message;
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
public class TestRest {
    private final TourService service;
    private final UserService userService;
    @PostMapping("createTour")
    public ResponseEntity<Tour> createTour(@RequestBody Tour tour) {
        return ResponseEntity.ok(service.save(tour));
    }
    @PutMapping("updateTour")
    public ResponseEntity<Tour> updateTour(@RequestBody Tour tour) {
        return ResponseEntity.ok(service.update(tour));
    }
    @DeleteMapping("deleteTour/{id}")
    public ResponseEntity<Message> deleteTour(@PathVariable String id) {
        service.remove(id);
        return ResponseEntity.ok(new Message("ok"));
    }
    @GetMapping("findAllTours")
    public ResponseEntity<List<Tour>> findAllTour() {
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("deleteAll")
    public ResponseEntity<Message> deleteAllTour() {
        service.deleteAll();
        return ResponseEntity.ok(new Message("ok"));
    }
    @DeleteMapping("deleteFromTour/{idTour}/{idUser}")
    public void deleteFromTour(@PathVariable String idTour, @PathVariable String idUser){
        userService.deleteFromTour(idTour,idUser);
    }
    @PutMapping("updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String tourId,@RequestBody User user) {

        return ResponseEntity.ok(userService.updateUser(tourId,user));
    }
    @PostMapping("createUser/{tourId}")
    public ResponseEntity<User> createUser(@PathVariable String tourId,@RequestBody User user) {

        return ResponseEntity.ok(userService.saveUser(tourId,user));
    }
    @DeleteMapping("deleteUser/{idTour}/{idUser}")
    public void deleteUser(@PathVariable String idTour, @PathVariable String idUser){
        userService.deleteFromTour(idTour,idUser);
    }

}
