package nc.observation.animaux.marins.repository;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import nc.observation.animaux.marins.entity.Ilot;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Sql("/data/ilots.sql")
public class IlotRepositoryTest {

    @Autowired
    private IlotRepository ilotRepository;

    @Test
    public void testfindByTitre() {
        // GIVEN
        String titre = "Îlot Tapoué";

        // WHEN
        Optional<Ilot> optionalResult = ilotRepository.findByTitre(titre);

        // THEN
        assertThat(optionalResult).hasValueSatisfying(result -> {
            assertThat(result.getId()).isEqualTo("ff80818167a5556c0167a555a7e50056");
            assertThat(result.getTitre()).isEqualTo(titre);
            assertThat(result.getLocalisation()).isEqualTo("POINT (166.10652709356654 -22.024985452842138)");
            assertThat(result.getPageWeb()).isEqualTo("https://www.province-sud.nc/pandoreweb/app/ilot/Tapoué");
        });
    }
}
