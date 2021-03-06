package com.edgardcurso.algaapi.repository.lancamento;

import com.edgardcurso.algaapi.model.Categoria_;
import com.edgardcurso.algaapi.model.Lancamento;
import com.edgardcurso.algaapi.model.Lancamento_;
import com.edgardcurso.algaapi.model.Pessoa_;
import com.edgardcurso.algaapi.repository.filter.LancamentoFilter;
import com.edgardcurso.algaapi.repository.projection.ResumoLancamento;
import org.hibernate.Criteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
        Root<Lancamento> root = criteria.from(Lancamento.class);

        //cria as restrições
        Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Lancamento> query = entityManager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query,pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter));
    }

    @Override
    public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ResumoLancamento> criteria = builder.createQuery(ResumoLancamento.class);
        Root<Lancamento> root = criteria.from(Lancamento.class);

        criteria.select(builder.construct(ResumoLancamento.class
            , root.get(Lancamento_.codigo), root.get(Lancamento_.descricao)
            , root.get(Lancamento_.dataVencimento), root.get(Lancamento_.dataPagamento)
            , root.get(Lancamento_.valor), root.get(Lancamento_.tipo)
            , root.get(Lancamento_.categoria).get(Categoria_.nome)
            , root.get(Lancamento_.pessoa).get(Pessoa_.nome)));

        //cria as restrições
        Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<ResumoLancamento> query = entityManager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query,pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter));
    }

    private Predicate[] criarRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder builder, Root<Lancamento> root) {

        List<Predicate> predicates = new ArrayList<>();

        if(!StringUtils.isEmpty(lancamentoFilter.getDescricao())){
            predicates.add(builder.like(
                    builder.lower(root.get(Lancamento_.descricao)),"%" + lancamentoFilter.getDescricao().toLowerCase() + "%"
            ));
        }

        if(lancamentoFilter.getDataVencimentoDe() != null){
            predicates.add(
                    builder.greaterThanOrEqualTo(root.get(Lancamento_.dataVencimento), lancamentoFilter.getDataVencimentoDe())
            );
        }

        if(lancamentoFilter.getDataVencimentoAte() != null){
            predicates.add(
                    builder.lessThanOrEqualTo(root.get(Lancamento_.dataVencimento), lancamentoFilter.getDataVencimentoAte())
            );
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private Long total(LancamentoFilter lancamentoFilter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);

        /**
         * esquema como é montado a query com o criteria do Jpa
         */
        //from lancamento
        Root<Lancamento> root = criteria.from(Lancamento.class);

        //where
        Predicate[] preciates = criarRestricoes(lancamentoFilter, builder, root);
        criteria.where(preciates);

        //select count * from
        criteria.select(builder.count(root));
        return entityManager.createQuery(criteria).getSingleResult();
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }
}
