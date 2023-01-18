package nc.observation.animaux.marins.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nc.observation.animaux.marins.bean.CreateFicheObservation;
import nc.observation.animaux.marins.entity.FicheObservation;
import nc.observation.animaux.marins.entity.Ilot;
import nc.observation.animaux.marins.enums.TypeAnimalMarin;
import nc.observation.animaux.marins.mapper.FicheObservationMapper;
import nc.observation.animaux.marins.repository.FicheObservationRepository;
import nc.observation.animaux.marins.service.FicheObservationService;
import nc.observation.animaux.marins.service.IlotService;

@Service
@Transactional
public class FicheObservationServiceImpl implements FicheObservationService {

    private final IlotService ilotService;
    private final FicheObservationMapper ficheObservationMapper;
    private final FicheObservationRepository ficheObservationRepository;

    public FicheObservationServiceImpl(IlotService ilotService, FicheObservationMapper ficheObservationMapper,
        FicheObservationRepository ficheObservationRepository) {
        this.ilotService = ilotService;
        this.ficheObservationMapper = ficheObservationMapper;
        this.ficheObservationRepository = ficheObservationRepository;
    }

    @Override
    public List<FicheObservation> getFiches(TypeAnimalMarin animalMarin) {
        return ficheObservationRepository.findAllByType(animalMarin);
    }

    @Override
    public FicheObservation createFicheObservation(CreateFicheObservation createFicheObservationDTO) {

        // On récupère l'ilot associé à la fiche
        Ilot ilot = ilotService.findByTitre(createFicheObservationDTO.getTitreIlot());

        // On sauvegarde la nouvelle fiche en base
        return ficheObservationRepository.save(ficheObservationMapper.toEntity(createFicheObservationDTO, ilot));
    }
}
