package com.example.hospital.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.hospital.model.Medico;
import com.example.hospital.service.MedicoService;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoService service;

    public MedicoController(MedicoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Medico> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Medico buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Medico salvar(@RequestBody Medico medico) {
        return service.salvar(medico);
    }

    @PutMapping("/{id}")
    public Medico atualizar(@PathVariable Long id, @RequestBody Medico medico) {
        return service.atualizar(id, medico);
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id) {
        return service.deletar(id)
                ? "Médico removido com sucesso!"
                : "Médico não encontrado!";
    }
}