package com.rafsanjani.sandstorm.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "applications")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Application {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "package_name")
    private String packages;

    @Column(name = "apk_version")
    private String apkVersion;

}
