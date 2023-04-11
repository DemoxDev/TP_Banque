package part2.banque;

import common.TextColor;

public final class Compte_Courant extends Compte {
	/* PROPRIÉTÉS/ATTRIBUTS */
	private double decouvert_autorise;
	private double solde;

	/* MÉTHODES */
	// GETTERS
	// Récupérer le solde du compte courant
	protected final double getSolde() {
		return this.solde;
	}

	// Récupérer le découvert autorisé du compte courant
	protected final double getDecouvert() {
		return decouvert_autorise;
	}

	// SETTERS
	// Définir le solde du compte courant
	protected final void setSolde(double solde) {
		this.solde = solde;
	}

	// Définir le découvert autorisé du compte courant
	protected final void setDecouvert(double decouvert_autorise) {
		this.decouvert_autorise = decouvert_autorise;
	}

	/* AUTRES MÉTHODES */
	// Effectuer un dépôt
	protected final void deposer(double val) {
		this.solde = this.getSolde() + val;
	}

	// Effectuer un retrait (retourne true si le retrait a été effectué)
	protected final boolean retirer(double val) {
		if (this.getSolde() - val < -this.getDecouvert()) {
			System.out.println(
					TextColor.RED + "Impossible de faire le retrait, vous dépassez votre limite de découvert."
							+ TextColor.RESET);
			return false;
		} else {
			this.solde -= val;
			return true;
		}
	}

	/* CONSTRUCTEUR */
	protected Compte_Courant(int numero, String titulaire, double solde, double decouvert_autorise) {
		super(numero, titulaire);
		this.decouvert_autorise = decouvert_autorise;
		this.solde = solde;
	}

	/* toString */
	public String toString() { // = consulte()
		return super.toString() + ", Découvert autorisé : " + this.decouvert_autorise + "EUR";
	}

}
