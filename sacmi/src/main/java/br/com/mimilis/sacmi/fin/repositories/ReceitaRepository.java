package br.com.mimilis.sacmi.fin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mimilis.sacmi.fin.domain.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

}