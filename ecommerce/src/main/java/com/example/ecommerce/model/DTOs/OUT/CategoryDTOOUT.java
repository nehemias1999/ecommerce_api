package com.example.ecommerce.model.DTOs.OUT;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CategoryDTOOUT {

    private UUID id;
    private String name;
    private String description;

}