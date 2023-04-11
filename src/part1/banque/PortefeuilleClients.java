package part1.banque;

import java.util.ArrayList;
import java.util.ListIterator;

public class PortefeuilleClients {
	/* PROPRIÉTÉS/ATTRIBUTS */
	private ArrayList<Compte> collecComptes;
	private int objectsNumber;

	/* MÉTHODES */
	// SETTERS
	public void setObjectsNumber(int n) {
		this.objectsNumber = n;
	}

	// GETTERS
	public int getObjectsNumber() {
		return objectsNumber;
	}

	public ArrayList<Compte> getCollecComptes() {
		return collecComptes;
	}

	/* AUTRES MÉTHODES */
	public void modifierDecouvert(int nCompte, double d) {
		String message = "Il n'y a aucun compte à ce numéro dans cette banque !";
		ListIterator<Compte> it = this.getCollecComptes().listIterator();
		while (it.hasNext()) {
			Object Obj = it.next();
			Compte unCompte = (Compte) Obj;
			if (unCompte.getNumero() == nCompte) {
				unCompte.setDecouvert(d);
				message = "Le découvert du compte " + nCompte + " a été modifié à " + d + " EUR";
			}
		}
		System.out.println(message);
	}

	public String afficherSoldesNegatifs() {
		String message = "";

		if (this.getCollecComptes().isEmpty()) {
			message = "Il n'y a aucun compte dans cette banque";
		} else {
			ListIterator<Compte> it = this.getCollecComptes().listIterator();
			while (it.hasNext()) {
				Object Obj = it.next();
				Compte unCompte = (Compte) Obj;
				if (unCompte.getSolde() < 0)
					message += "Solde du compte " + unCompte.getNumero() + ": " + unCompte.getSolde() + " EUR\n";
			}
		}
		if (message == "")
			message = "Il n'y a aucun compte à solde négatif dans cette banque !";
		return message;
	}

	/* AUTRES MÉTHODES */
	public void ajouteComptes(int n) {
		for (int i = 0; i < n; i++) {
			System.out.println("Création du compte " + n);
			Compte unCompte = new Compte();
			this.getCollecComptes().add(unCompte);
			setObjectsNumber(this.getObjectsNumber() + 1);
		}
	}

	public void consulterCompte(int nCompte) {
		String message = "Aucun compte ne correspond à ce numéro de compte !";
		if (this.getCollecComptes().isEmpty()) {
			message = "Il n'y a aucun compte à consulter";
		} else {
			ListIterator<Compte> it = this.getCollecComptes().listIterator();
			while (it.hasNext()) {
				Object Obj = it.next();
				Compte unCompte = (Compte) Obj;
				if (unCompte.getNumero() == nCompte)
					message = "Solde du compte " + nCompte + ": " + unCompte.getSolde() + " EUR";
			}
		}
		System.out.println(message);
	}

	public void supprimerCompte(int nCompte) {
		String message = "Aucun compte ne correspond à ce numéro de compte !";
		if (this.getCollecComptes().isEmpty()) {
			message = "Il n'y a aucun compte à supprimer";
		} else {
			for (int compte = 0; compte < this.getCollecComptes().size(); compte++) {
				if (this.getCollecComptes().get(compte).getNumero() == nCompte) {
					message = "Suppression du compte " + nCompte;
					this.getCollecComptes().remove(compte);
				}
			}
		}
		System.out.println(message);
	}

	public void viderComptes() {
		String message = "";
		if (this.getCollecComptes().isEmpty()) {
			message = "Il n'y a aucun compte à supprimer";
		} else {
			this.getCollecComptes().clear();
			message = "Tous les comptes ont été supprimés !";
		}
		System.out.println(message);
	}

	// toString
	public String toString() {
		String liste = "";
		if (this.getCollecComptes().isEmpty()) {
			liste += "Aucun client n'a de compte dans cette banque !\n";
		} else {
			liste += "Voici la liste de nos clients :\n";
			ListIterator<Compte> it = this.getCollecComptes().listIterator();
			while (it.hasNext()) {
				Object Obj = it.next();
				Compte unCompte = (Compte) Obj;
				liste += unCompte.toString() + "\n";
			}
		}
		return liste;
	}

	/* CONSTRUCTEUR */
	public PortefeuilleClients() {
		this.collecComptes = new ArrayList<Compte>();
		setObjectsNumber(0);
	}
}