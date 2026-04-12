package com.example.hospital.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hospital.model.Consulta;
import com.example.hospital.repository.ConsultaRepository;

@Service
public class ConsultaService {

    private final ConsultaRepository repository;

    public ConsultaService(ConsultaRepository repository) {
        this.repository = repository;
    }

    public List<Consulta> listarTodos() {
        return repository.findAll();
    }

    public Consulta buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Consulta salvar(Consulta consulta) {
        return repository.save(consulta);
    }

    public Consulta atualizar(Long id, Consulta consultaAtualizada) {
        Consulta consultaExistente = repository.findById(id).orElse(null);

        if (consultaExistente == null) {
            return null;
        }

        consultaExistente.setDataHora(consultaAtualizada.getDataHora());
        consultaExistente.setMotivo(consultaAtualizada.getMotivo());
        consultaExistente.setValor(consultaAtualizada.getValor());
        consultaExistente.setPaciente(consultaAtualizada.getPaciente());
        consultaExistente.setMedico(consultaAtualizada.getMedico());
        consultaExistente.setConvenio(consultaAtualizada.getConvenio());
        consultaExistente.setReceita(consultaAtualizada.getReceita());

        return repository.save(consultaExistente);
    }

    public boolean deletar(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }
}