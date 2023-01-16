package nc.observation.animaux.marins.items;


import java.util.Collections;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.database.AbstractPagingItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import nc.observation.animaux.marins.dto.GetIlotDTO;
import nc.observation.animaux.marins.dto.IlotDTO;

@Component
public class ImportIlotItemReader extends AbstractPagingItemReader<IlotDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImportIlotItemReader.class);

    private static final int PAGE_SIZE = 50;

    private final String ilotUrl;
    private final RestTemplate restTemplate;

    public ImportIlotItemReader(@Value("${application.ilot.url}") String ilotUrl,
        RestTemplate restTemplate) {
        this.ilotUrl = ilotUrl;
        this.restTemplate = restTemplate;
        setPageSize(PAGE_SIZE);
    }

    @Override
    protected void doReadPage() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        LOGGER.info("Récupération des ilots avec comme paramètres [pageSize : {}, page : {}]", this.getPageSize(), this.getPage());
        try {
            ResponseEntity<GetIlotDTO> responseEntity = restTemplate.exchange(
                ilotUrl, HttpMethod.GET, entity, GetIlotDTO.class, this.getPageSize(), this.getPageSize() * this.getPage());
            this.results = Optional.ofNullable(responseEntity.getBody()).map(GetIlotDTO::getIlots).orElse(Collections.emptyList());
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des ilots", e);
        }
    }
}
