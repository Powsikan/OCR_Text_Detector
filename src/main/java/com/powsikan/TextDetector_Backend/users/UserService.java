package com.powsikan.TextDetector_Backend.users;

import jdk.net.SocketFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(String username) {
        return userRepository.findById(username);
    }

    public Object addUser(User user) {
        if (!userRepository.existsById(user.getUsername())) {
            return userRepository.save(user);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    public Object updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    public ResponseEntity validateUser(User user) {
        User user1 = userRepository.findByUsername(user.getUsername());
        if (user1 != null) {
            if (user1.getPassword().equals(user.getPassword()) ) {
                return new ResponseEntity("user valid", HttpStatus.OK);
            } else {
                return new ResponseEntity("password not valid", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity("user not found", HttpStatus.NOT_FOUND);
        }
    }
}
