package com.example.form1.repository;

import com.example.form1.entity.YtCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * interface de manipulation des donnees de l'entite YtCategory
 */
public interface YtCategoryRepository extends JpaRepository<YtCategory, Long> {

    /**
     *  Recherche par le nom avec la case et sans la complesion
     * @param nom
     * @return Liste de catégorie
     */
    List<YtCategory> findByName(String nom);

    /**
     * Recherche par le nom sans tenir compte de la case et sans la completion
     * @param nom
     * @return Liste de catégorie
     */
    List<YtCategory> findByNameIgnoreCase(String nom); //recherche sans la case

    /**
     * Recherche par le nom sans tenir compte de la case et avec la completion
     * @param nom
     * @return Liste de catégorie
     */
    List<YtCategory> findByNameContainingIgnoreCase(String nom); //recherche sans la case

    /**
     * Recherche par le nom sans tenir compte de la case et avec la completion en ordonnant par le nom (descendant)
     * @param nom
     * @return Liste de catégorie
     */
    List<YtCategory> findByNameContainingIgnoreCaseOrderByNameDesc(String nom); //recherche sans la case

    /**
     *
     *
     * @return Liste de catégorie ordonner par le nom par ordre décroissant
     */
    List<YtCategory> findAllByOrderByNameDesc();

    /**
     * Verification de l' existence d'une catégorie par le nom
     * @param nom
     * @return Retourne un boolean
     */
    boolean existsByNameIgnoreCase(String nom);

}
