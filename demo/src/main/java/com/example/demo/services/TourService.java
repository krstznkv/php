package com.example.demo.services;

import com.example.demo.domain.Tour;
import com.example.demo.domain.User;
import com.example.demo.repo.TourRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourService {
    @Autowired
    private TourRepo tourRepo;
    @Autowired
    private MongoOperations mongoOperations;
    public List<Tour> findAll(){
        return tourRepo.findAll();
    }
    public Tour findById(String id){
        return tourRepo.findById(id).get();
    }
    public Tour save(Tour tour){
     Tour tour1=tourRepo.findByName(tour.getName());
     if(tour1!=null){
         throw new IllegalArgumentException("Tour exist");
     }
     return tourRepo.save(tour);
    }
   public Tour update(Tour tour){
       return mongoOperations.save(tour);
    }
    public void remove(String id){
        Tour tour=this.findById(id);
        List<User> users=tour.getMembers();
        for (User user:users
             ) {
            user.setIdTour("does't selected");
            mongoOperations.save(user);
        }
        tourRepo.deleteById(id);
    }
    public void deleteAll(){
        tourRepo.deleteAll();
    }

}
