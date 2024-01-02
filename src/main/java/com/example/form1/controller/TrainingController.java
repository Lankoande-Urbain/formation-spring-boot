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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    @GetMapping("/categories")
    public List<YtCategory> fetchcatégories(
            @RequestParam(name = "nom", required = false) String nom
    ) {
        if (!StringUtils.hasText(nom)) { // si le paramètre de recherche est vide renvoyer tout les catégories ordonner par ordre décroissant
            final List<YtCategory> list = this.categoryRepository.findAllByOrderByNomDesc();
            return list;
        } else { // Recherche selon la paramètre rentrer
            List<YtCategory> list = this.categoryRepository.findByNomContainingIgnoreCaseOrderByNomDesc(nom);
            return list;
        }

    }

    /**
     * enregistrement des données dans la base de données
     *
     * @param catDto
     * @return entité construit a partir du DTO
     */
    @PostMapping("/catégories")
    public ResponseEntity<YtCategoryDto> postCategory(
            @RequestBody @Valid YtCategoryDto catDto)  throws Exception {
        log.info("Saving a catégorie : {}", catDto.getName().toUpperCase());
        Long nouveauId = new Random().nextLong();
        catDto.setId(nouveauId);
        // Procéder a l' enregistrement dans la base de basse en construisant l' entité a partir du DTO

        final YtCategory entity = new YtCategory();
        entity.setId(catDto.getId());
        entity.setNom(catDto.getName());

        //Check des doublons avant enregistrement
        boolean exists = this.categoryRepository.existsByNomIgnoreCase(catDto.getName());
        if (exists) {
            //Empêcher l' enregistrement de la catégorie existence

            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "La catégorie existe deja !");

        }
        //sauvegarde de la catégorie dans la base de donnees
        this.categoryRepository.save(entity);

        // Retourner l' objet avec un identifiant
        return new ResponseEntity<>(catDto, HttpStatus.CREATED);


    }

    /**
     * @param idcategorie
     * @param catDto
     * @return entité construit a partir du DTO
     */
    @PutMapping("/catégories/{id}")
    public ResponseEntity<YtCategoryDto> putCategory(@PathVariable("id") Long idcategorie,
                                                     @RequestBody @Valid YtCategoryDto catDto) {
        log.info("Update catégorie : {}", idcategorie);
        final YtCategory catById = this.categoryRepository.getReferenceById(idcategorie);

        //Procedure de la modification
        catById.setNom(catDto.getName());

        //Retourner l'objet avec un identifiant
        this.categoryRepository.save(catById);

        return new ResponseEntity<>(catDto, HttpStatus.OK);
    }

    /**
     * Suppression de donnees
     *
     * @param idcategorie
     * @return entité construit a partir du DTO
     */
    @DeleteMapping("/catégories/{id}")
    public ResponseEntity<Void> putCategory(
            @PathVariable("id") Long idcategorie) {
        log.warn("Delete a catégorie : {}", idcategorie);

        //Procedure de suppression
        this.categoryRepository.deleteById(idcategorie);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
