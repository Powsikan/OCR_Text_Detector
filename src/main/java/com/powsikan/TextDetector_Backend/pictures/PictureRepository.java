package com.powsikan.TextDetector_Backend.pictures;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PictureRepository extends JpaRepository<Picture, Integer> {
    @Query(value = "SELECT p.*  FROM picture p WHERE user_username =:username", nativeQuery = true)
    List<Picture> findPictureByUsername(String username);


//    List<Picture> findByName(String filename);
}
