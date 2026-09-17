package com.hfl.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Table(name="posts")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Post {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false) private String title;
    @Column(length=10000, nullable=false) private String content;
    private String lostPersonName;
    private String lostCircumstance;
    private Integer lostYear;
    private Integer lostAge;
    private String objectInformation;
    private String photoUrl;
    private String status = "PUBLISHED";
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist void prePersist(){ createdAt=LocalDateTime.now(); updatedAt=createdAt; }
    @PreUpdate void preUpdate(){ updatedAt=LocalDateTime.now(); }
}
