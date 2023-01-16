package nc.observation.animaux.marins.service;

import nc.observation.animaux.marins.entity.Ilot;

public interface IlotService {

    Ilot findByTitre(String nom);
}
