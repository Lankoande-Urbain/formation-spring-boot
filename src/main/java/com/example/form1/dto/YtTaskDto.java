package com.example.form1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter // generateur de getter
@Setter // generateur de setter
@NoArgsConstructor // generateur de constructeur sans argument
@AllArgsConstructor // generateur de constructeur avec tout les  arguments
public class YtTaskDto {
    private Long id;
    private String title;
    private String description;
    private EStatus status;


}
