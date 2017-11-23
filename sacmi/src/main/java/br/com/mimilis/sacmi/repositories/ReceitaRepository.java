package br.com.mimilis.sacmi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mimilis.sacmi.domain.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

}
