package com.example.form1.controller;

import com.example.form1.dto.ETypeOperation;
import com.example.form1.dto.YtCategoryDto;
import com.example.form1.entity.YtCategory;
import com.example.form1.repository.YtCategoryRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Slf4j

@RestController
public class TrainingController {

    @Autowired
    private YtCategoryRepository categoryRepository;

    /**
     * @return message de bienvenue
     */
    @GetMapping("/") //ecoute GET sur le /
    public String home() {
        return "Bienvenue sur mon Application !!";
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
     * @return une operation etre num1 et num2 en fonction du choix
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
     *
     * @return
     */
    @GetMapping("/categories")
    public List<YtCategory> fetchCategories() {
       final  List<YtCategory> list = this.categoryRepository.findAll();
       return list;
    }

    /**
     * enregistrement des données dans la base de données
     * @param catDto
     * @return  entité construit a partir du DTO
     */
    @PostMapping("/categories")
    public ResponseEntity<YtCategoryDto> postCategory(
            @RequestBody @Valid YtCategoryDto catDto) {
        log.info("Saving a Categorie : {}", catDto.getName().toUpperCase());
        Long nouveauId = new Random().nextLong();
        catDto.setId(nouveauId);
        // Proceder a l'enregistrement dans la base de basse en construidant l'entite a partir du DTO

        final YtCategory entity = new YtCategory();
        entity.setId(catDto.getId());
        entity.setNom(catDto.getName());

        this.categoryRepository.save(entity);

        // Retouner l'objet avec un identifiant
        return new ResponseEntity<>(catDto, HttpStatus.CREATED);
    }

    /**
     *
     * @param idCategorie
     * @param catDto
     * @return  entité construit a partir du DTO
     */
    @PutMapping("/categories/{id}")
    public ResponseEntity<YtCategoryDto> putCategory( @PathVariable("id") Long idCategorie,
            @RequestBody @Valid YtCategoryDto catDto) {
        log.info("Update Categorie : {}", idCategorie);
        final YtCategory catById = this.categoryRepository.getReferenceById(idCategorie);

        //Procedure de la modification
        catById.setNom(catDto.getName());

        //Retourner l'objet avec un identifiant
        this.categoryRepository.save(catById);

        return new ResponseEntity<>(catDto, HttpStatus.OK);
    }

    /**
     *  Suppression de donnees
     * @param idCategorie
     * @return  entité construit a partir du DTO
     */
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> putCategory(
            @PathVariable("id") Long idCategorie) {
        log.warn("Delete a Categorie : {}", idCategorie);

        //Procedure de suppresion
        this.categoryRepository.deleteById(idCategorie);

        return new ResponseEntity<>( HttpStatus.OK);
    }
}
