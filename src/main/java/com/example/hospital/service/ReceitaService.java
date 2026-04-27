package com.example.hospital.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hospital.dto.ReceitaRequestDTO;
import com.example.hospital.dto.ReceitaResponseDTO;
import com.example.hospital.model.Consulta;
import com.example.hospital.model.Receita;
import com.example.hospital.repository.ConsultaRepository;
import com.example.hospital.repository.ReceitaRepository;

@Service
public class ReceitaService {

    private final ReceitaRepository repository;
    private final ConsultaRepository consultaRepository;

    public ReceitaService(ReceitaRepository repository, ConsultaRepository consultaRepository) {
        this.repository = repository;
        this.consultaRepository = consultaRepository;
    }

    public List<ReceitaResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(ReceitaResponseDTO::new)
                .toList();
    }

    public ReceitaResponseDTO buscarPorId(Long id) {
        Receita receita = repository.findById(id).orElse(null);

        if (receita == null) {
            return null;
        }

        return new ReceitaResponseDTO(receita);
    }

    public ReceitaResponseDTO salvar(ReceitaRequestDTO dto) {
        Receita receita = new Receita();

        receita.setMedicamento(dto.getMedicamento());
        receita.setDosagem(dto.getDosagem());
        receita.setDuracaoDias(dto.getDuracaoDias());

        Consulta consulta = consultaRepository.findById(dto.getConsultaId()).orElse(null);
        receita.setConsulta(consulta);

        Receita receitaSalva = repository.save(receita);

        return new ReceitaResponseDTO(receitaSalva);
    }

    public ReceitaResponseDTO atualizar(Long id, ReceitaRequestDTO dto) {
        Receita receitaExistente = repository.findById(id).orElse(null);

        if (receitaExistente == null) {
            return null;
        }

        receitaExistente.setMedicamento(dto.getMedicamento());
        receitaExistente.setDosagem(dto.getDosagem());
        receitaExistente.setDuracaoDias(dto.getDuracaoDias());

        Consulta consulta = consultaRepository.findById(dto.getConsultaId()).orElse(null);
        receitaExistente.setConsulta(consulta);

        Receita receitaAtualizada = repository.save(receitaExistente);

        return new ReceitaResponseDTO(receitaAtualizada);
    }

    public boolean deletar(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }
}