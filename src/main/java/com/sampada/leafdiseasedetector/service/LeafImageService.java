package com.sampada.leafdiseasedetector.service;

import com.sampada.leafdiseasedetector.entity.LeafImage;
import com.sampada.leafdiseasedetector.repository.LeafImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LeafImageService {

    private final LeafImageRepository leafImageRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public LeafImage saveLeafImage(MultipartFile file) throws IOException {
        Path folder = Paths.get(uploadDir);
        Files.createDirectories(folder);

        String imageName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = folder.resolve(imageName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        LeafImage leafImage = new LeafImage();
        leafImage.setImageName(imageName);
        leafImage.setImagePath(filePath.toString());
        return leafImageRepository.save(leafImage);
    }

    public List<LeafImage> getAllLeafImages() {
        return leafImageRepository.findAll();
    }

    public LeafImage getLeafImageById(Long id) {
        return leafImageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leaf image not found with id: " + id));
    }
}