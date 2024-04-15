package com.proje.konutapp.services;

import com.proje.konutapp.entities.Apartment;
import com.proje.konutapp.entities.User;
import com.proje.konutapp.repos.ApartmentRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ApartmentService {

    @Autowired
    private ApartmentRepository apartmentRepository;
    @Autowired
    private MailSenderService mailSenderService;

    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String formattedDate = today.format(formatter);

    @Async
    public void sendEmailNotification(Apartment apartment, User user) throws MessagingException {
        String to = user.getEmail();
        String userName = user.getFullName();
        String adName = apartment.getAdName();
        String subject = "Bir ilanı güncellediniz!";
        String message = String.format("Sayın %s,\n\n %s tarihinde '%s' isimli bir ilanı güncellediniz!", userName, formattedDate, adName);
        mailSenderService.sendMail(to, subject, message);
    }


    public Apartment saveApartment(Apartment apartment, User user) throws MessagingException {
        Apartment createdApartment = new Apartment();
        createdApartment.setAdName(apartment.getAdName());
        createdApartment.setAgeOfApartment(apartment.getAgeOfApartment());
        createdApartment.setApartmentName(apartment.getApartmentName());
        createdApartment.setFloorLevel(apartment.getFloorLevel());
        createdApartment.setAddress(apartment.getAddress());
        createdApartment.setArea(apartment.getArea());
        createdApartment.setPresenceOfBalcony(apartment.getPresenceOfBalcony());
        createdApartment.setNumberOfBathrooms(apartment.getNumberOfBathrooms());
        createdApartment.setNumberOfBedrooms(apartment.getNumberOfBedrooms());
        createdApartment.setUser(user);

        return apartmentRepository.save(createdApartment);
    }

    public List<Apartment> getAllApartments(){
        return (List<Apartment>) apartmentRepository.findAll();
    }

    public List<Apartment> getUserApartments(Integer userId){
        return (List<Apartment>) apartmentRepository.findByUserId(Long.valueOf(userId));
    }

    public Optional<Apartment> getApartment(Integer apartmentId){
        return apartmentRepository.findById(apartmentId);
    }

    public void deleteApartment(Integer apartmentId) {
        apartmentRepository.deleteById(apartmentId);
    }

    public Apartment updateApartment(Integer apartmentId, Apartment updatedApartment) throws MessagingException {
        Optional<Apartment> apartment = apartmentRepository.findById(apartmentId);
        if(apartment.isPresent()){
            Apartment foundedApartment = apartment.get();
            foundedApartment.setAdName(updatedApartment.getAdName());
            foundedApartment.setAddress(updatedApartment.getAddress());
            foundedApartment.setArea(updatedApartment.getArea());
            foundedApartment.setApartmentName(updatedApartment.getApartmentName());
            foundedApartment.setAgeOfApartment(updatedApartment.getAgeOfApartment());
            foundedApartment.setNumberOfBedrooms(updatedApartment.getNumberOfBedrooms());
            foundedApartment.setNumberOfBathrooms(updatedApartment.getNumberOfBathrooms());
            foundedApartment.setFloorLevel(updatedApartment.getFloorLevel());
            foundedApartment.setPresenceOfBalcony(updatedApartment.getPresenceOfBalcony());

            Apartment savedApartment = apartmentRepository.save(foundedApartment);
            sendEmailNotification(savedApartment, savedApartment.getUser());

            return savedApartment;
        }
        return null;
    }
}
