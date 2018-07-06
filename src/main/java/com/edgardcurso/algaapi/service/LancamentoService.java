package com.edgardcurso.algaapi.service;

import com.edgardcurso.algaapi.model.Lancamento;
import com.edgardcurso.algaapi.model.Pessoa;
import com.edgardcurso.algaapi.repository.LancamentoRepository;
import com.edgardcurso.algaapi.repository.PessoaRepository;
import com.edgardcurso.algaapi.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LancamentoService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    public Lancamento salvar(Lancamento lancamento) {
        Optional<Pessoa> p = pessoaRepository.findById(lancamento.getPessoa().getCodigo());

        if(!p.isPresent() || p.get().isInativo()){
            throw new PessoaInexistenteOuInativaException();
        }

        return lancamentoRepository.save(lancamento);

    }
}
