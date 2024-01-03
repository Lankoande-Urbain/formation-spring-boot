package com.example.form1.api;

import com.example.form1.dto.YtCategoryDto;
import com.example.form1.entity.YtCategory;
import com.example.form1.repository.YtCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Profile("dev")
@Service
public class DevCategorieAdaptater implements CategorieInterface {


    public List<YtCategoryDto> fetchCategory(final String nom) {

        return Arrays.asList(new YtCategoryDto(1L, "Domicile"));
        }

    }
