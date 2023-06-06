package com.dev.backend.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_marca")
public class Marca {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "A marca precisa ter nome")
    private String nome;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;
}
