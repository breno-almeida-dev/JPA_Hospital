package com.example.hospital.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.hospital.model.Receita;
import com.example.hospital.service.ReceitaService;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    private final ReceitaService service;

    public ReceitaController(ReceitaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Receita> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Receita buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Receita salvar(@RequestBody Receita receita) {
        return service.salvar(receita);
    }

    @PutMapping("/{id}")
    public Receita atualizar(@PathVariable Long id, @RequestBody Receita receita) {
        return service.atualizar(id, receita);
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id) {
        return service.deletar(id)
                ? "Receita removida com sucesso!"
                : "Receita não encontrada!";
    }
}