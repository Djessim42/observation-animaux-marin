package nc.observation.animaux.marins.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import nc.observation.animaux.marins.entity.FicheObservation;
import nc.observation.animaux.marins.enums.TypeAnimalMarin;

@Repository
public interface FicheObservationRepository extends JpaRepository<FicheObservation, Long> {

    /**
     * Récupère l'ensemble des titres d'observations filtrées (ou non) par animal marin
     * @param typeAnimalMarin type d'animal marin
     * @return une liste de FicheObservation
     */
    @Query("select f from FicheObservation f inner join f.ilot where :animalMarin is null or f.animalMarin = :animalMarin order by f.dateObservation")
    List<FicheObservation> findAllByType(@Param("animalMarin")TypeAnimalMarin typeAnimalMarin);
}
