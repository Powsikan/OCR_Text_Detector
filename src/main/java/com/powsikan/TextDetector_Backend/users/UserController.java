package com.powsikan.TextDetector_Backend.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/users")
    public List<User> getAll() {
        return userService.getAll();
    }

    @RequestMapping("/users/{username}")
    public Optional<User> getUser(@PathVariable String username) {
        return userService.getUser(username);

    }

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    @ResponseBody
    public Object addUser(@RequestBody User user) {
       return userService.addUser(user);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users/{username}")
    public void updateUser( @PathVariable String username,   @RequestBody User user) {
        userService.updateUser(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{username}")
    public void deleteUser(@PathVariable String username) {
        userService.deleteUser(username);

    }

}
