package com.nubankChall.demo.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient.ResponseSpec;

import com.nubankChall.demo.Dto.ContatoDto;
import com.nubankChall.demo.Model.Clientes;
import com.nubankChall.demo.Model.Contato;
import com.nubankChall.demo.Repository.ClientesRepository;
import com.nubankChall.demo.Repository.ContatoRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/contatos")
public class ContatoController {
    @Autowired
    private ContatoRepository conRep;
    @Autowired
    private ClientesRepository cliRep;

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ContatoDto dto) {
        Optional<Clientes> cliente = cliRep.findById(dto.clienteId());
        if(cliente.isEmpty()) {return ResponseEntity.badRequest().body(null);}

        Contato contato = new Contato();
        contato.setTelefone(dto.telefone());
        contato.setEmail(dto.email());
        contato.setCliente(cliente.get());

        conRep.save(contato);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(contato);
    }
    
}
