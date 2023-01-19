package nc.observation.animaux.marins.service;

import nc.observation.animaux.marins.entity.Ilot;

public interface IlotService {

    /**
     * Récupère un ilot à partir de son titre
     * @param titre de l'ilot
     * @return un ilot
     */
    Ilot findByTitre(String titre);
}
