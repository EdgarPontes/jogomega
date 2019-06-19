package br.com.paginamega.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.paginamega.domain.Jogo;

public interface JogoRepository extends JpaRepository<Jogo, Integer>{

}
