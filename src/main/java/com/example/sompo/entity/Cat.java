package com.example.sompo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor // ← Adds default constructor
@AllArgsConstructor // ← Optional: Adds constructor with all fields
@Table(name = "cat")
public class Cat {
    @Id
    @JsonProperty("id")
    private String id;
    @JsonProperty("width")
    private Integer width;
    @JsonProperty("height")
    private Integer height;
    @JsonProperty("url")
    private String url;
    // No-arg constructor (required by JPA)
    public Cat() {}
    public Cat(String filename, String filePath, int width, int height) {
        this.id=filename;
        this.width=width;
        this.height=height;
        this.url=filePath;;
    }
}
