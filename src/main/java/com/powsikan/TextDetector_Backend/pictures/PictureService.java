package com.powsikan.TextDetector_Backend.pictures;


import com.powsikan.TextDetector_Backend.users.User;
import com.powsikan.TextDetector_Backend.users.UserRepository;
import lombok.SneakyThrows;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PictureService {
   private Path fileStorageLocation = Paths.get("uploads")
            .toAbsolutePath().normalize();

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PictureRepository pictureRepository;


    public List<Picture> getImages() {
        List<Picture> list = new ArrayList<>();
        for (Picture picture : pictureRepository.findAll()) {
            list.add(picture);
        }
        return list;
    }

    public Optional<Picture> getImage(Integer id) {
        return pictureRepository.findById(id);
    }

    public List<Picture> getImagesByUsername(String username) {
        List<Picture> list = new ArrayList<>();
        for (Picture picture : pictureRepository.findPictureByUsername(username)) {
            list.add(picture);
        }
        return list;
    }

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

        File convFile = convert(file);
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("/usr/share/tesseract-ocr/4.00/tessdata/");
        String text = tesseract.doOCR(convFile);

        Picture picture = new Picture();
        picture.setName(fileName);
        picture.setImageUrl(fileDownloadUri);
        User user = userRepository.findByUsername(username);

        picture.setUser(user);
        picture.setDetected_text(text);
        pictureRepository.save(picture);

        return ResponseEntity.ok(fileDownloadUri);
    }


    public static File convert(MultipartFile file) throws IOException {
        File convFile = new File("uploads/", file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    public Resource loadFileAsResource(String fileName) throws Exception {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new Exception("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new Exception("File not found " + fileName, ex);
        }
    }


}

