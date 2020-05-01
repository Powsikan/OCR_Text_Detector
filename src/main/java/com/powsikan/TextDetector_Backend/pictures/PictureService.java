package com.powsikan.TextDetector_Backend.pictures;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PictureService {

//    @Autowired
//    private PictureRepository pictureRepository;
//
// public List<Picture> getImages(String username){
//     List<Picture> list =new ArrayList<>();
//    return pictureRepository.findAll();
// }
//
// public Optional<Picture> getImage( Integer id){
//     return pictureRepository.findById(id);
// }

  public  void uploadFile(MultipartFile file) throws IOException {
     file.transferTo(new File("../uploads/"));

  }
}
