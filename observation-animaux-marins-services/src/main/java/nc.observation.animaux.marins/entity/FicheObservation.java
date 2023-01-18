package nc.observation.animaux.marins.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import nc.observation.animaux.marins.enums.QualiteIdentification;
import nc.observation.animaux.marins.enums.TypeAnimalMarin;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "t_fiche_observation")
public class FicheObservation {

    @Id
    @Column(name = "fob_id")
    @EqualsAndHashCode.Include()
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "fob_type_animal_marin", nullable = false)
    private TypeAnimalMarin animalMarin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fob_ilot_id", referencedColumnName = "ilot_id", nullable=false)
    private Ilot ilot;

    @Column(name = "fob_distance_bord_ilot", nullable = false)
    private int distanceBordIlot; // En mètres

    @Column(name = "fob_date_observation", nullable = false)
    private LocalDateTime dateObservation;

    @Enumerated(EnumType.STRING)
    @Column(name = "fob_qualite_identification", nullable = false)
    private QualiteIdentification qualiteIdentification;

    @Column(name = "fob_temps_apnee")
    private int tempsApnee; // En secondes

    @Column(name = "fob_is_individu", nullable = false)
    private Boolean isIndividu;

    @Column(name = "fob_taille_estimee_individu")
    private Integer tailleEstimeIndividu; // En mètres

    @Column(name = "fob_nb_estime_individus")
    private Integer estimationNbIndividus;
}
