package com.dev.backend.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dev.backend.entity.Estado;
import com.dev.backend.repository.EstadoRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

@Service
public class EstadoService {
    
    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> buscarTodos() {
        return estadoRepository.findAll();
    }

    public Estado inserir(Estado estado) {
        estado.setDataCriacao(new Date());
        Estado estadoNovo = estadoRepository.saveAndFlush(estado);
        return estadoNovo;
    }

    public Estado alterar(Estado estado) {
        estado.setDataAtualizacao(new Date());
        return estadoRepository.saveAndFlush(estado);
    }

    public void excluir(Long id) {
        Estado estado = estadoRepository.findById(id).get();
        estadoRepository.delete(estado);
    }


    public List<Estado> lerCSV(MultipartFile file) throws IOException, CsvException {
        List<Estado> estados = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            List<String[]> linhas = reader.readAll();

            String[] linhaSigla = linhas.get(0);
            if(linhaSigla[2].equals("sigla")){
                for (int i = 1; i < linhas.size(); i++) {
                    String[] linha = linhas.get(i);
                    Estado estado = new Estado();
                    long id = Long.parseLong(linha[0]);
                    if(estadoRepository.existsById(id)){
                        estado = estadoRepository.findById(id).get();
                        estado.setDataAtualizacao(new Date());
                    } else {
                        estado.setId(id);
                        estado.setDataCriacao(new Date());
                    }
                    estado.setNome(linha[1]);
                    estado.setSigla(linha[2]);  
                    estadoRepository.saveAndFlush(estado);
                    estados.add(estado);
                }
            } else {
                System.out.println("Esse arquivo não tem os dados correspondente");
            }
        }
        return estados;
    }
}
