package com.hfl.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Table(name="news")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class News {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false) private String title;
    @Column(length=15000, nullable=false) private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @PrePersist void prePersist(){createdAt=LocalDateTime.now();updatedAt=createdAt;}
    @PreUpdate void preUpdate(){updatedAt=LocalDateTime.now();}
}
