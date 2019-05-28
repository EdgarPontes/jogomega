package br.com.paginamega.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.paginamega.domain.Sorteio;

public interface SorteioRepository extends JpaRepository<Sorteio, Long>{

	List<Sorteio> findByConcursoLessThan(Integer concurso);	//Trás os jogos menores que o valor passado
	
	List<Sorteio> findByConcursoGreaterThan(Integer concurso); //Trás os jogos maiores que o valor passado
	
}
