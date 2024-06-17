package modele;

public class Transport {

    private TypeTransporteur type;

    public Transport(TypeTransporteur type) {
        this.type = type;
    }

    // Méthode pour calculer les frais de port en fonction du montant total
    public float calculerFraisDePort(float montantTotal) {
        switch (type) {
            case COLISSIMO:
                return calculerFraisColissimo(montantTotal);
            case CHRONORELAIS:
                return calculerFraisChronorelais(montantTotal);
            case CHRONOFRESH:
                return calculerFraisChronofresh(montantTotal);
        }
        return -1;
    }

    // Méthode privée pour les frais Colissimo
    private float calculerFraisColissimo(float montantTotal) {
    	if (montantTotal == 0) {
    		return 0.0F;
    	}
    	if (montantTotal < 60) {
            return 14.90F;
        } else if (montantTotal < 90) {
            return 9.90F;
        } else if (montantTotal < 120) {
            return 4.90F;
        } else {
            return 0.0F;
        }
    }

    // Méthode privée pour les frais Chronorelais
    private float calculerFraisChronorelais(float montantTotal) {
    	if (montantTotal == 0) {
    		return 0.0F;
    	}
        if (montantTotal < 60F) {
            return 14.90F;
        } else if (montantTotal < 90F) {
            return 9.90F;
        } else if (montantTotal < 120F) {
            return 4.90F;
        } else {
            return 0.0F;
        }
    }

    // Méthode privée pour les frais Chronofresh
    private float calculerFraisChronofresh(float montantTotal) {
    	if (montantTotal == 0) {
    		return 0.0F;
    	}
        if (montantTotal < 50F) {
            return 23.80F;
        } else if (montantTotal < 80F) {
            return 17.80F;
        } else if (montantTotal < 120F) {
            return 9.90F;
        } else {
            return 0.0F;
        }
    }
}