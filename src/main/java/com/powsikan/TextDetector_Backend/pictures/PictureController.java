package com.powsikan.TextDetector_Backend.pictures;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("picture")
public class PictureController {
    @Autowired
    private PictureService pictureService;


    @GetMapping()
    public List<Picture> getImages() {
        return pictureService.getImages();
    }

    @GetMapping("/{username}/all")
    public List<Picture> getImagesByUsername(@PathVariable String username) {
        return pictureService.getImagesByUsername(username);
    }

    @GetMapping("/{id}")
    public Optional<Picture> getImage(@PathVariable Integer id) {
        return pictureService.getImage(id);
    }

    @PostMapping("/{username}")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String username) throws IOException {
        return pictureService.uploadFile(file, username);
    }
}
