package com.example.hospital.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hospital.model.Prontuario;
import com.example.hospital.repository.ProntuarioRepository;

@Service
public class ProntuarioService {

    private final ProntuarioRepository repository;

    public ProntuarioService(ProntuarioRepository repository) {
        this.repository = repository;
    }

    public List<Prontuario> listarTodos() {
        return repository.findAll();
    }

    public Prontuario buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Prontuario salvar(Prontuario prontuario) {
        return repository.save(prontuario);
    }

    public Prontuario atualizar(Long id, Prontuario prontuarioAtualizado) {
        Prontuario prontuarioExistente = repository.findById(id).orElse(null);

        if (prontuarioExistente == null) {
            return null;
        }

        prontuarioExistente.setTipoSanguineo(prontuarioAtualizado.getTipoSanguineo());
        prontuarioExistente.setAlergia(prontuarioAtualizado.getAlergia());
        prontuarioExistente.setObservacoes(prontuarioAtualizado.getObservacoes());
        prontuarioExistente.setPaciente(prontuarioAtualizado.getPaciente());

        return repository.save(prontuarioExistente);
    }

    public boolean deletar(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }
}