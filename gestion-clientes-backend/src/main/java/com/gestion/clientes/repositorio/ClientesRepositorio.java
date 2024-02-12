package com.gestion.clientes.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.clientes.modelo.Clientes;

@Repository
public interface ClientesRepositorio extends JpaRepository<Clientes, Long>{

}
