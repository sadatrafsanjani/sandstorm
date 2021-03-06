package com.rafsanjani.sandstorm.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "age_id")
    private AgeGroup ageGroup;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "area_id")
    private Area area;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "education_id")
    private Education education;

    @Column(name = "gender")
    private String gender;
}
