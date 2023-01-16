package nc.observation.animaux.marins.web.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nc.observation.animaux.marins.dto.CreateFicheObservationDTO;
import nc.observation.animaux.marins.dto.FicheObservationDTO;
import nc.observation.animaux.marins.entity.FicheObservation;
import nc.observation.animaux.marins.enums.TypeAnimalMarin;
import nc.observation.animaux.marins.mapper.FicheObservationDTOMapper;
import nc.observation.animaux.marins.service.FicheObservationService;

@RestController
@RequestMapping("/api/fiches-observations")
public class FicheObservationController {

    private final FicheObservationService ficheObservationService;
    private final FicheObservationDTOMapper ficheObservationDTOMapper;

    public FicheObservationController(FicheObservationService ficheObservationService, FicheObservationDTOMapper ficheObservationDTOMapper) {
        this.ficheObservationService = ficheObservationService;
        this.ficheObservationDTOMapper = ficheObservationDTOMapper;
    }

    @GetMapping
    public ResponseEntity<List<FicheObservationDTO>> getFiches(@RequestParam(required = false)TypeAnimalMarin animalMarin) {
        return ResponseEntity.ok().body(ficheObservationDTOMapper.toDTOs(ficheObservationService.getFiches(animalMarin)));
    }

    @PostMapping("/create")
    public ResponseEntity<FicheObservationDTO> createFiche(@RequestBody CreateFicheObservationDTO createFicheObservationDTO) {
        FicheObservation ficheObservation = ficheObservationService.createFicheObservation(ficheObservationDTOMapper.toBean(createFicheObservationDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(ficheObservationDTOMapper.toDTO(ficheObservation));
    }
}
