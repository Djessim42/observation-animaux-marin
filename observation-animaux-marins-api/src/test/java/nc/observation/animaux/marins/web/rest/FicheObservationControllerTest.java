package nc.observation.animaux.marins.web.rest;

import static nc.observation.animaux.marins.enums.QualiteIdentification.SUSPICION;
import static nc.observation.animaux.marins.enums.QualiteIdentification.VERIFIE;
import static nc.observation.animaux.marins.enums.TypeAnimalMarin.CACHALOT;
import static nc.observation.animaux.marins.enums.TypeAnimalMarin.DAUPHIN_TURSIOPE;
import static nc.observation.animaux.marins.enums.TypeAnimalMarin.THAZARD;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import nc.observation.animaux.marins.ObservationAnimauxMarinsApplication;
import nc.observation.animaux.marins.dto.CreateFicheObservationDTO;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ObservationAnimauxMarinsApplication.class)
@AutoConfigureMockMvc
@Sql(scripts = {"/data/ilots.sql", "/data/fiches-observations.sql"})
public class FicheObservationControllerTest {

    private static final String API_BASE_URL = "/api/fiches-observations";
    private static final String API_CREATE = API_BASE_URL + "/create";

    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @AfterEach
    void teardown() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "t_fiche_observation", "t_ilot");
    }

    @Test
    public void testGetFiches() throws Exception {
        mockMvc.perform(get(API_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$", hasSize(4)))
            .andExpect(jsonPath("$[0].animalMarin").value("BALEINE_A_BOSSE"))
            .andExpect(jsonPath("$[0].distanceBordIlot").value(10))
            .andExpect(jsonPath("$[0].dateObservation").value("2023-01-14T14:30:00"))
            .andExpect(jsonPath("$[0].qualiteIdentification").value("SUSPICION"))
            .andExpect(jsonPath("$[0].tailleEstimeIndividu").value(15))
            .andExpect(jsonPath("$[0].tempsApnee").value(60))
            .andExpect(jsonPath("$[0].isIndividu").value(true))
            .andExpect(jsonPath("$[0].estimationNbIndividus").isEmpty())
            .andExpect(jsonPath("$[0].ilot.id").value("ff80818167a5556c0167a5559f2c0007"))
            .andExpect(jsonPath("$[0].ilot.titre").value("Récif Mendigué"))
            .andExpect(jsonPath("$[0].ilot.localisation").value("POINT (166.48788888050734 -21.71354567432781)"))
            .andExpect(jsonPath("$[0].ilot.pageWeb").value("https://www.province-sud.nc/pandoreweb/app/ilot/Récif-Mendigué"))
            .andDo(print());
    }

    @Test
    public void testGetFichesDauphinTursiope() throws Exception {
        mockMvc.perform(get(API_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .param("animalMarin", DAUPHIN_TURSIOPE.name()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].animalMarin").value("DAUPHIN_TURSIOPE"))
            .andExpect(jsonPath("$[0].distanceBordIlot").value(20))
            .andExpect(jsonPath("$[0].dateObservation").value("2023-01-15T19:40:00"))
            .andExpect(jsonPath("$[0].qualiteIdentification").value("VERIFIE"))
            .andExpect(jsonPath("$[0].tailleEstimeIndividu").value(3))
            .andExpect(jsonPath("$[0].tempsApnee").value(40))
            .andExpect(jsonPath("$[0].isIndividu").value(true))
            .andExpect(jsonPath("$[0].estimationNbIndividus").isEmpty())
            .andExpect(jsonPath("$[0].ilot.id").value("ff80818167a5556c0167a5559f2c0007"))
            .andExpect(jsonPath("$[0].ilot.titre").value("Récif Mendigué"))
            .andExpect(jsonPath("$[0].ilot.localisation").value("POINT (166.48788888050734 -21.71354567432781)"))
            .andExpect(jsonPath("$[0].ilot.pageWeb").value("https://www.province-sud.nc/pandoreweb/app/ilot/Récif-Mendigué"))
            .andDo(print());
    }

    @Test
    public void testCreateFicheMammifere() throws Exception {
        // GIVEN
        CreateFicheObservationDTO createFicheObservationDTO = CreateFicheObservationDTO.builder()
            .animalMarin(CACHALOT)
            .titreIlot("Îlot Nouma")
            .distanceBordIlot(10)
            .dateObservation(LocalDateTime.of(2023, 1, 18, 6, 30))
            .qualiteIdentification(VERIFIE)
            .tailleEstimeIndividu(16)
            .tempsApnee(120)
            .isIndividu(true)
            .build();

        // WHEN
        mockMvc.perform(post(API_CREATE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createFicheObservationDTO)))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.animalMarin").value("CACHALOT"))
            .andExpect(jsonPath("$.distanceBordIlot").value(10))
            .andExpect(jsonPath("$.dateObservation").value("2023-01-18T06:30:00"))
            .andExpect(jsonPath("$.qualiteIdentification").value("VERIFIE"))
            .andExpect(jsonPath("$.tailleEstimeIndividu").value(16))
            .andExpect(jsonPath("$.tempsApnee").value(120))
            .andExpect(jsonPath("$.isIndividu").value(true))
            .andExpect(jsonPath("$.estimationNbIndividus").isEmpty())
            .andExpect(jsonPath("$.ilot.id").value("ff80818167a5556c0167a555a31b001b"))
            .andExpect(jsonPath("$.ilot.titre").value("Îlot Nouma"))
            .andExpect(jsonPath("$.ilot.localisation").value("POINT (167.41780430910623 -22.628895306032415)"))
            .andExpect(jsonPath("$.ilot.pageWeb").value("https://www.province-sud.nc/pandoreweb/app/ilot/Nouma"))
            .andDo(print());
    }

    @Test
    public void testCreateFicheBancPoisson() throws Exception {
        // GIVEN
        CreateFicheObservationDTO createFicheObservationDTO = CreateFicheObservationDTO.builder()
            .animalMarin(THAZARD)
            .titreIlot("Îlot Nouma")
            .distanceBordIlot(30)
            .dateObservation(LocalDateTime.of(2023, 1, 18, 7, 30))
            .qualiteIdentification(SUSPICION)
            .tempsApnee(60)
            .isIndividu(false)
            .estimationNbIndividus(4)
            .build();

        // WHEN
        mockMvc.perform(post(API_CREATE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createFicheObservationDTO)))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.animalMarin").value("THAZARD"))
            .andExpect(jsonPath("$.distanceBordIlot").value(30))
            .andExpect(jsonPath("$.dateObservation").value("2023-01-18T07:30:00"))
            .andExpect(jsonPath("$.qualiteIdentification").value("SUSPICION"))
            .andExpect(jsonPath("$.tailleEstimeIndividu").isEmpty())
            .andExpect(jsonPath("$.tempsApnee").value(60))
            .andExpect(jsonPath("$.isIndividu").value(false))
            .andExpect(jsonPath("$.estimationNbIndividus").value(4))
            .andExpect(jsonPath("$.ilot.id").value("ff80818167a5556c0167a555a31b001b"))
            .andExpect(jsonPath("$.ilot.titre").value("Îlot Nouma"))
            .andExpect(jsonPath("$.ilot.localisation").value("POINT (167.41780430910623 -22.628895306032415)"))
            .andExpect(jsonPath("$.ilot.pageWeb").value("https://www.province-sud.nc/pandoreweb/app/ilot/Nouma"))
            .andDo(print());
    }

    @Test
    public void testCreateFicheMammifereSansTempsApnee_Error() throws Exception {
        // GIVEN
        CreateFicheObservationDTO createFicheObservationDTO = CreateFicheObservationDTO.builder()
            .animalMarin(CACHALOT)
            .titreIlot("Îlot Nouma")
            .distanceBordIlot(10)
            .dateObservation(LocalDateTime.of(2023, 1, 18, 6, 30))
            .qualiteIdentification(VERIFIE)
            .tailleEstimeIndividu(16)
            .isIndividu(true)
            .build();

        // WHEN
        mockMvc.perform(post(API_CREATE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createFicheObservationDTO)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.tempsApnee").value("Le temps d'apnée est obligatoire pour les mammifères"))
            .andDo(print());
    }

    @Test
    public void testCreateFichePoissonSansTailleEstime_Error() throws Exception {
        // GIVEN
        CreateFicheObservationDTO createFicheObservationDTO = CreateFicheObservationDTO.builder()
            .animalMarin(THAZARD)
            .titreIlot("Îlot Nouma")
            .distanceBordIlot(10)
            .dateObservation(LocalDateTime.of(2023, 1, 18, 6, 30))
            .qualiteIdentification(VERIFIE)
            .isIndividu(true)
            .build();

        // WHEN
        mockMvc.perform(post(API_CREATE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createFicheObservationDTO)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.tailleEstimeIndividu").value("La taille estimée de l'invidu est obligatoire"))
            .andDo(print());
    }

    @Test
    public void testCreateFicheBancPoissonSansNombreIndividu_Error() throws Exception {
        // GIVEN
        CreateFicheObservationDTO createFicheObservationDTO = CreateFicheObservationDTO.builder()
            .animalMarin(THAZARD)
            .titreIlot("Îlot Nouma")
            .distanceBordIlot(10)
            .dateObservation(LocalDateTime.of(2023, 1, 18, 6, 30))
            .qualiteIdentification(VERIFIE)
            .isIndividu(false)
            .build();

        // WHEN
        mockMvc.perform(post(API_CREATE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createFicheObservationDTO)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.estimationNbIndividus").value("L'estimation du nombre d'individus est obligatoire pour les bancs de poissons"))
            .andDo(print());
    }
}
