package nc.observation.animaux.marins.service;


import java.util.List;

import nc.observation.animaux.marins.bean.CreateFicheObservation;
import nc.observation.animaux.marins.entity.FicheObservation;
import nc.observation.animaux.marins.enums.TypeAnimalMarin;

public interface FicheObservationService {

    List<FicheObservation> getFiches(TypeAnimalMarin typeAnimalMarin);

    FicheObservation createFicheObservation(CreateFicheObservation createFicheObservationDTO);
}
