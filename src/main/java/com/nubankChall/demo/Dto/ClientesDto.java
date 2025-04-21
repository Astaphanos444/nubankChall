package com.nubankChall.demo.Dto;

import java.util.List;

import com.nubankChall.demo.Model.Contato;

public record ClientesDto(
    String nome,
    List<Contato> contatos
    ) {
}
