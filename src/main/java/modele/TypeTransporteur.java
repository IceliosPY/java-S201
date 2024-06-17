package modele;

public enum TypeTransporteur {
    COLISSIMO("Colissimo","Colissimo.jpg"),
    CHRONORELAIS("Chronorelais","Chronorelais.png"),
    CHRONOFRESH("Chronofresh", "Chronofresh.jpg");

    private String nomTransporteur;
    private String nomImage;

    private TypeTransporteur(String nomTransporteur, String nomImage) {
        this.nomTransporteur = nomTransporteur;
        this.nomImage = nomImage;
    }

    public String getNomTransporteur() {
        return this.nomTransporteur;
    }
    
    public String getNomImage() {
        return this.nomImage;
    }

    public static TypeTransporteur getTypeTransporteur(String dénomination) {
        for (TypeTransporteur t : TypeTransporteur.values()) {
            if (t.getNomTransporteur().equals(dénomination)) {
                return t;
            }
        }
        return null;
    }
}
