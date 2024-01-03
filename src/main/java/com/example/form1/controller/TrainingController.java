package com.example.form1.controller;

import com.example.form1.dto.ETypeOperation;
import com.example.form1.dto.YtCategoryDto;
import com.example.form1.dto.YtTaskDto;
import com.example.form1.entity.YtCategory;
import com.example.form1.entity.YtTask;
import com.example.form1.repository.YtCategoryRepository;
import com.example.form1.repository.YtTaskRepository;
import com.example.form1.service.TrainingService;
import com.example.form1.utils.YtConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TrainingController {


    private final YtCategoryRepository categoryRepository;
    private final YtTaskRepository taskRepository;
    private final TrainingService trainingService;


    /**
     * @return message de bienvenue
     */
    @GetMapping("/") //ecoute GET sur le /
    public String home() {
        return "\nBienvenue sur le REST de PRINCE.COM";

    }

    /**
     * Construre une phrase de salutation
     *
     * @param name le nom
     * @return la salutation avec le nom
     */
    @GetMapping("/greeting/{name}")
    public String greeting(@PathVariable String name) {
        return "Bonjour " + name + " ";
    }

    /**
     * Fonction d' operation de deux nombres
     *
     * @param type
     * @param num1
     * @param num2
     * @return une operation être num1 et num2 en fonction du choix
     */
    @GetMapping("/operation/{type}")
    public String operation(
            @PathVariable(name = "type") ETypeOperation type,
            @RequestParam(name = "num1", defaultValue = "0") int num1,
            @RequestParam(name = "num2", defaultValue = "0") int num2) {
        if (type == ETypeOperation.MULTIPLY) {
            int result = num1 * num2;
            return "Le Produit de " + num1 + " et " + num2 + " est " + result;
        } else if (type == ETypeOperation.ADD) {
            int result = num1 + num2;
            return "L'addition de " + num1 + " et " + num2 + " est " + result;
        } else if (type == ETypeOperation.SUBSTRUCT) {
            int result = num1 - num2;
            return "La soustraction de " + num1 + " et " + num2 + " est " + result;
        } else if (type == ETypeOperation.DIVIDE) {
            int result = num1 / num2;
            return "La division de " + num1 + " et " + num2 + " est " + result;
        } else {
            return "";
        }

        // request "http://localhost:8080/operation?num1=12&num2=12"
    }

    /**
     * requête qui renvoie les catégorie & requête de recherche de catégorie par le nom
     *
     * @param nom
     * @return
     */

    // Code des catégories
    @GetMapping(YtConstants.URLS.CATEGORY)
    public List<YtCategoryDto> fetchCategory(
            @RequestParam(name = "nom", required = false) String nom) {
        final List<YtCategoryDto> respServ = trainingService.fetchCategory(nom);
        return respServ;
    }

    /**
     * enregistrement des données dans la base de données
     *
     * @param catDto
     * @return entité construit a partir du DTO
     */
    @PostMapping(YtConstants.URLS.CATEGORY)
    public ResponseEntity<YtCategoryDto> postCategory(
            @RequestBody @Valid YtCategoryDto catDto) throws Exception {
        final YtCategoryDto respServ = this.trainingService.postCategory(catDto);

        log.info("Saving a catégorie : {}", catDto.getName().toUpperCase());
        //Retouner l'objet avec un code sattus
        return new ResponseEntity<>(respServ, HttpStatus.CREATED);
    }


    /**
     * @param idcategorie
     * @param catDto
     * @return entité construit a partir du DTO
     */
    @PutMapping(YtConstants.URLS.CATEGORY + "/{id}")
    public ResponseEntity<YtCategoryDto> updateCategory(@PathVariable("id") Long idcategorie,
                                                        @RequestBody @Valid YtCategoryDto catDto) {


        log.info("Update catégorie : {}", idcategorie);
        final YtCategoryDto respServ = this.trainingService.updateCategory(idcategorie, catDto);

        return new ResponseEntity<>(respServ, HttpStatus.OK);
    }

    /**
     * Suppression de donnees
     *
     * @param idcategorie
     * @return entité construit a partir du DTO
     */
    @DeleteMapping(YtConstants.URLS.CATEGORY + "/{id}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable("id") Long idcategorie) {

        log.warn("Delete a catégorie : {}", idcategorie);
        trainingService.deleteCategory(idcategorie);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    //tasck


    @GetMapping(YtConstants.URLS.TASK)
    public List<YtTaskDto> fetchTask() {
        final List<YtTaskDto> respServ = trainingService.fetchTasks();
        return respServ;
    }

    @PostMapping(YtConstants.URLS.TASK)
    public ResponseEntity<YtTaskDto> postTask(
            @RequestBody @Valid YtTaskDto taskDto)  {
        final YtTaskDto respServ = this.trainingService.postTask(taskDto);

        log.info("Saving a catégorie : {}", taskDto.getTitle().toUpperCase());
        //Retouner l'objet avec un code sattus
        return new ResponseEntity<>(respServ, HttpStatus.CREATED);
    }
}
