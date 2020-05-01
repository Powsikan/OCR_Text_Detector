package com.powsikan.TextDetector_Backend.pictures;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture,Integer> {
  Picture findByUser_Username(String username);
}
