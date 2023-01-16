package nc.observation.animaux.marins.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "t_ilot")
public class Ilot {

    @Id
    @Column(name = "ilot_id")
    @EqualsAndHashCode.Include()
    private String id;

    @Column(name = "ilot_titre", nullable = false)
    private String titre;

    @Column(name = "ilot_localisation", nullable = false)
    private String localisation;

    @Column(name = "ilot_page_web", nullable = false)
    private String pageWeb;
}
