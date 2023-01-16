package nc.observation.animaux.marins.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import nc.observation.animaux.marins.dto.IlotDTO;
import nc.observation.animaux.marins.entity.Ilot;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface IlotMapper {

    IlotDTO toDTO(Ilot ilot);
}
