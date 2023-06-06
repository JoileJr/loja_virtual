package com.dev.backend.entity;

import java.util.Date;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_cidade")
public class Cidade {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "A cidade precisa ter nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "idEstado")
    @NotNull(message = "A cidade precisa de um estado")
    private Estado estado;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

}
