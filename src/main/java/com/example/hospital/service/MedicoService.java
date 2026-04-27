package com.example.hospital.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hospital.dto.MedicoRequestDTO;
import com.example.hospital.dto.MedicoResponseDTO;
import com.example.hospital.model.Medico;
import com.example.hospital.repository.MedicoRepository;

@Service
public class MedicoService {

    private final MedicoRepository repository;

    public MedicoService(MedicoRepository repository) {
        this.repository = repository;
    }

    public List<MedicoResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(MedicoResponseDTO::new)
                .toList();
    }

    public MedicoResponseDTO buscarPorId(Long id) {
        Medico medico = repository.findById(id).orElse(null);

        if (medico == null) {
            return null;
        }

        return new MedicoResponseDTO(medico);
    }

    public MedicoResponseDTO salvar(MedicoRequestDTO dto) {
        Medico medico = new Medico();

        medico.setNome(dto.getNome());
        medico.setEspecialidade(dto.getEspecialidade());
        medico.setCrm(dto.getCrm());

        Medico medicoSalvo = repository.save(medico);

        return new MedicoResponseDTO(medicoSalvo);
    }

    public MedicoResponseDTO atualizar(Long id, MedicoRequestDTO dto) {
        Medico medicoExistente = repository.findById(id).orElse(null);

        if (medicoExistente == null) {
            return null;
        }

        medicoExistente.setNome(dto.getNome());
        medicoExistente.setEspecialidade(dto.getEspecialidade());
        medicoExistente.setCrm(dto.getCrm());

        Medico medicoAtualizado = repository.save(medicoExistente);

        return new MedicoResponseDTO(medicoAtualizado);
    }

    public boolean deletar(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }
}