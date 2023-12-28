package com.example.form1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class YtCategoryDto {

    private Long id;
    @NotBlank
    //@Email
    @Size(min = 2 , max = 75)
    private String name;


}
