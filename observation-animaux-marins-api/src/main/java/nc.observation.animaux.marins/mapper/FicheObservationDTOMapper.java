package nc.observation.animaux.marins.mapper;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import nc.observation.animaux.marins.bean.CreateFicheObservation;
import nc.observation.animaux.marins.dto.CreateFicheObservationDTO;
import nc.observation.animaux.marins.dto.FicheObservationDTO;
import nc.observation.animaux.marins.entity.FicheObservation;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, uses = IlotMapper.class)
public interface FicheObservationDTOMapper {

    FicheObservationDTO toDTO(FicheObservation ficheObservation);

    List<FicheObservationDTO> toDTOs(List<FicheObservation> ficheObservation);

    CreateFicheObservation toBean(CreateFicheObservationDTO dto);

    @AfterMapping
    default void afterToBean(@MappingTarget CreateFicheObservation.CreateFicheObservationBuilder builder, CreateFicheObservationDTO dto) {
        // Le temps d'apnée est nécessaire seulement pour les mammifères
        if (dto.getAnimalMarin().isPoisson()) {
            builder.tempsApnee(null);
        }

        // La taille estimée n'est nécessaire que pour les individus
        if (dto.getAnimalMarin().isPoisson() && !dto.getIsIndividu()) {
            builder.tailleEstimeIndividu(null);
        }
    }
}
