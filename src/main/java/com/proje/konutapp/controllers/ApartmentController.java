package com.proje.konutapp.controllers;

import com.proje.konutapp.entities.Apartment;
import com.proje.konutapp.entities.User;
import com.proje.konutapp.services.ApartmentService;
import com.proje.konutapp.services.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/apartments")
public class ApartmentController {
    @Autowired
    private ApartmentService apartmentService;
    @Autowired
    private UserService userService;

    @PostMapping("save/apartment")
    public Apartment saveApartment(@RequestHeader("Authorization") String jwt, @RequestBody Apartment apartment ) throws Exception {
        User user = userService.findUserByJwt(jwt);
        return apartmentService.saveApartment(apartment, user );

    }
    @GetMapping
    public List<Apartment> getAllAparments(){
        return apartmentService.getAllApartments();
    }

    @GetMapping("get/userApartments/{userId}")
    public List<Apartment> getAparmentsByUserId(@PathVariable Integer userId){
        return apartmentService.getUserApartments(userId);
    }

    @GetMapping("/get/apartment/{apartmentId}")
    public Optional<Apartment> getApartment(@PathVariable Integer apartmentId){
        return  apartmentService.getApartment(apartmentId);
    }
    @GetMapping("/delete/apartment/{apartmentId}")
    public void deleteApartment(@PathVariable Integer apartmentId){
        apartmentService.deleteApartment(apartmentId);
    }

    @PutMapping("/update/apartment/{apartmentId}")
    public Apartment updateApartment(@PathVariable Integer apartmentId, @RequestBody Apartment updatedApartment) throws MessagingException {
        return apartmentService.updateApartment(apartmentId, updatedApartment);
    }
}
