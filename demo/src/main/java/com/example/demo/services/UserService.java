package com.example.demo.services;

import com.example.demo.domain.Tour;
import com.example.demo.domain.User;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
   TourService tourService;
    @Autowired
    UserRepo userRepo;
    public User saveUser(String idTour,User user){
       Tour tour= tourService.findById(idTour);
       userRepo.save(user);
       tour.saveMember(userRepo.findByName(user.getName()));
       tourService.update(tour);
        return user;
    }
    public User updateUser(String idTour, User user){
        Tour tour= tourService.findById(idTour);
        tour.updateMember(user);
        tourService.update(tour);
        return user;
    }
    public void deleteUser(String idTour,String idUser){
        tourService.findById(idTour).deleteMember(idUser);
        userRepo.deleteById(idUser);
    }
    public Tour changeTour(String idTourBefore,String idAfter, User user){
        Tour tour= tourService.findById(idTourBefore);
        Tour tour2= tourService.findById(idAfter);
        tour.deleteMember(user.getId());
        tour2.saveMember(user);
        tourService.update(tour);
        tourService.update(tour2);
        return tour2;
    }
    public void deleteFromTour(String id,String idUser){
        Tour tour = tourService.findById(id);
        tour.deleteMember(idUser);
        tourService.update(tour);
    }
}
