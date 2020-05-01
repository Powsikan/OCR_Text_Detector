package com.powsikan.TextDetector_Backend.pictures;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("picture")
public class PictureController {
    @Autowired
    private PictureService pictureService;

    @PostMapping()
    public void uploadFile(@RequestParam("file")MultipartFile file ) throws IOException {
        pictureService.uploadFile(file);
    }
}
