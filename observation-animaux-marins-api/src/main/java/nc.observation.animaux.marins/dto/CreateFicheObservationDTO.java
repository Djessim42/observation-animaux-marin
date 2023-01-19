package nc.observation.animaux.marins.dto;

import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @NotEmpty(message = "Le titre de l'ilot doit être renseigné")
    private String titreIlot;

    @NotNull(message = "L'animal marin doit être renseigné")
    private TypeAnimalMarin animalMarin;

    @NotNull(message = "La distance du bord de l'ilôt doit être renseignée")
    private Integer distanceBordIlot;

    @NotNull(message = "La date d'observation de l'animal doit être renseignée")
    private LocalDateTime dateObservation;

    @NotNull(message = "La qualité d'indentification doit être renseignée")
    private QualiteIdentification qualiteIdentification;

    @NotNull(message = "Le champ indiquant s'il s'agit d'un individu ou d'un banc doit être renseigné")
    private Boolean isIndividu;

    private Integer tailleEstimeIndividu;

    private Integer tempsApnee;

    private Integer estimationNbIndividus;

    @JsonIgnore
    public boolean isMammifere() {
        return Optional.ofNullable(animalMarin).map(TypeAnimalMarin::isMammifere).orElse(false);
    }

    @JsonIgnore
    public boolean isIndividuPoisson() {
        return Optional.ofNullable(animalMarin).map(a -> a.isPoisson() && this.isIndividu).orElse(false);
    }

    @JsonIgnore
    public boolean isBancPoisson() {
        return Optional.ofNullable(animalMarin).map(a -> a.isPoisson() && !this.isIndividu).orElse(false);
    }
}
