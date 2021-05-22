package com.rafsanjani.sandstorm.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "devices")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Device {

    @Id
    @Column(name = "id")
    private String id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "mac")
    private String mac;

    @Column(name = "android_version")
    private String androidVersion;
}