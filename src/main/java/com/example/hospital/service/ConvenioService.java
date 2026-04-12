package com.example.hospital.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hospital.model.Convenio;
import com.example.hospital.repository.ConvenioRepository;

@Service
public class ConvenioService {

    private final ConvenioRepository repository;

    public ConvenioService(ConvenioRepository repository) {
        this.repository = repository;
    }

    public List<Convenio> listarTodos() {
        return repository.findAll();
    }

    public Convenio buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Convenio salvar(Convenio convenio) {
        return repository.save(convenio);
    }

    public Convenio atualizar(Long id, Convenio convenioAtualizado) {
        Convenio convenioExistente = repository.findById(id).orElse(null);

        if (convenioExistente == null) {
            return null;
        }

        convenioExistente.setNome(convenioAtualizado.getNome());
        convenioExistente.setCnpj(convenioAtualizado.getCnpj());
        convenioExistente.setConsultas(convenioAtualizado.getConsultas());

        return repository.save(convenioExistente);
    }

    public boolean deletar(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }
}