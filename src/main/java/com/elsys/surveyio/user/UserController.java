package com.elsys.surveyio.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<User> findAll(){
        List<User> users = userService.findAll();
        for(User user : users){
            user.setPassword("");
        }
        return  users;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> findOneById(@PathVariable("id") Long id){
        try {
            User user = userService.findOneById(id);
            user.setPassword("");
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(Map.of("message", "User not found"), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/user")
    public User create(@RequestBody CreateUserDto userDto){
        return userService.create(userDto);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> update(@PathVariable("id") Long id, @RequestBody CreateUserDto userDto){
        try {
            return new ResponseEntity<>(userService.update(id, userDto), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(Map.of("message", "User not found"), HttpStatus.NOT_FOUND);
        }
    }
}
