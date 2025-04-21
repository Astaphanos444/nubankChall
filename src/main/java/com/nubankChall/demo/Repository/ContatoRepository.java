package com.nubankChall.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nubankChall.demo.Model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long>{

}
