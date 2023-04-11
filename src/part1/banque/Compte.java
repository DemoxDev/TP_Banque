package part1.banque;

import common.Saisie;

public class Compte {
	/* PROPRIÉTÉS/ATTRIBUTS */
	private String titulaire;
	private int numero;
	private double solde;
	private double decouvert;

	/* MÉTHODES */
	// GETTERS
	public String getTitulaire() {
		return this.titulaire;
	}

	public int getNumero() {
		return this.numero;
	}

	public double getSolde() {
		return this.solde;
	}

	public double getDecouvert() {
		return this.decouvert;
	}

	// SETTERS
	public void setTitulaire(String titulaire) {
		this.titulaire = titulaire;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}

	/* AUTRES MÉTHODES */
	public void depot(double somme) {
		solde = solde + somme;

	}

	public void retrait(double somme) {
		if ((solde - somme) < decouvert) {
			System.out.println("Retrait impossible");
		} else
			solde = solde - somme;

	}

	/* CONSTRUCTEUR */
	public Compte() {
		setTitulaire(Saisie.lire_String("Quel est le nom du client ?"));
		setNumero(Saisie.lire_int("Quel est le numéro de ce compte ?"));
		setSolde(Saisie.lire_double("Quel est le solde intitial ?"));
		setDecouvert(Saisie.lire_double("Quel est le montant du decouvert autorisé ?"));
	}

	public Compte(String titulaire, int numero, double solde, double decouvert) {
		setTitulaire(titulaire);
		setNumero(numero);
		setSolde(solde);
		setDecouvert(decouvert);
	}

	public String toString() {
		return "Client " + this.getTitulaire() + ", votre compte dont le numéro est " + this.getNumero()
				+ " présente un solde de "
				+ this.getSolde() + " EUR"
				+ "\nVotre découvert autorisé est de " + this.getDecouvert() + " EUR";
	}

}
