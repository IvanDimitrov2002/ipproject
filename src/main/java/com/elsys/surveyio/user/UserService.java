package com.elsys.surveyio.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findOneById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    public User findOneByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException(username));
    }

    public User create(CreateUserDto userDto){
        ModelMapper modelMapper = new ModelMapper();
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User newUser = modelMapper.map(userDto, User.class);
        return userRepository.save(newUser);
    }

    public User update(Long id, CreateUserDto userDto){
        ModelMapper modelMapper = new ModelMapper();
        if(userDto.getPassword() != null){
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        User user = userRepository.getOne(id);
        modelMapper.map(userDto, user);
        return  userRepository.save(user);
    }
}
