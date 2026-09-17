package com.hfl.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Table(name="find_relative_requests")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class FindRelativeRequest {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private String requesterName;
    private String email;
    private String phone;
    private String address;
    private String relativeName;
    private String relationship;
    private String lostCircumstance;
    private Integer lostYear;
    private Integer lostAge;
    @Column(length=10000) private String description;
    private String status = "PENDING";
    private LocalDateTime createdAt;
    @PrePersist void prePersist(){createdAt=LocalDateTime.now();}
}
