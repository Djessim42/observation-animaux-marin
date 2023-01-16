package nc.observation.animaux.marins.dto.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import nc.observation.animaux.marins.dto.FicheObservationDTO;

public class CreateFicheObservationValidator implements ConstraintValidator<CreateFicheObservationConstraint, FicheObservationDTO> {

    @Override
    public boolean isValid(FicheObservationDTO ficheObservationDTO, ConstraintValidatorContext context) {

        // Le temps d'apnée est obligatoire pour les mammifères
        if (ficheObservationDTO.getAnimalMarin().isMammifere() && ficheObservationDTO.getTempsApnee() == null) {
            context.buildConstraintViolationWithTemplate(
                    "Le temps d'apnée est obligatoire pour les mammifères")
                .addPropertyNode("tempsApnee")
                .addConstraintViolation();
            return false;
        }

        // Le temps d'apnée est obligatoire pour les mammifères
        if (ficheObservationDTO.getAnimalMarin().isMammifere() && ficheObservationDTO.getTailleEstimeIndividu() == null) {
            context.buildConstraintViolationWithTemplate(
                    "La taille estimée de l'invidu est obligatoire pour les mammifères")
                .addPropertyNode("tailleEstimeIndividu")
                .addConstraintViolation();
            return false;
        }

        // Pour les poissons, il est obligatoire de savoir s'il sagit d'un individu ou d'un banc
        if (ficheObservationDTO.getAnimalMarin().isPoisson() && ficheObservationDTO.getIsIndividu() == null) {
            context.buildConstraintViolationWithTemplate(
                    "Le champ isIndividu est obligatoire pour les poissons")
                .addPropertyNode("isIndividu")
                .addConstraintViolation();
            return false;
        }

        return true;
    }
}
