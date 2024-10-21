package br.com.fiap.ms_produtos_catalog.repository;

import br.com.fiap.ms_produtos_catalog.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
}