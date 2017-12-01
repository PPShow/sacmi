package br.com.mimilis.sacmi.fin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mimilis.sacmi.fin.domain.Despesa;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {

}
