package com.dev.backend.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.dto.PessoaClienteRequestDTO;
import com.dev.backend.entity.Pessoa;
import com.dev.backend.repository.PessoaClienteRepository;

@Service
public class PessoaClienteService {

    @Autowired
    private PessoaClienteRepository pessoaClienteRepository;

    @Autowired
    private PermissaoPessoaService permissaoPessoaService;

    @Autowired
    private EmailService emailService;

    public Pessoa registrar(PessoaClienteRequestDTO pessoaClienteRequestDTO) {
        Pessoa pessoa = new PessoaClienteRequestDTO().converter(pessoaClienteRequestDTO);
        pessoa.setDataCriacao(new Date());
        Pessoa pessoaNovo = pessoaClienteRepository.saveAndFlush(pessoa);
        permissaoPessoaService.vincularPessoaPermissaoCliente(pessoaNovo);
        //emailService.enviarEmailTexto(pessoaNovo.getEmail(), "cadastro na loja tabajara", "o registro foi realizado com sucesso");
        Map<String, Object> propMap = new HashMap<>();
        propMap.put("nome", pessoaNovo.getNome());
        propMap.put("mensagem", "teste");
        emailService.enviarEmailTemplate(pessoaNovo.getEmail(), "Cadastro na loja tabajara", propMap);
        return pessoaNovo;
    }

}