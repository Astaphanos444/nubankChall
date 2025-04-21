package com.nubankChall.demo.Dto;

import java.util.List;



public record ClientesResponseDto(
    Long id,
    String nome,
    List<ContatoResponseDto> contatos) 
{
}
