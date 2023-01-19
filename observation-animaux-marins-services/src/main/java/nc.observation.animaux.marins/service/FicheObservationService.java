package nc.observation.animaux.marins.service;


import java.util.List;

import nc.observation.animaux.marins.bean.CreateFicheObservation;
import nc.observation.animaux.marins.entity.FicheObservation;
import nc.observation.animaux.marins.enums.TypeAnimalMarin;

public interface FicheObservationService {

    /**
     * Récupère l'ensemble des fiches d'observations filtrées par animal marin
     * @param typeAnimalMarin type d'animal sur lequel filtrer
     * @return une liste de FicheObservation
     */
    List<FicheObservation> getFiches(TypeAnimalMarin typeAnimalMarin);

    /**
     * Crée une fiche d'observation à partir des données en paramètre
     * @param createFicheObservationDTO DTO de création
     * @return la fiche créée
     */
    FicheObservation createFicheObservation(CreateFicheObservation createFicheObservationDTO);
}
