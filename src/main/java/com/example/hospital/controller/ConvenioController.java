package com.example.hospital.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.hospital.model.Convenio;
import com.example.hospital.service.ConvenioService;

@RestController
@RequestMapping("/convenios")
public class ConvenioController {

    private final ConvenioService service;

    public ConvenioController(ConvenioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Convenio> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Convenio buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Convenio salvar(@RequestBody Convenio convenio) {
        return service.salvar(convenio);
    }

    @PutMapping("/{id}")
    public Convenio atualizar(@PathVariable Long id, @RequestBody Convenio convenio) {
        return service.atualizar(id, convenio);
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id) {
        return service.deletar(id)
                ? "Convênio removido com sucesso!"
                : "Convênio não encontrado!";
    }
}