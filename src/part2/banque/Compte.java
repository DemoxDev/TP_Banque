package part2.banque;

public abstract class Compte {
	/* PROPRIÉTÉS/ATTRIBUTS */
	private String titulaire;
	private int numero;

	/* MÉTHODES */
	// GETTERS
	// Récupérer le titulaire du compte
	protected final String getTitulaire() {
		return this.titulaire;
	}

	// Récupérer le numéro du compte
	public final int getNumero() {
		return this.numero;
	}

	// Récupérer le solde du compte
	protected abstract double getSolde();

	// SETTERS
	// Définir le titulaire du compte
	protected final void setTitulaire(String titulaire) {
		this.titulaire = titulaire;
	}

	// Définir le numéro du compte
	protected final void setNumero(int numero) {
		this.numero = numero;
	}

	// Définir le solde du compte
	protected abstract void setSolde(double solde);

	/* AUTRES MÉTHODES */
	// Effectuer un dépôt
	protected abstract void deposer(double val);

	// Effectuer un retrait (doit retourner true si le retrait a été effectué)
	protected abstract boolean retirer(double val);

	/* CONSTRUCTEUR */
	protected Compte(int numero, String titulaire) {
		this.numero = numero;
		this.titulaire = titulaire;
	}

	/* toString */
	public String toString() { // = consulte()
		return "Client N° " + this.getNumero()
				+ ", Nom: " + this.getTitulaire()
				+ ", Solde: " + this.getSolde() + "EUR";
	}

}
