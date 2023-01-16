package nc.observation.animaux.marins.bean;

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
public class CreateFicheObservation {

    private String titreIlot;

    private TypeAnimalMarin animalMarin;

    private Integer distanceBordIlot;

    private LocalDateTime dateObservation;

    private QualiteIdentification qualiteIdentification;

    private Integer tailleEstimeIndividu;

    private Integer tempsApnee;

    private Boolean isIndividu;

    private Integer estimationNbIndividu;
}