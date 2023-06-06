package com.dev.backend.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
@Table(name = "tb_pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "O nome não pode ser nulo")
    private String nome;

    @CPF(message = "O CPF deve ser valido")
    @NotNull(message = "O cpf não pode ser nulo")
    private String cpf;

    @Email(message = "O email deve ser um endereço valido")
    @NotNull(message = "O email não pode ser nulo")
    private String email;

    @NotNull(message = "A senha não pode ser nula")
    @Size(min = 8, max = 20, message = "A senha deve ter entre 8 - 20 caracteres")
    private String senha;
    private String endereco;

    private String cep;

    @ManyToOne
    @JoinColumn(name = "idCidade")
    private Cidade cidade;

    @OneToMany(mappedBy = "pessoa", orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Setter(value = AccessLevel.NONE)
    private List<PermissaoPessoa> permissaoPessoa;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

    public void setPermissaoPessoa(List<PermissaoPessoa> pp){
        for(PermissaoPessoa p: pp){
            p.setPessoa(this);
        }
        this.permissaoPessoa = pp;
    }
}
