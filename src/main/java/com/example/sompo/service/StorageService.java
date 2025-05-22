package com.example.sompo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
@Service
public class StorageService {
    private final Path rootLocation = Paths.get("uploads");
    public String storeFile(MultipartFile file,String filename) throws IOException {
        // Create upload directory if it doesn't exist
        if (!Files.exists(rootLocation)) {
            Files.createDirectories(rootLocation);
        }

        Path destinationFile = rootLocation.resolve(filename);

        // Save file
        Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);

        return "http://localhost:8082/"+destinationFile.toString(); // Returns absolute path like "C:/projects/your-app/uploads/filename.jpg"
    }
}
