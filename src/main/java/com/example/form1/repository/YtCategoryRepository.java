package com.example.form1.repository;

import com.example.form1.entity.YtCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * interface de manipulation des donnees de l'entite YtCategory
 */
public interface YtCategoryRepository extends JpaRepository<YtCategory, Long> {
}
