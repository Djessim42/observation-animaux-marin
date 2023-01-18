package nc.observation.animaux.marins.items;

import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import nc.observation.animaux.marins.dto.IlotDTO;
import nc.observation.animaux.marins.mapper.IlotMapper;
import nc.observation.animaux.marins.entity.Ilot;

@Component
public class ImportIlotItemProcessor implements ItemProcessor<IlotDTO, Ilot> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImportIlotItemProcessor.class);

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    private final IlotMapper ilotMapper;

    public ImportIlotItemProcessor(IlotMapper ilotMapper) {
        this.ilotMapper = ilotMapper;
    }

    @Override
    public Ilot process(IlotDTO ilotDTO) {
        Set<ConstraintViolation<IlotDTO>> violations = factory.getValidator().validate(ilotDTO);

        if (CollectionUtils.isNotEmpty(violations)) {
            String violationsMessage = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", "));
            LOGGER.warn("Erreur de validation de l'ilot : {} : {}", ilotDTO, violationsMessage);
            return null;
        }
        return ilotMapper.toEntity(ilotDTO);
    }
}
