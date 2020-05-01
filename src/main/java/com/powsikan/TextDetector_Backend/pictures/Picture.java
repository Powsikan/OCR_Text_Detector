package com.powsikan.TextDetector_Backend.pictures;


import com.powsikan.TextDetector_Backend.users.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private  String name;
    private  String imageUrl;
    private  String detected_text;

    @ManyToOne
    private User user;

    public Picture(){}
}
