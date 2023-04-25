package com.dev.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.backend.entity.ProdutoImagens;
import com.dev.backend.service.ProdutoImagensService;

@RestController
@RequestMapping("/api/produtoImagem")
public class ProdutoImagensController {

    @Autowired
    private ProdutoImagensService produtoImagemService;

    @GetMapping("/")
    public List<ProdutoImagens> buscarTodos(){
       return produtoImagemService.buscarTodos();
    }

    @PostMapping("/")
    public ProdutoImagens inserir(@RequestBody ProdutoImagens produtoImagens){
        return produtoImagemService.inserir(produtoImagens);
    }

    @PutMapping("/")
    public ProdutoImagens alterar(@RequestBody ProdutoImagens produtoImagens){
        return produtoImagemService.alterar(produtoImagens);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id){
        produtoImagemService.excluir(id);
        return ResponseEntity.ok().build();
    }
    
}
