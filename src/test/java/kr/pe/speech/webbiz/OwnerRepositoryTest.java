package kr.pe.speech.webbiz;

import kr.pe.speech.webbiz.domain.Owner;
import kr.pe.speech.webbiz.domain.OwnerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase( replace= AutoConfigureTestDatabase.Replace.NONE)
public class OwnerRepositoryTest {
    @Autowired
    private OwnerRepository repository;


    @Test
    void saveOwner(){
        repository.save( new Owner("Lucy", "Smith") );
        //assertThat( repository.findByFirstName("Lucy").isPresent() ).isTrue();

    }
}
