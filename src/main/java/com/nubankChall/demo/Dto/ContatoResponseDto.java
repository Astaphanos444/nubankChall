package com.nubankChall.demo.Dto;




public record ContatoResponseDto(
    Long id,
    String telefone, 
    String email, 
    Long clienteId) 
{
}
