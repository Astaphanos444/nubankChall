package com.nubankChall.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nubankChall.demo.Model.Clientes;

public interface ClientesRepository extends JpaRepository<Clientes, Long>{

}
