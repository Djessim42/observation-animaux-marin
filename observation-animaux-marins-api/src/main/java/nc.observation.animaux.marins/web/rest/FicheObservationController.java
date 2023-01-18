package nc.observation.animaux.marins.web.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
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
    public ResponseEntity<FicheObservationDTO> createFiche(@Valid @RequestBody CreateFicheObservationDTO createFicheObservationDTO) {
        FicheObservation ficheObservation = ficheObservationService.createFicheObservation(ficheObservationDTOMapper.toBean(createFicheObservationDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(ficheObservationDTOMapper.toDTO(ficheObservation));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
        MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            if (error instanceof FieldError fieldError) {
                String fieldName = fieldError.getField();
                String errorMessage = fieldError.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            }
        });
        return errors;
    }
}
