package nc.observation.animaux.marins.config;

public final class Constants {

    public static final String API_ILOT_RESPONSE = """
        {
            "total": 202,
            "size": "1738 bytes",
            "success": true,
            "msg": "direct",
            "acl": {},
            "data": [
                {
                    "id": "ff80818167a5556c0167a5559f2c0007",
                    "acl": {},
                    "close": false,
                    "titre": "Récif Mendigué",
                    "urlPageWeb": "https://www.province-sud.nc/pandoreweb/app/ilot/Récif-Mendigué",
                    "localisation": "POINT (166.48788888050734 -21.71354567432781)",
                    "photoPrincipale": null
                },
                {
                    "id": "ff80818167a5556c0167a5559f740009",
                    "acl": {},
                    "close": false,
                    "titre": "Récif émergé",
                    "urlPageWeb": "https://www.province-sud.nc/pandoreweb/app/ilot/Récif-émergé-bis",
                    "localisation": "POINT (165.41177570577474 -21.62263787308672)",
                    "photoPrincipale": null
                },
                {
                    "id": "ff80818167a5556c0167a555a7e50056",
                    "acl": {},
                    "close": false,
                    "titre": "Îlot Tapoué",
                    "urlPageWeb": "https://www.province-sud.nc/pandoreweb/app/ilot/Tapoué",
                    "localisation": "POINT (166.10652709356654 -22.024985452842138)",
                    "photoPrincipale": {
                        "id": "ff80818167b930800167b93fd04106d8",
                        "nom": "080703 Ilot Tapoué 001.JPG.jpeg",
                        "pathHd": "https://www.province-sud.nc/pandoreweb/pandore/photo/PhotoFile/ff80818167b930800167b93fdc3206dd/fichier?_responseMode=binary",
                        "priorite": null,
                        "copyright": "Martial Dosdane",
                        "pathVignette": "https://www.province-sud.nc/pandoreweb/pandore/photo/PhotoFile/ff80818167b930800167b93fd80f06dc/fichier?_responseMode=binary"
                    }
                },
                {
                    "id": "ff80818167a5556c0167a555a31b001b",
                    "acl": {},
                    "close": false,
                    "titre": "Îlot Nouma",
                    "urlPageWeb": "https://www.province-sud.nc/pandoreweb/app/ilot/Nouma",
                    "localisation": "POINT (167.41780430910623 -22.628895306032415)",
                    "photoPrincipale": null
                },
                {
                    "id": "ff80818167a5556c0167a555a350001c",
                    "acl": {},
                    "close": false,
                    "titre": "Îlot Yuèpè",
                    "urlPageWeb": "https://www.province-sud.nc/pandoreweb/app/ilot/Yuèpè",
                    "localisation": "POINT (167.44008458013084 -22.536444636785426)",
                    "photoPrincipale": null
                }
            ]
        }
        """;

    public static final String API_ILOT_EMPTY_RESPONSE = """
        {
        	"total": 202,
        	"size": "0 bytes",
        	"success": true,
        	"msg": "direct",
        	"acl": {},
        	"data": []
        }
        """;

    private Constants() {
        // Empty OK
    }
}
