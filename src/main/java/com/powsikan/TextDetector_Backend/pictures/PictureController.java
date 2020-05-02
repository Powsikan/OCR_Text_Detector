package com.powsikan.TextDetector_Backend.pictures;


import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping()
public class PictureController {
    @Autowired
    private PictureService pictureService;


    @GetMapping("/picture")
    public List<Picture> getImages() {
        return pictureService.getImages();
    }
    @GetMapping("/picture/{id}")
    public Optional<Picture> getImage(Integer id) {
        return pictureService.getImage(id);
    }

    @GetMapping("picture/{username}/all")
    public List<Picture> getImagesByUsername(@PathVariable String username) {
        return pictureService.getImagesByUsername(username);
    }

    @SneakyThrows
    @GetMapping("/uploads/{filename}")
    public Resource getImage(@PathVariable String filename) {
        return pictureService.loadFileAsResource(filename);
    }

    @PostMapping("picture/{username}")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String username) throws IOException {
        return pictureService.uploadFile(file, username);
    }
}
