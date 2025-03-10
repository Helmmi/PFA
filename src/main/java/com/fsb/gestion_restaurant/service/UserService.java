package com.fsb.gestion_restaurant.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fsb.gestion_restaurant.models.User;
import com.fsb.gestion_restaurant.models.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUsername(username);
        if (user.isPresent()) {
            var userObj = user.get();
            return user.build()
                    .email(userObj.getEmail())
                    .password(userObj.getPassword())
                    .build();
            
        }else{
            throw new UsernameNotFoundException(username);
        }
        throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
    }


}
