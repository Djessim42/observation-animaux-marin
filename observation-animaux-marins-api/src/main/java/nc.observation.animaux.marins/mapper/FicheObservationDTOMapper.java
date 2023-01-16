package nc.observation.animaux.marins.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import nc.observation.animaux.marins.bean.CreateFicheObservation;
import nc.observation.animaux.marins.dto.CreateFicheObservationDTO;
import nc.observation.animaux.marins.dto.FicheObservationDTO;
import nc.observation.animaux.marins.dto.IlotDTO;
import nc.observation.animaux.marins.entity.FicheObservation;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, uses = IlotDTO.class)
public interface FicheObservationDTOMapper {

    FicheObservationDTO toDTO(FicheObservation ficheObservation);

    List<FicheObservationDTO> toDTOs(List<FicheObservation> ficheObservation);

    CreateFicheObservation toBean(CreateFicheObservationDTO dto);
}
