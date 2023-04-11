package part2.banque;

import common.TextColor;

public final class Compte_Epargne extends Compte {
	private double taux;
	private double solde;

	/* MÉTHODES */
	// GETTERS
	// Récupérer le solde
	protected final double getSolde() {
		return this.solde;
	}

	// Récupérer le solde
	protected final double getTaux() {
		return this.taux;
	}

	// SETTERS
	// Définir le solde du compte épargne
	protected final void setSolde(double solde) {
		this.solde = solde;
	}

	// Définir le taux du compte épargne
	protected final void setTaux(double taux) { // = mise_a_jour() (voir PortefeuilleClients.java)
		this.taux = taux;
	}

	/* AUTRES MÉTHODES */

	// Effectuer un dépot
	protected final void deposer(double val) {
		this.solde = this.getSolde() + val;
	}

	// Effectuer un retrait (retourne true si le retrait a été effectué)
	protected final boolean retirer(double val) {
		if (this.getSolde() - val < 0) {
			System.out.println(
					TextColor.RED + "Impossible de faire le retrait, vous ne pouvez pas avoir une épargne négative."
							+ TextColor.RESET);
			return false;
		} else {
			this.solde -= val;
			return true;
		}
	}

	/* CONSTRUCTEUR */
	protected Compte_Epargne(int numero, String titulaire, double solde, double taux) {
		super(numero, titulaire);
		this.solde = solde;
		this.taux = taux;
	}

	/* toString */
	public final String toString() { // = consulte()
		return "Montant d'épargne: " + this.getSolde() + "EUR, Taux d'épargne: " + this.taux + "%";
	}
}
