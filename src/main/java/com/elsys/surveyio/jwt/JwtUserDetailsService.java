package com.elsys.surveyio.jwt;

import java.util.ArrayList;

import com.elsys.surveyio.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            com.elsys.surveyio.user.User user = userService.findOneByUsername(username);
            return new User(user.getUsername(), user.getPassword(),
                    new ArrayList<>());
        } catch (Exception e){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
