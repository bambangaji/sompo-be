package com.example.sompo.request;
import com.example.sompo.entity.Cat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CatRequest {
    @jakarta.validation.Valid
    private Cat cat;

    @NotBlank
    private String additionalInfo;

}