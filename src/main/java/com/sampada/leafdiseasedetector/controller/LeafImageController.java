package com.sampada.leafdiseasedetector.controller;

import com.sampada.leafdiseasedetector.entity.LeafImage;
import com.sampada.leafdiseasedetector.service.LeafImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/leaf-images")
@RequiredArgsConstructor
public class LeafImageController {

    private final LeafImageService leafImageService;

    @PostMapping("/upload")
    public LeafImage uploadLeafImage(@RequestParam("file") MultipartFile file) throws IOException {
        return leafImageService.saveLeafImage(file);
    }

    @GetMapping
    public List<LeafImage> getAllLeafImages() {
        return leafImageService.getAllLeafImages();
    }

    @GetMapping("/{id}")
    public LeafImage getLeafImageById(@PathVariable Long id) {
        return leafImageService.getLeafImageById(id);
    }
}