package nc.observation.animaux.marins.repository;

import static nc.observation.animaux.marins.enums.QualiteIdentification.SUSPICION;
import static nc.observation.animaux.marins.enums.TypeAnimalMarin.BALEINE_A_BOSSE;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import nc.observation.animaux.marins.entity.FicheObservation;
import nc.observation.animaux.marins.repository.FicheObservationRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Sql(scripts = {"/data/ilots.sql", "/data/fiches-observations.sql"})
public class FicheObservationRepositoryTest {

    @Autowired
    private FicheObservationRepository ficheObservationRepository;

    @Test
    public void testFindAllByTypeNoFilter() {
        // WHEN
        List<FicheObservation> result = ficheObservationRepository.findAllByType(null);

        // THEN
        assertThat(result).hasSize(4)
            .anySatisfy(ficheObservation -> {
                assertThat(ficheObservation.getAnimalMarin()).isEqualTo(BALEINE_A_BOSSE);
                assertThat(ficheObservation.getIlot().getId()).isEqualTo("ff80818167a5556c0167a5559f2c0007");
                assertThat(ficheObservation.getDistanceBordIlot()).isEqualTo(10);
                assertThat(ficheObservation.getDateObservation()).isEqualTo(LocalDateTime.of(2023, 1, 14, 14, 30));
                assertThat(ficheObservation.getQualiteIdentification()).isEqualTo(SUSPICION);
                assertThat(ficheObservation.getTailleEstimeIndividu()).isEqualTo(15);
                assertThat(ficheObservation.getTempsApnee()).isEqualTo(60);
                assertThat(ficheObservation.getIsIndividu()).isTrue();
                assertThat(ficheObservation.getEstimationNbIndividus()).isNull();
            });
    }

    @Test
    public void testFindAllByTypeWithFilter() {
        // WHEN
        List<FicheObservation> result = ficheObservationRepository.findAllByType(BALEINE_A_BOSSE);

        // THEN
        assertThat(result).hasSize(2).allSatisfy(ficheObservation -> assertThat(ficheObservation.getAnimalMarin()).isEqualTo(BALEINE_A_BOSSE));
    }
}
