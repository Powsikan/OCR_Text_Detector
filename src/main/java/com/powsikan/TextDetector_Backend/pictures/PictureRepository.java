package com.powsikan.TextDetector_Backend.pictures;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture,Integer> {
//    String findImagesByUsername(String username);
}
