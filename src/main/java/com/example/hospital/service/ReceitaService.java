package com.example.hospital.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hospital.model.Receita;
import com.example.hospital.repository.ReceitaRepository;

@Service
public class ReceitaService {

    private final ReceitaRepository repository;

    public ReceitaService(ReceitaRepository repository) {
        this.repository = repository;
    }

    public List<Receita> listarTodos() {
        return repository.findAll();
    }

    public Receita buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Receita salvar(Receita receita) {
        return repository.save(receita);
    }

    public Receita atualizar(Long id, Receita receitaAtualizada) {
        Receita receitaExistente = repository.findById(id).orElse(null);

        if (receitaExistente == null) {
            return null;
        }

        receitaExistente.setMedicamento(receitaAtualizada.getMedicamento());
        receitaExistente.setDosagem(receitaAtualizada.getDosagem());
        receitaExistente.setDuracaoDias(receitaAtualizada.getDuracaoDias());
        receitaExistente.setConsulta(receitaAtualizada.getConsulta());

        return repository.save(receitaExistente);
    }

    public boolean deletar(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }
}