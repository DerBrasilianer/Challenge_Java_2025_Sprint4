package com.fiap.gestaofrota.entity;

import com.fiap.gestaofrota.enums.Marcas;
import com.fiap.gestaofrota.enums.Modelos;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "CH_TB_MOTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "A marca é obrigatória")
    @Enumerated(EnumType.STRING)
    private Marcas marca;

    @NotNull(message = "O modelo é obrigatório")
    @Enumerated(EnumType.STRING)
    private Modelos modelo;

    @NotBlank(message = "A placa é obrigatória")
    @Column(nullable = false, unique = true)
    private String placa;

    @NotNull(message = "O ano é obrigatório")
    private Integer ano;

    private Long numeroIot;

    @ManyToOne
    @JoinColumn(name = "PATIO_ID", nullable = false)
    private PatioEntity patio;

}
