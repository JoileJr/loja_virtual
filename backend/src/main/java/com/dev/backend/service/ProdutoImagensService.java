package com.dev.backend.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.entity.ProdutoImagens;
import com.dev.backend.repository.ProdutoImagensRepository;

@Service
public class ProdutoImagensService {

    @Autowired
    private ProdutoImagensRepository produtoImangemRepository;

    public List<ProdutoImagens> buscarTodos() {
        return  produtoImangemRepository.findAll();
    }

    public ProdutoImagens inserir(ProdutoImagens produtoImagens) {
        produtoImagens.setDataCriacao(new Date());
        ProdutoImagens produtoImagensNovo =  produtoImangemRepository.saveAndFlush(produtoImagens);
        return produtoImagensNovo;
    }

    public ProdutoImagens alterar(ProdutoImagens produtoImagens) {
        produtoImagens.setDataAtualizacao(new Date());
        return  produtoImangemRepository.saveAndFlush(produtoImagens);
    }

    public void excluir(Long id) {
        ProdutoImagens produtoImagens =  produtoImangemRepository.findById(id).get();
        produtoImangemRepository.delete(produtoImagens);
    }
    
}
