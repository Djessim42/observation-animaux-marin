package nc.observation.animaux.marins.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IlotDTO {

    private String id;

    private String titre;

    private String localisation;

    private String pageWeb;
}
