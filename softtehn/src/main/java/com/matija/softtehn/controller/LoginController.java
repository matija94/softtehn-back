package com.matija.softtehn.controller;

import com.matija.softtehn.model.User;
import com.matija.softtehn.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private CustomUserDetailService userDetailsService;

    @PostMapping(Urls.LOGIN)
    public ResponseEntity<String> login(@RequestBody User user) {
        UserDetails dbUser = null;
        try {
            dbUser = userDetailsService.loadUserByUsername(user.getEmail());
        }
        catch (UsernameNotFoundException e) {}
        finally {
           if (dbUser != null) {
               return new ResponseEntity<>(dbUser.getUsername(), HttpStatus.OK);
           }
           else {
               return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
           }
        }
    }
}
