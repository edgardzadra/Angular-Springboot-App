package com.edgardcurso.algaapi.repository;

import com.edgardcurso.algaapi.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
}
