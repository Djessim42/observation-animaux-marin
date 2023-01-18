package nc.observation.animaux.marins.enums;

import lombok.Getter;

@Getter
public enum TypeAnimalMarin {
    BALEINE_A_BOSSE(false),
    DAUPHIN_TURSIOPE(false),
    DUGONG(false),
    CACHALOT(false),
    RAIE_MANTA(true),
    THON_A_DENT_DE_CHIEN(true),
    THAZARD(true);

    TypeAnimalMarin(boolean isPoisson) {
        this.isPoisson = isPoisson;
    }

    private boolean isPoisson;

    public boolean isMammifere() {
        return !this.isPoisson;
    }
}
