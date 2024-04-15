package com.proje.konutapp.services;

import com.proje.konutapp.config.JwtProvider;
import com.proje.konutapp.entities.User;
import com.proje.konutapp.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;
    @Autowired
    private JwtProvider jwtProvider;

    public void findByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if(user!=null){
            throw new Exception("This account is already exist with this email: "+ email);
        }
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long userId) throws Exception{
        Optional<User> user =  userRepository.findById(userId);

        if(user.isPresent()){
            return user.get();
        }else {
            throw  new Exception("There is no user that has this id: "+ userId);
        }
    }

    public User findUserByJwt(String jwt) throws Exception{

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        if(email==null){
            throw new Exception("Provide valid jwt token!");
        }

        User user= userRepository.findByEmail((email));

        if(user==null){
            throw new Exception("User not found with this email: "+email);
        }
        return user;
    }
}
