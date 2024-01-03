package com.example.form1.api;

import com.example.form1.dto.YtCategoryDto;
import com.example.form1.entity.YtCategory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategorieInterface {

    List<YtCategoryDto> fetchCategory(final String nom);

}
