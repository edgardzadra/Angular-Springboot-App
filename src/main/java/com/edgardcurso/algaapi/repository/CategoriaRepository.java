package com.edgardcurso.algaapi.repository;

import com.edgardcurso.algaapi.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
