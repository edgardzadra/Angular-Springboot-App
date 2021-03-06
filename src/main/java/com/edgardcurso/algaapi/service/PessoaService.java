package com.edgardcurso.algaapi.service;

import com.edgardcurso.algaapi.model.Pessoa;
import com.edgardcurso.algaapi.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa atualizar(Long codigo, Pessoa pessoa){
        Optional<Pessoa> pessoaSalva = getPessoa(codigo);

        BeanUtils.copyProperties(pessoa, pessoaSalva.get(), "codigo");
        return pessoaRepository.save(pessoaSalva.get());
    }

    public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
        Optional<Pessoa> pessoaSalva = getPessoa(codigo);
        pessoaSalva.get().setAtivo(ativo);

        pessoaRepository.save(pessoaSalva.get());
    }

    public Optional<Pessoa> getPessoa(Long codigo) {
        Optional<Pessoa> pessoaSalva = pessoaRepository.findById(codigo);

        if(!pessoaSalva.isPresent()){
            throw new EmptyResultDataAccessException(1);
        }
        return pessoaSalva;
    }
}
