package com.powsikan.TextDetector_Backend.pictures;


import com.powsikan.TextDetector_Backend.users.User;
import com.powsikan.TextDetector_Backend.users.UserRepository;
import lombok.SneakyThrows;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PictureService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PictureRepository pictureRepository;

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

    @SneakyThrows
    public ResponseEntity uploadFile(MultipartFile file, String username) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path path = Paths.get("uploads/" + fileName);
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("uploads/")
                .path(fileName)
                .toUriString();


        Tesseract tesseract = new Tesseract();
        String text = tesseract.doOCR((File) file);

        Picture picture = new Picture();
        picture.setName(fileName);
        picture.setImageUrl(fileDownloadUri);
        User user = userRepository.findByUsername(username);

        picture.setUser(user);
        picture.setDetected_text(text);
        pictureRepository.save(picture);

        return ResponseEntity.ok(fileDownloadUri);
    }
}

