package nc.observation.animaux.marins.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nc.observation.animaux.marins.enums.QualiteIdentification;
import nc.observation.animaux.marins.enums.TypeAnimalMarin;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FicheObservationDTO {

    private Long id;

    private TypeAnimalMarin animalMarin;

    private IlotDTO ilot;

    private int distanceBordIlot;

    private LocalDateTime dateObservation;

    private QualiteIdentification qualiteIdentification;

    private Integer tailleEstimeIndividu;

    private int tempsApnee;

    private Boolean isIndividu;

    private Integer estimationNbIndividus;
}
