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
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_estado")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "O nome do estado não pode ser nulo")
    private String nome;

    @NotNull(message = "A sigla do estado não pode ser nulo")
    @Size(min = 2, max = 2, message = "A sigla precisa ter 2 caracteres")
    private String sigla;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;
}
