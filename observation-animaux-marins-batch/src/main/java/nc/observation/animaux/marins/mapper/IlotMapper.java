package nc.observation.animaux.marins.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import nc.observation.animaux.marins.dto.IlotDTO;
import nc.observation.animaux.marins.entity.Ilot;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface IlotMapper {

    @Mapping(target = "pageWeb", source = "urlPageWeb")
    Ilot toEntity(IlotDTO dto);
}
