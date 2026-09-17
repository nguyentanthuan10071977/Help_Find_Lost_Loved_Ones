package com.hfl.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Table(name="video_media")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class VideoMedia {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false) private String title;
    @Column(length=10000) private String description;
    @Column(nullable=false) private String videoUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @PrePersist void prePersist(){createdAt=LocalDateTime.now();updatedAt=createdAt;}
    @PreUpdate void preUpdate(){updatedAt=LocalDateTime.now();}
}
