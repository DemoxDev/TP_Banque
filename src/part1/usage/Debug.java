package part1.usage;

import part1.banque.Compte;
import part1.banque.PortefeuilleClients;
// import common.Saisie; // Utilisé pour le mode de saisie manuel

public class Debug {

	public static void main(String[] args) {
		System.out.println("Exécution du programe de gestion de compte partie 1 ");

		PortefeuilleClients MonPF = new PortefeuilleClients();

		int n;
		// Saisie utilisateur manuelle
		// n = Saisie.lire_int("Combien de comptes voulez-vous créer ?");
		// MonPF.ajouteComptes(n);

		// Création automatique
		n = 3;
		for (int i = 0; i < n; i++) {
			System.out.println("Création du compte " + i);
			Compte unCompte = new Compte("Utilisateur " + i, i, i * -1.0, 0.0);
			MonPF.getCollecComptes().add(unCompte);
			MonPF.setObjectsNumber(MonPF.getObjectsNumber() + 1);
		}

		// // Test du découvert
		// System.out.println("Portefeuille avant modification découvert: " +
		// MonPF.toString());
		// MonPF.modifierDecouvert(2, 200.0);
		// System.out.println("Portefeuille après modification découvert: " +
		// MonPF.toString());

		// // Test de la suppression de compte
		// System.out.println("Portefeuille avant suppression compte: " +
		// MonPF.toString());
		// MonPF.supprimerCompte(1);
		// System.out.println("Portefeuille après suppression compte: " +
		// MonPF.toString());

		// // Test de vidage de la collection
		// System.out.println("Portefeuille avant suppression de tous les comptes: \n" +
		// MonPF.toString());
		// MonPF.viderComptes();
		// System.out.println("Portefeuille après suppression compte: \n" +
		// MonPF.toString());

		// // Consultation de compte
		// n = Saisie.lire_int("Quel numéro de compte souhaitez-vous consulter ?");
		// MonPF.consulterCompte(n);

		// Consultation des comptes à solde négatifs
		// System.out.println("\nComptes à solde négatif:");
		// System.out.println(MonPF.afficherSoldesNegatifs());
	}

}
