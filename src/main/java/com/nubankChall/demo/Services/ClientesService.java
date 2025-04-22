package com.nubankChall.demo.Services;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nubankChall.demo.Dto.ClientesDto;
import com.nubankChall.demo.Dto.ClientesResponseDto;
import com.nubankChall.demo.Dto.ContatoResponseDto;
import com.nubankChall.demo.Model.Clientes;
import com.nubankChall.demo.Model.Contato;
import com.nubankChall.demo.Repository.ClientesRepository;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepository rep;

    public Clientes salvarCliente(ClientesDto dto)
    {
        Clientes cliente = new Clientes();
        cliente.setNome(dto.nome());

        if(dto.contatos() != null && dto.contatos().size() > 0)
        {
            List<Contato> contatos = dto.contatos()
                .stream()
                .map(c -> 
                {
                    Contato contato = new Contato();
                    contato.setTelefone(c.getTelefone());
                    contato.setEmail(c.getEmail());
                    contato.setCliente(c.getCliente()); 
                    return contato;
                })
                .collect(Collectors.toList());
                cliente.setContatos(contatos);
        }

        return rep.save(cliente);
    }

    public List<ClientesResponseDto> listarTodos()
    {
        return rep.findAll()
            .stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    } 

    public List<ContatoResponseDto> listarContatosPorClienteId(Long clienteId)
        throws RuntimeException
    {
        Clientes cliente = rep.findById(clienteId)
            .orElseThrow(()->new RuntimeException("cliente não encontrado"));
        return cliente.getContatos()
            .stream()
            .map(c->{
                ContatoResponseDto contato = new ContatoResponseDto(
                    c.getId(), 
                    c.getTelefone(), 
                    c.getEmail(),
                    cliente.getId());
                    return contato;
            }).collect(Collectors.toList());
    }

    private ClientesResponseDto toDto(Clientes cliente)
    {
        List<ContatoResponseDto> contatos = cliente.getContatos()
                .stream()
                .map(c -> 
                {
                    ContatoResponseDto contato = new ContatoResponseDto(
                        c.getId(), 
                        c.getTelefone(), 
                        c.getEmail(),
                        cliente.getId());
                    return contato;
                })
                .collect(Collectors.toList());

        ClientesResponseDto response = new ClientesResponseDto(
            cliente.getId(), 
            cliente.getNome(), 
            contatos);
        
        
        return response;
    }
}
