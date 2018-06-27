package com.edgardcurso.algaapi.repository;

import com.edgardcurso.algaapi.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
