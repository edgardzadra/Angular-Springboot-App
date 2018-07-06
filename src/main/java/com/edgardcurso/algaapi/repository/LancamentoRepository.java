package com.edgardcurso.algaapi.repository;

import com.edgardcurso.algaapi.model.Lancamento;
import com.edgardcurso.algaapi.repository.lancamento.LancamentoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {
}
