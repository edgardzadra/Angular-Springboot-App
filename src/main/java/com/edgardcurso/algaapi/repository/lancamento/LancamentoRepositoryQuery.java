package com.edgardcurso.algaapi.repository.lancamento;

import com.edgardcurso.algaapi.model.Lancamento;
import com.edgardcurso.algaapi.repository.filter.LancamentoFilter;
import com.edgardcurso.algaapi.repository.projection.ResumoLancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LancamentoRepositoryQuery {

    public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
    public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);
}
