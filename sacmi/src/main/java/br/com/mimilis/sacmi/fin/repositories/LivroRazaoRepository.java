package br.com.mimilis.sacmi.fin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mimilis.sacmi.fin.domain.LivroRazao;

@Repository
public interface LivroRazaoRepository extends JpaRepository<LivroRazao, Long> {

}
