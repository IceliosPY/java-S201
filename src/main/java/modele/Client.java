package modele;

public class Client {
	
	private String nom; 
	private String prénom; 
	private String adresse1;
	private String adresse2; 
	private String codePostal; 
	private String ville; 
	private String téléphone;
	private String mail;
	
	public Client(String nom, String prénom, String adresse1, String adresse2, String codePostal, String ville,
			String téléphone, String mail) {
		super();
		this.nom = nom;
		this.prénom = prénom;
		this.adresse1 = adresse1;
		this.adresse2 = adresse2;
		this.codePostal = codePostal;
		this.ville = ville;
		this.téléphone = téléphone;
		this.mail = mail;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @return the prénom
	 */
	public String getPrénom() {
		return prénom;
	}

	/**
	 * @return the adresse1
	 */
	public String getAdresse1() {
		return adresse1;
	}

	/**
	 * @return the adresse2
	 */
	public String getAdresse2() {
		return adresse2;
	}

	/**
	 * @return the codePostal
	 */
	public String getCodePostal() {
		return codePostal;
	}

	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * @return the téléphone
	 */
	public String getTéléphone() {
		return téléphone;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @param prénom the prénom to set
	 */
	public void setPrénom(String prénom) {
		this.prénom = prénom;
	}

	/**
	 * @param adresse1 the adresse1 to set
	 */
	public void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}

	/**
	 * @param adresse2 the adresse2 to set
	 */
	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}

	/**
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

	/**
	 * @param téléphone the téléphone to set
	 */
	public void setTéléphone(String téléphone) {
		this.téléphone = téléphone;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public boolean informationsCompletes(){
		return (this.getNom() != null && !this.getNom().isEmpty() &&
	            this.getPrénom() != null && !this.getPrénom().isEmpty() &&
	            this.getAdresse1() != null && !this.getAdresse1().isEmpty() &&
	            this.getCodePostal() != null && !this.getCodePostal().isEmpty() &&
	            this.getVille() != null && !this.getVille().isEmpty() &&
	            this.getTéléphone() != null && !this.getTéléphone().isEmpty() &&
	            this.getMail() != null && !this.getMail().isEmpty());
	}
	
	

	
}
