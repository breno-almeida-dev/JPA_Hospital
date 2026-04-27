package com.example.hospital.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hospital.dto.ConsultaRequestDTO;
import com.example.hospital.dto.ConsultaResponseDTO;
import com.example.hospital.model.*;
import com.example.hospital.repository.*;

@Service
public class ConsultaService {

    private final ConsultaRepository repository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;
    private final ConvenioRepository convenioRepository;

    public ConsultaService(
            ConsultaRepository repository,
            PacienteRepository pacienteRepository,
            MedicoRepository medicoRepository,
            ConvenioRepository convenioRepository
    ) {
        this.repository = repository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
        this.convenioRepository = convenioRepository;
    }

    public List<ConsultaResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(ConsultaResponseDTO::new)
                .toList();
    }

    public ConsultaResponseDTO buscarPorId(Long id) {
        Consulta consulta = repository.findById(id).orElse(null);

        if (consulta == null) {
            return null;
        }

        return new ConsultaResponseDTO(consulta);
    }

    public ConsultaResponseDTO salvar(ConsultaRequestDTO dto) {
        Consulta consulta = new Consulta();

        consulta.setDataHora(dto.getDataHora());
        consulta.setMotivo(dto.getMotivo());
        consulta.setValor(dto.getValor());

        Paciente paciente = pacienteRepository.findById(dto.getPacienteId()).orElse(null);
        Medico medico = medicoRepository.findById(dto.getMedicoId()).orElse(null);
        Convenio convenio = convenioRepository.findById(dto.getConvenioId()).orElse(null);

        consulta.setPaciente(paciente);
        consulta.setMedico(medico);
        consulta.setConvenio(convenio);

        Consulta salva = repository.save(consulta);

        return new ConsultaResponseDTO(salva);
    }

    public ConsultaResponseDTO atualizar(Long id, ConsultaRequestDTO dto) {
        Consulta consultaExistente = repository.findById(id).orElse(null);

        if (consultaExistente == null) {
            return null;
        }

        consultaExistente.setDataHora(dto.getDataHora());
        consultaExistente.setMotivo(dto.getMotivo());
        consultaExistente.setValor(dto.getValor());

        Paciente paciente = pacienteRepository.findById(dto.getPacienteId()).orElse(null);
        Medico medico = medicoRepository.findById(dto.getMedicoId()).orElse(null);
        Convenio convenio = convenioRepository.findById(dto.getConvenioId()).orElse(null);

        consultaExistente.setPaciente(paciente);
        consultaExistente.setMedico(medico);
        consultaExistente.setConvenio(convenio);

        Consulta atualizada = repository.save(consultaExistente);

        return new ConsultaResponseDTO(atualizada);
    }

    public boolean deletar(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }
}