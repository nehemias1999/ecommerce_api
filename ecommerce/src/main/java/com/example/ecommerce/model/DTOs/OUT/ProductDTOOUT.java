package com.example.ecommerce.model.DTOs.OUT;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTOOUT {

    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private Set<CategoryDTOOUT> categories = new HashSet<>();

}