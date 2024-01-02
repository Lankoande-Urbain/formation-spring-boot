package com.example.form1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter // générateur de getter
@Setter // générateur de setter
@NoArgsConstructor // générateur de constructeur sans argument
@AllArgsConstructor // générateur de constructeur avec tout les  arguments

public class YtTaskDto {
    private Long id;
    private String title;
    private String description;
    private EStatus status;

}
