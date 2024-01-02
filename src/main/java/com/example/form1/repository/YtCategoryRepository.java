package com.example.form1.repository;

import com.example.form1.entity.YtCategory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * interface de manipulation des donnees de l'entite YtCategory
 */
public interface YtCategoryRepository extends JpaRepository<YtCategory, Long> {

    /**
     *  Recherche par le nom avec la case et sans la complesion
     * @param nom
     * @return Liste de categorie
     */
    List<YtCategory> findByNom(String nom);

    /**
     * Recherche par le nom sans tenir compte de la case et sans la complesion
     * @param nom
     * @return Liste de categorie
     */
    List<YtCategory> findByNomIgnoreCase(String nom); //recherche sans la case

    /**
     * Recherche par le nom sans tenir compte de la case et avec la complesion
     * @param nom
     * @return Liste de categorie
     */
    List<YtCategory> findByNomContainingIgnoreCase(String nom); //recherche sans la case

    /**
     * Recherche par le nom sans tenir compte de la case et avec la completion en ordonnant par le nom (descendant)
     * @param nom
     * @return Liste de catégorie
     */
    List<YtCategory> findByNomContainingIgnoreCaseOrderByNomDesc(String nom); //recherche sans la case

    /**
     *
     *
     * @return Liste de catégorie ordonner par le nom par ordre décroissant
     */
    List<YtCategory> findAllByOrderByNomDesc();

    /**
     * Verification de l' existence d'une catégorie par le nom
     * @param nom
     * @return Retourne un boolean
     */
    boolean existsByNomIgnoreCase(String nom);

}
