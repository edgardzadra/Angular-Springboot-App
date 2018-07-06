package com.edgardcurso.algaapi.repository.lancamento;

import com.edgardcurso.algaapi.model.Lancamento;
import com.edgardcurso.algaapi.repository.filter.LancamentoFilter;

import java.util.List;

public interface LancamentoRepositoryQuery {

    public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);
}
