package com.nubankChall.demo.Controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nubankChall.demo.Dto.ClientesDto;
import com.nubankChall.demo.Dto.ClientesResponseDto;
import com.nubankChall.demo.Dto.ContatoResponseDto;
import com.nubankChall.demo.Model.Clientes;
import com.nubankChall.demo.Services.ClientesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClientesService serv;

    @PostMapping
    public ResponseEntity<Clientes> criar(@RequestBody ClientesDto dto) {
        Clientes cliente = serv.salvarCliente(dto);
        return ResponseEntity.ok().body(cliente);
    }
    
    @GetMapping
    public ResponseEntity<List<ClientesResponseDto>> listarTodos() {
        return ResponseEntity.ok().body(serv.listarTodos());
    }

    @GetMapping("/{id}/contatos")
        public ResponseEntity<List<ContatoResponseDto>> getMethodName(@PathVariable Long id) {
        return ResponseEntity.ok().body(serv.listarContatosPorClienteId(id));
    }
    
}
