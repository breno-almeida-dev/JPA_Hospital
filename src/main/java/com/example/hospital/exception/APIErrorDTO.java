package com.example.hospital.exception;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class APIErrorDTO {

    @Getter
    private List<String> erros;

    public APIErrorDTO(String mensagem){
        this.erros = Arrays.asList(mensagem);
    }

    public APIErrorDTO(List<String> erros){
        this.erros = erros;
    }
}