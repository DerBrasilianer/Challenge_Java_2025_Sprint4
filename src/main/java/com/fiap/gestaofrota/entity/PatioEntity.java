package com.fiap.gestaofrota.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CH_TB_PATIO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O endereço é obrigatório")
    private String endereco;

    @NotNull(message = "O telefone é obrigatório")
    private Integer telefone;

    @OneToMany(mappedBy = "patio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MotoEntity> motos = new ArrayList<>();

}
