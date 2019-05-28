package br.com.paginamega.repository;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.paginamega.domain.Sorteio;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TestSorteioRepository {

	@Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SorteioRepository repository;
    
    @Test
    public void findById() {
    	Sorteio newSorteio = new Sorteio(1,1,2,3,4,5,6);
    	
    	this.entityManager.persist(newSorteio );
    	Optional<Sorteio> sorteio = this.repository.findById(1L);
    	
    	Assert.assertNotNull(sorteio);
    }
}
