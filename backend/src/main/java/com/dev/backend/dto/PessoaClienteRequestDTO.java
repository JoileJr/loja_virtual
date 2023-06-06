package com.dev.backend.dto;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.BeanUtils;

import com.dev.backend.entity.Cidade;
import com.dev.backend.entity.Pessoa;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PessoaClienteRequestDTO {
    @NotNull(message = "Nome não pode ser nulo")
    private String nome;
    @NotNull(message = "CPF não pode ser nulo")
    @CPF(message = "CPF precisa ser valido")
    private String cpf;
    @NotNull(message = "email não pode ser nulo")
    @Email(message = "email precisa ser valido")
    private String email;
    @Size(min = 8, max = 20, message = "A senha precisa ter entre 8 - 20 caracteres")
    private String senha;
    private String endereco;
    private String cep;
    private Cidade cidade;

    public Pessoa converter(PessoaClienteRequestDTO pessoaClienteRequestDTO){
        Pessoa pessoa = new Pessoa();
        BeanUtils.copyProperties(pessoaClienteRequestDTO, pessoa);
        return pessoa;
    }
}
