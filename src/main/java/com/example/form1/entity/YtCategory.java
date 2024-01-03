package com.example.form1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "yt_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class YtCategory {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "nom", length = 100)
    private String name;
}
