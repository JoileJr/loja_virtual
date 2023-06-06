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
@Table(name = "tb_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "descrição curta é necessária")
    private String descricaoCurta;
    @NotNull(message = "descrição detalhada é necessária")
    private String descricaoDetalhada;
    @NotNull(message = "valor de custo é necessário")
    private Double valorCusto;
    @NotNull(message = "valor de venda é necessário")
    private Double valorVenda;

    @ManyToOne
    @JoinColumn(name = "idMarca")
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;
}
