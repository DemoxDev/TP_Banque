package part2.usage;

//import common.Saisie;
import common.TextColor;
import part2.banque.PortefeuilleClients;

public final class Debug {

	public final static void main(String[] args) {
		System.out.println(TextColor.YELLOW + "[DEV/DEBUG] " + TextColor.RESET
				+ "Exécution du programe de gestion de compte (partie 2)"
				+ TextColor.RESET);

		// Menu portefeuille
		final PortefeuilleClients MonPF = new PortefeuilleClients();
		MonPF.menuPortefeuille();

		// Création de comptes
		MonPF.ajouteComptes();

		// À FAIRE:
		// AJOUTER POSSIBILITÉ DE CRÉER UN PORTEFEUILLE DANS RunAdmin.menuGeneral()
		// VÉRIFIER ENCAPSULATION (PROTECTED etc...)
		// DOCUMENTATION (commentaires etc...)

		// // Test de la suppression de compte (fonctionnel compilation 21/10/22)
		// System.out.println("Portefeuille avant suppression compte: ");
		// System.out.println(MonPF.toString());
		// MonPF.supprimerCompte(1);
		// System.out.println("Portefeuille après suppression compte: ");
		// System.out.println(MonPF.toString());

		// // Test de vidage de la collection (fonctionnel compilation 21/10/22)
		// System.out.println("Portefeuille avant suppression de tous les comptes: \n");
		// System.out.println(MonPF.toString());
		// MonPF.viderComptes();
		// System.out.println("Portefeuille après suppression de tous les comptes: \n");
		// System.out.println(MonPF.toString());

		// // Consultation de compte (fonctionnel compilation 21/10/22)
		// int n = Saisie.lire_int("Quel numéro de compte souhaitez-vous consulter ?");
		// MonPF.consulterCompte(n);

		// // Affichage des comptes à solde négatif (fonctionnel compilation 21/10/22)
		// MonPF.afficherSoldesNegatifs();

		// // Mise à jour des taux d'épargne de tous les comptes (fonctionnel
		// // compilation 21/10/22)
		// System.out.println("Portefeuille avant mise à jour du taux: \n");
		// System.out.println(MonPF.toString());
		// MonPF.mise_a_jour();
		// System.out.println("Portefeuille après mise à jour du taux: \n");
		// System.out.println(MonPF.toString());
	}

}
