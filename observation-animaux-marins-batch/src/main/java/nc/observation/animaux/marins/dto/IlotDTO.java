package nc.observation.animaux.marins.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IlotDTO {

    @NotNull
    private String id;

    @NotNull
    private String titre;

    @NotNull
    private String urlPageWeb;

    @NotNull
    private String localisation;
}
