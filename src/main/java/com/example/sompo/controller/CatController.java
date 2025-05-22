package com.example.sompo.controller;

import com.example.sompo.entity.Cat;
import com.example.sompo.repository.CatRepository;
import com.example.sompo.response.ApiResponse;
import com.example.sompo.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/cats")
public class CatController {

    private final CatRepository catRepository;
    private final StorageService storageService;
    public CatController(CatRepository repository, StorageService storageService) {
        this.catRepository = repository;
        this.storageService = storageService;
    }

    @GetMapping
    @CrossOrigin
    public ApiResponse<Map<String, Object>> getAllCats(int page,int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Cat> catPage = catRepository.findAll(pageable);
            Map<String, Object> response = new HashMap<>();
            response.put("cats", catPage.getContent());
            response.put("currentPage", catPage.getNumber());
            response.put("totalItems", catPage.getTotalElements());
            response.put("totalPages", catPage.getTotalPages());
            return new ApiResponse<>(response, 200, "success");
        } catch (Exception e) {
            return new ApiResponse<>(null,500,"Internal Server Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    @CrossOrigin
    public ApiResponse<String> deleteCatById(@PathVariable String id) {
        try {
            catRepository.deleteById(id);
            return new ApiResponse<>("Cat deleted successfully", 200, "success");
        } catch (Exception e) {
            return new ApiResponse<>(null, 500, "Internal Server Error: " + e.getMessage());
        }
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @CrossOrigin
    public ApiResponse<Cat> uploadCats(@RequestPart("image") MultipartFile imageFile) {
        try {

            String filename = imageFile.getOriginalFilename().replaceFirst("[.][^.]+$", "");
            String filePath = storageService.storeFile(imageFile,filename);
            BufferedImage image = ImageIO.read(imageFile.getInputStream());
            int width = image != null ? image.getWidth() : 0;
            int height = image != null ? image.getHeight() : 0;
            Cat cat = new Cat(filename, filePath, width, height);
            Cat savedCat = catRepository.save(cat);

            return new ApiResponse<>(savedCat, 200, "Cat with image uploaded successfully");
        } catch (IOException e) {
            return new ApiResponse<>(null, 500, "File storage error: " + e.getMessage());
        } catch (Exception e) {
            return new ApiResponse<>(null, 500, "Internal Server Error: " + e.getMessage());
        }
    }

    @PostMapping("/insert")
    @CrossOrigin
    public ApiResponse<Cat> addCat(@RequestBody Cat catResponse) {
        try {
            Cat savedCat = catRepository.save(catResponse);
            return new ApiResponse<>(savedCat, 200, "Cat with image uploaded successfully");
        } catch (Exception e) {
            return new ApiResponse<>(null, 500, "Internal Server Error: " + e.getMessage());
        }
    }
}


