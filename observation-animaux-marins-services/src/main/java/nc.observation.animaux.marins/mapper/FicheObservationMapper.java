package nc.observation.animaux.marins.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import nc.observation.animaux.marins.bean.CreateFicheObservation;
import nc.observation.animaux.marins.entity.FicheObservation;
import nc.observation.animaux.marins.entity.Ilot;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface FicheObservationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ilot", source = "ilot")
    FicheObservation toEntity(CreateFicheObservation createFicheObservation, Ilot ilot);
}
