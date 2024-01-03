package com.example.form1.api;

import com.example.form1.config.YtMapper;
import com.example.form1.dto.YtCategoryDto;
import com.example.form1.entity.YtCategory;
import com.example.form1.repository.YtCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Profile({"prod","default"})
@Service
public class ProdCategorieAdaptater implements CategorieInterface {

    private final YtCategoryRepository categoryRepository;

    private final YtMapper mapper = Mappers.getMapper(YtMapper.class);
    public List<YtCategoryDto> fetchCategory(final String nom) {
        if (!StringUtils.hasText(nom)) { // si le paramètre de recherche est vide renvoyer tout les catégories ordonner par ordre décroissant
            final List<YtCategory> list = this.categoryRepository.findAllByOrderByNameDesc();

            List<YtCategoryDto> listDto = new ArrayList<>(); //conversion de la liste retournant l'entité YtCategory en DTO
            list.forEach(e -> {
                YtCategoryDto d =  mapper.maps(e);
                listDto.add(d);
            });

            return listDto;

        } else { // Recherche selon la paramètre rentrer
            List<YtCategory> list = this.categoryRepository.findByNameContainingIgnoreCaseOrderByNameDesc(nom);


            List<YtCategoryDto> listDto = new ArrayList<>(); //conversion de la liste retournant l'entité YtCategory en DTO
            list.forEach(e -> {
                YtCategoryDto d =   mapper.maps(e);
                listDto.add(d);
            });

            return listDto;
        }


    }
}
