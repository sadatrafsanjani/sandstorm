package com.rafsanjani.sandstorm.model;

import lombok.*;
import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "resources")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Resource {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "device_id")
    private Device device;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "application_id")
    private Application application;

    @Column(name = "camera")
    private Boolean camera;

    @Column(name = "microphone")
    private Boolean microphone;

    @Column(name = "gps")
    private Boolean gps;

    @Column(name = "contact")
    private Boolean contact;

    @Column(name = "sms")
    private Boolean sms;

    @Column(name = "memory")
    private Boolean memory;

    @Column(name = "recorded_at")
    private Instant recordedAt;
}
