package com.ohgiraffers.springdatajpa.menu.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class CategoryDto {

    private int categoryCode;
    private String categoryName;
    private Integer refCategoryCode;
}
