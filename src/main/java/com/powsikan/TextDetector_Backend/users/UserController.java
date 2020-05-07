package com.powsikan.TextDetector_Backend.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{username}")
    public Optional<User> getUser(@PathVariable String username) {
        return userService.getUser(username);
    }

    @PostMapping()
    @ResponseBody
    public Object addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping()
    public Object updateUser(@RequestBody User user) {

        return userService.updateUser(user);
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable String username) {
        userService.deleteUser(username);

    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity validateUser(@RequestBody User user){
        return userService.validateUser(user);
    }

}
