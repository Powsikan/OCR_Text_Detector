package com.powsikan.TextDetector_Backend.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class User {


    @Id
    private String username;
    private String password;


    public User() {

    }

}
