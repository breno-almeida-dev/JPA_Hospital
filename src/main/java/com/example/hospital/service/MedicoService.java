package com.example.hospital.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hospital.model.Medico;
import com.example.hospital.repository.MedicoRepository;

@Service
public class MedicoService {

    private final MedicoRepository repository;

    public MedicoService(MedicoRepository repository) {
        this.repository = repository;
    }

    public List<Medico> listarTodos() {
        return repository.findAll();
    }

    public Medico buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Medico salvar(Medico medico) {
        return repository.save(medico);
    }

    public Medico atualizar(Long id, Medico medicoAtualizado) {
        Medico medicoExistente = repository.findById(id).orElse(null);

        if (medicoExistente == null) {
            return null;
        }

        medicoExistente.setNome(medicoAtualizado.getNome());
        medicoExistente.setEspecialidade(medicoAtualizado.getEspecialidade());
        medicoExistente.setCrm(medicoAtualizado.getCrm());
        medicoExistente.setConsultas(medicoAtualizado.getConsultas());

        return repository.save(medicoExistente);
    }

    public boolean deletar(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }
}