package com.powsikan.TextDetector_Backend.pictures;


import lombok.SneakyThrows;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping()
public class PictureController {

    private static final Logger logger = LoggerFactory.getLogger(PictureController.class);

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
    public ResponseEntity<Resource> getImageResource(@PathVariable String filename, HttpServletRequest request) {

        Resource resource = pictureService.loadFileAsResource(filename);
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);

    }

    @PostMapping("picture/{username}")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String username) throws IOException {
        return pictureService.uploadFile(file, username);
    }

    @DeleteMapping("picture/delete/{id}")
    public void delete(@PathVariable Integer id) {
        pictureService.deletePicture(id);
    }
}
