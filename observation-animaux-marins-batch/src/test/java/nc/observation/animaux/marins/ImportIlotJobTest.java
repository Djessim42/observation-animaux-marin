package nc.observation.animaux.marins;

import static nc.observation.animaux.marins.config.Constants.API_ILOT_EMPTY_RESPONSE;
import static nc.observation.animaux.marins.config.Constants.API_ILOT_RESPONSE;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestToUriTemplate;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import java.util.List;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import nc.observation.animaux.marins.config.BatchTestConfiguration;
import nc.observation.animaux.marins.entity.Ilot;
import nc.observation.animaux.marins.repository.IlotRepository;

@SpringBatchTest
@SpringJUnitConfig(BatchTestConfiguration.class)
@TestPropertySource(locations = "classpath:application-batch.properties")
public class ImportIlotJobTest {

    @Value("${application.ilot.url}")
    private String url;

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IlotRepository ilotRepository;

    @Test
    public void testJobImportIlot() throws Exception {
        // GIVEN
        // Mock de la réponse de l'API des ilots
        MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);
        mockServer.expect(once(), requestToUriTemplate(url, "50", "0")).andExpect(method(GET))
            .andRespond(withStatus(OK).contentType(MediaType.APPLICATION_JSON).body(API_ILOT_RESPONSE));
        mockServer.expect(once(), requestToUriTemplate(url, "50", "50")).andExpect(method(GET))
            .andRespond(withStatus(OK).contentType(MediaType.APPLICATION_JSON).body(API_ILOT_EMPTY_RESPONSE));

        // WHEN
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        // THEN
        assertThat(jobExecution.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);
        assertThat(jobExecution.getStepExecutions()).singleElement().satisfies(stepExecution -> {
            assertThat(stepExecution.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);
            assertThat(stepExecution.getReadCount()).isEqualTo(5);
            assertThat(stepExecution.getWriteCount()).isEqualTo(4);
            assertThat(stepExecution.getFilterCount()).isEqualTo(1); // Erreur de validation
        });

        List<Ilot> result = ilotRepository.findAll();
        assertThat(result).hasSize(4)
            .anySatisfy(ilot -> {
                AssertionsForClassTypes.assertThat(ilot.getTitre()).isEqualTo("Récif Mendigué");
                AssertionsForClassTypes.assertThat(ilot.getId()).isEqualTo("ff80818167a5556c0167a5559f2c0007");
                AssertionsForClassTypes.assertThat(ilot.getLocalisation()).isEqualTo("POINT (166.48788888050734 -21.71354567432781)");
                AssertionsForClassTypes.assertThat(ilot.getPageWeb()).isEqualTo("https://www.province-sud.nc/pandoreweb/app/ilot/Récif-Mendigué");
            })
            .anySatisfy(ilot -> {
                AssertionsForClassTypes.assertThat(ilot.getTitre()).isEqualTo("Îlot Tapoué");
                AssertionsForClassTypes.assertThat(ilot.getId()).isEqualTo("ff80818167a5556c0167a555a7e50056");
                AssertionsForClassTypes.assertThat(ilot.getLocalisation()).isEqualTo("POINT (166.10652709356654 -22.024985452842138)");
                AssertionsForClassTypes.assertThat(ilot.getPageWeb()).isEqualTo("https://www.province-sud.nc/pandoreweb/app/ilot/Tapoué");
            })
            .anySatisfy(ilot -> {
                AssertionsForClassTypes.assertThat(ilot.getTitre()).isEqualTo("Îlot Nouma");
                AssertionsForClassTypes.assertThat(ilot.getId()).isEqualTo("ff80818167a5556c0167a555a31b001b");
                AssertionsForClassTypes.assertThat(ilot.getLocalisation()).isEqualTo("POINT (167.41780430910623 -22.628895306032415)");
                AssertionsForClassTypes.assertThat(ilot.getPageWeb()).isEqualTo("https://www.province-sud.nc/pandoreweb/app/ilot/Nouma");
            })
            .anySatisfy(ilot -> {
                AssertionsForClassTypes.assertThat(ilot.getTitre()).isEqualTo("Îlot Yuèpè");
                AssertionsForClassTypes.assertThat(ilot.getId()).isEqualTo("ff80818167a5556c0167a555a350001c");
                AssertionsForClassTypes.assertThat(ilot.getLocalisation()).isEqualTo("POINT (167.44008458013084 -22.536444636785426)");
                AssertionsForClassTypes.assertThat(ilot.getPageWeb()).isEqualTo("https://www.province-sud.nc/pandoreweb/app/ilot/Yuèpè");
            });
    }
}
