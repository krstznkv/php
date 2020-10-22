package com.example.demo.services;

import com.example.demo.domain.Tour;
import com.example.demo.domain.User;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
   TourService tourService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    private MongoOperations mongoOperations;
    public User saveUser(User user){
       Tour tour= tourService.findById(user.getIdTour());
       User userFromDB=userRepo.findByName(user.getName());
       if(userFromDB!=null) throw new IllegalArgumentException("User exist");
       tour.saveMember(userRepo.save(user));
       tourService.update(tour);
        return user;
    }
    public User updateUser(User user){
        User u=userRepo.findById(user.getId()).get();
        if(u.getIdTour().equals("does't selected")) {
            Tour tour1=tourService.findById(user.getIdTour());
            mongoOperations.save(user);
            tour1.saveMember(user);
        } else{

        if(!u.getIdTour().equals(user.getIdTour())){
            Tour tour= tourService.findById(user.getIdTour());
            Tour tour2= tourService.findById(u.getIdTour());
            tour2.deleteMember(user.getId());
            tour.saveMember(user);
            tourService.update(tour);
            tourService.update(tour2);
            mongoOperations.save(user);
        }
        Tour tour= tourService.findById(user.getIdTour());
        tour.updateMember(user);
        tourService.update(tour);
        mongoOperations.save(user);
        }
        return user;
    }
    public void deleteUser(String idUser){
        User u=userRepo.findById(idUser).get();
        Tour t=tourService.findById(u.getIdTour());
        t.deleteMember(idUser);
        tourService.update(t);
        userRepo.deleteById(idUser);
    }
    public void deleteFromTour(String id,String idUser){
        Tour tour = tourService.findById(id);
        tour.deleteMember(idUser);
        User u=userRepo.findById(idUser).get();
        u.setIdTour("does't selected");
        tourService.update(tour);
        mongoOperations.save(u);
        System.out.println(u.getName());
    }
    public void deleteAll(){
        userRepo.deleteAll();
    }
    public List<User> findAll(){
        List<Tour> tours=tourService.findAll();
        List<User> list= userRepo.findAll();
        for (User u: list
             ) {
            if(u.getIdTour().equals("does't selected")){
               u.setTourName(u.getIdTour());
                continue;
            }
            for (Tour t:tours
                 ) {
                if(t.getId().equals(u.getIdTour()))
                    u.setTourName(t.getName());
            }
        }
        return list;
    }
}
