package com.hfl.model;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="search_information")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SearchInformation {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false) private String name;
    private String description;
    private String value;
    private Boolean active = true;
}
