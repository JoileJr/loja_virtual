package com.dev.backend.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dev.backend.entity.Estado;
import com.dev.backend.service.EstadoService;
import com.opencsv.exceptions.CsvException;

@RestController
@RequestMapping("/api/estado")
public class EstadoController {
    
    @Autowired
    private EstadoService estadoService;

    @GetMapping("/")
    public List<Estado> buscarTodos(){
       return estadoService.buscarTodos();
    }

    @PostMapping("/processar")
    public ResponseEntity<String> processarCSV(@RequestParam("file") MultipartFile file) {
        try {
            estadoService.lerCSV(file);
            return ResponseEntity.ok("Arquivo CSV processado com sucesso");
        } catch (IOException | CsvException e) {
            System.out.println("Erro ao receber o arquivo");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar o arquivo CSV");
        }
    }

    @PostMapping("/")
    public Estado inserir(@RequestBody Estado estado){
        return estadoService.inserir(estado);
    }

    @PutMapping("/")
    public Estado alterar(@RequestBody Estado estado){
        return estadoService.alterar(estado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id){
        estadoService.excluir(id);
        return ResponseEntity.ok().build();
    }
}