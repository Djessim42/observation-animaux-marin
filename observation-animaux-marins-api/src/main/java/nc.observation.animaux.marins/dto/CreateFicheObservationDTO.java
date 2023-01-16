package nc.observation.animaux.marins.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nc.observation.animaux.marins.dto.validation.CreateFicheObservationConstraint;
import nc.observation.animaux.marins.enums.QualiteIdentification;
import nc.observation.animaux.marins.enums.TypeAnimalMarin;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@CreateFicheObservationConstraint
public class CreateFicheObservationDTO {

    @NotEmpty
    private String titreIlot;

    @NotNull
    private TypeAnimalMarin animalMarin;

    @NotNull
    private Integer distanceBordIlot;

    @NotNull
    private LocalDateTime dateObservation;

    @NotNull
    private QualiteIdentification qualiteIdentification;

    private Integer tailleEstimeIndividu;

    private Integer tempsApnee;

    @NotNull
    private Boolean isIndividu;

    private Integer estimationNbIndividu;
}
