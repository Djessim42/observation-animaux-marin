package nc.observation.animaux.marins.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import nc.observation.animaux.marins.entity.Ilot;

@Repository
public interface IlotRepository extends JpaRepository<Ilot, String> {

    Optional<Ilot> findByTitre(@Param("titre") String titre);
}
