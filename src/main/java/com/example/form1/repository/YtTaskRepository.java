package com.example.form1.repository;

import com.example.form1.entity.YtTask;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * Interface de manipulation de la l'entité task
 */
public interface YtTaskRepository extends JpaRepository<YtTask, String> {
}
