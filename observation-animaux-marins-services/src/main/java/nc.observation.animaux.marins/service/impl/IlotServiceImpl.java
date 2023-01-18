package nc.observation.animaux.marins.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import nc.observation.animaux.marins.entity.Ilot;
import nc.observation.animaux.marins.repository.IlotRepository;
import nc.observation.animaux.marins.service.IlotService;

@Service
@Transactional
public class IlotServiceImpl implements IlotService {

    private final IlotRepository ilotRepository;

    public IlotServiceImpl(IlotRepository ilotRepository) {
        this.ilotRepository = ilotRepository;
    }

    @Override
    public Ilot findByTitre(String titre) {
        return this.ilotRepository.findByTitre(titre)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("L'ilot %s n'a pas été trouvé", titre)));
    }
}
