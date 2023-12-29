package com.example.form1.entity;

import com.example.form1.dto.EStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "yt_task")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class YtTask {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "status")
    private EStatus status;
}
