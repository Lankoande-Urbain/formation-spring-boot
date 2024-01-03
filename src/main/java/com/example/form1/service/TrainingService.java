package com.example.form1.service;

import com.example.form1.api.CategorieInterface;
import com.example.form1.config.YtMapper;
import com.example.form1.dto.YtCategoryDto;
import com.example.form1.dto.YtTaskDto;
import com.example.form1.entity.YtCategory;
import com.example.form1.entity.YtTask;
import com.example.form1.repository.YtCategoryRepository;
import com.example.form1.repository.YtTaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
@Slf4j
public class TrainingService {

    private final YtCategoryRepository categoryRepository;
    private final YtTaskRepository taskRepository;

    private final CategorieInterface categorieInterface;
    private final YtMapper mapper = Mappers.getMapper(YtMapper.class);

    public List<YtCategoryDto> fetchCategory(final String nom) {

        return categorieInterface.fetchCategory(nom);
    }


    public YtCategoryDto postCategory(final YtCategoryDto catDto)  {
        Long nouveauId = new Random().nextLong();
        catDto.setId(nouveauId);
        // Procéder a l' enregistrement dans la base de basse en construisant l' entité a partir du DTO

        final YtCategory entity = mapper.maps(catDto);

        //Check des doublons avant enregistrement
        boolean exists = this.categoryRepository.existsByNameIgnoreCase(catDto.getName());
        if (exists) {
            //Empêcher l' enregistrement de la catégorie existence

            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "La catégorie existe deja !");

        }
        //sauvegarde de la catégorie dans la base de donnees
        this.categoryRepository.save(entity);

        // Retourner l' objet avec un identifiant
        return catDto;


    }

    public YtCategoryDto updateCategory( final Long idcategorie, final YtCategoryDto catDto) {
        final YtCategory catById = this.categoryRepository.getReferenceById(idcategorie);

        //Procedure de la modification
        catById.setName(catDto.getName());

        //Retourner l'objet avec un identifiant
        this.categoryRepository.save(catById);

        return catDto;
    }

    public void deleteCategory(final Long idcategorie) {

        //Procedure de suppression
        this.categoryRepository.deleteById(idcategorie);

    }

                // Tasks
    public List<YtTaskDto> fetchTasks() {
        final List<YtTask> resp = this.taskRepository.findAll();

        //
        List<YtTaskDto> listDto = new ArrayList<>(); //conversion de la liste retournant l'entité YtCategory en DTO
        resp.forEach(e -> {
            YtTaskDto d =  mapper.maps(e);
            listDto.add(d);
        });
        return listDto;
    }

    public YtTaskDto postTask(final YtTaskDto taskDto)  {
        Long nouveauId = new Random().nextLong();
        taskDto.setId(nouveauId);
        // Procéder a l' enregistrement dans la base de basse en construisant l' entité a partir du DTO

        final YtTask entity = mapper.maps(taskDto);



        //sauvegarde de la catégorie dans la base de donnees
        this.taskRepository.save(entity);

        // Retourner l' objet avec un identifiant
        return taskDto;


    }
}
