package com.powsikan.TextDetector_Backend.pictures;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private  String name;
    private  String imageUrl;
    private  String detected_text;

    public Picture(){}
}
