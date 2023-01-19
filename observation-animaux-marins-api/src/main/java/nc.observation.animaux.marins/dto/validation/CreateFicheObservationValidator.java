package nc.observation.animaux.marins.dto.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import nc.observation.animaux.marins.dto.CreateFicheObservationDTO;

public class CreateFicheObservationValidator implements ConstraintValidator<CreateFicheObservationConstraint, CreateFicheObservationDTO> {

    @Override
    public boolean isValid(CreateFicheObservationDTO ficheObservationDTO, ConstraintValidatorContext context) {

        if (ficheObservationDTO.isMammifere() && ficheObservationDTO.getTempsApnee() == null) {
            context.buildConstraintViolationWithTemplate(
                    "Le temps d'apnée est obligatoire pour les mammifères")
                .addPropertyNode("tempsApnee")
                .addConstraintViolation();
            return false;
        }

        if ((ficheObservationDTO.isMammifere() || ficheObservationDTO.isIndividuPoisson()) && ficheObservationDTO.getTailleEstimeIndividu() == null) {
            context.buildConstraintViolationWithTemplate(
                    "La taille estimée de l'invidu est obligatoire")
                .addPropertyNode("tailleEstimeIndividu")
                .addConstraintViolation();
            return false;
        }

        if (ficheObservationDTO.isBancPoisson() && ficheObservationDTO.getEstimationNbIndividus() == null) {
            context.buildConstraintViolationWithTemplate(
                    "L'estimation du nombre d'individus est obligatoire pour les bancs de poissons")
                .addPropertyNode("estimationNbIndividus")
                .addConstraintViolation();
            return false;
        }

        return true;
    }
}
