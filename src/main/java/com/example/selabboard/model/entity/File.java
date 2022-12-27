package com.example.selabboard.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Table(name = "file")
@Entity
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "originalName")
    private String originalName;

    @Column(name = "saveName")
    private String savedName;

    @Column(name = "savedPath")
    private String savedPath;

    @Builder
    public File(Long id, String originalName, String savedName, String savedPath) {
        this.id = id;
        this.originalName = originalName;
        this.savedName = savedName;
        this.savedPath = savedPath;
    }
}
