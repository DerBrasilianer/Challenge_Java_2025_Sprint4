package com.fiap.gestaofrota.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CH_TB_TELEMETRIA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(name = "telemetria_seq", sequenceName = "SEQ_TELEMETRIA", allocationSize = 1)
public class TelemetriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "telemetria_seq")
    private Long id;

    @Column(name = "timestamp_raw")
    private String timestamp;

    @Column
    private Double distancia;

    @Column
    private String estado;

    @Column
    private Boolean busca;

    @Column
    private String dispositivo;

}