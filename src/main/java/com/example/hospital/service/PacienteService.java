package com.example.hospital.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hospital.model.Paciente;
import com.example.hospital.repository.PacienteRepository;

@Service
public class PacienteService {

    private final PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    public List<Paciente> listarTodos() {
        return repository.findAll();
    }

    public Paciente buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Paciente salvar(Paciente paciente) {
        return repository.save(paciente);
    }

    public Paciente atualizar(Long id, Paciente pacienteAtualizado) {
        Paciente pacienteExistente = repository.findById(id).orElse(null);

        if (pacienteExistente == null) {
            return null;
        }

        pacienteExistente.setNome(pacienteAtualizado.getNome());
        pacienteExistente.setCpf(pacienteAtualizado.getCpf());
        pacienteExistente.setTelefone(pacienteAtualizado.getTelefone());
        pacienteExistente.setProntuario(pacienteAtualizado.getProntuario());
        pacienteExistente.setConsultas(pacienteAtualizado.getConsultas());

        return repository.save(pacienteExistente);
    }

    public boolean deletar(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }
}