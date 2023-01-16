package nc.observation.animaux.marins.dto.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = CreateFicheObservationValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CreateFicheObservationConstraint {

    String message() default "CreateFicheObservationConstraint";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
