package part2.banque;

import java.util.ArrayList;

import common.Saisie;
import common.TextColor;

public final class PortefeuilleClients {
	/* PROPRIÉTÉS/ATTRIBUTS */
	private ArrayList<Compte_Courant> listeComptesCourants;
	private ArrayList<Compte_Epargne> listeComptesEpargne;

	/* MÉTHODES */
	// SETTERS
	// Définir la liste des comptes courants
	private void setListeComptesCourants(ArrayList<Compte_Courant> listeComptesCourants) {
		this.listeComptesCourants = listeComptesCourants;
	}

	// Définir la liste des comptes épargne
	private void setListeComptesEpargne(ArrayList<Compte_Epargne> listeComptesEpargne) {
		this.listeComptesEpargne = listeComptesEpargne;
	}

	// GETTERS
	// Récupérer la liste des comptes courants
	public final ArrayList<Compte_Courant> getListeComptesCourants() {
		return this.listeComptesCourants;
	}

	// Récupérer la liste des comptes épargne
	private final ArrayList<Compte_Epargne> getListeComptesEpargne() {
		return this.listeComptesEpargne;
	}

	/* AUTRES MÉTHODES */
	// Récupérer le premier compte courant inscrit avec le numéro en argument
	public final Compte_Courant getCompteCourant(int numero) {
		for (Compte_Courant unCompteCourant : this.getListeComptesCourants()) {
			if (unCompteCourant.getNumero() == numero) {
				return unCompteCourant;
			}
		}
		return null;
	}

	// Récupérer le premier compte épargne inscrit avec le numéro en argument
	private final Compte_Epargne getCompteEpargne(int numero) {
		for (Compte_Epargne unCompteEpargne : this.getListeComptesEpargne()) {
			if (unCompteEpargne.getNumero() == numero) {
				return unCompteEpargne;
			}
		}
		return null;
	}

	// Ajouter un ou plusieurs comptes (appelée depuis this.menuPortefeuille())
	private final void ajouteComptes() {
		int n = Saisie.lire_int("Combien de comptes voulez-vous créer ?");
		while (n < 0) {
			System.out.println(TextColor.RED + "Vous devez saisir un nombre entier positif !" + TextColor.RESET);
			n = Saisie.lire_int("Combien de comptes voulez-vous créer ?");
		}

		for (int i = 1; i <= n; i++) {
			System.out.println(TextColor.GREEN + "Création du compte " + i + TextColor.RESET);
			int numero = Saisie.lire_int("Quel est le numéro de ce compte ?");
			while (numero < 0) {
				System.out.println(TextColor.RED + "Vous ne pouvez pas donner un numéro de compte négatif !"
						+ TextColor.RESET);
				numero = Saisie.lire_int("Quel est le numéro de ce compte ?");
			}
			while (getCompteCourant(numero) != null) {
				System.out.println(TextColor.RED + "Ce numéro de compte existe déjà, veuillez en saisir un autre !"
						+ TextColor.RESET);
				numero = Saisie.lire_int("Quel est le numéro de ce compte ?");
			}
			String titulaire = Saisie.lire_String("Quel est le nom du client ?");
			double decouvert_autorise = Saisie.lire_double("Quel est le découvert autorisé pour ce compte ?");
			while (decouvert_autorise < 0) {
				System.out.println(TextColor.RED + "Vous ne pouvez pas définir un solde inférieur au découvert !"
						+ TextColor.RESET);
				decouvert_autorise = Saisie.lire_double("Quel est le découvert autorisé pour ce compte ?");
			}
			double solde = Saisie.lire_double("Quel est le solde intitial ?");
			while (solde < -decouvert_autorise) {
				System.out.println(TextColor.RED
						+ "Veuillez entrer un nombre positif ! Le calcul en négatif est effectué par l'application."
						+ TextColor.RESET);
				solde = Saisie.lire_double("Quel est le solde intitial ?");
			}
			Compte_Courant compteCourant = new Compte_Courant(numero, titulaire, solde, decouvert_autorise);
			this.getListeComptesCourants().add(compteCourant);

			int createEpargne = Saisie.lire_int("Souhaitez vous créer un compte épargne ? (0/1)");
			while (createEpargne != 0 && createEpargne != 1) {
				System.out.println(TextColor.RED + "Veuillez saisir un choix valide (0/1) !\n"
						+ TextColor.RESET);
				createEpargne = Saisie.lire_int("Souhaitez vous créer un compte épargne ? (0/1)");
			}
			if (createEpargne == 1) {
				solde = Saisie.lire_double("Quel est le solde initial de ce compte épargne ?");
				while (solde < 0) {
					System.out.println(TextColor.RED + "Vous ne pouvez pas définir un solde d'épargne négatif !"
							+ TextColor.RESET);
					solde = Saisie.lire_double("Quel est le solde initial de ce compte épargne ?");
				}
				double taux = Saisie.lire_double("Quel est le taux à appliquer sur ce compte épargne ?");
				while (taux < 0) {
					System.out.println(
							TextColor.RED + "Vous ne pouvez pas définir un taux négatif !" + TextColor.RESET);
					taux = Saisie.lire_double("Quel est le taux à appliquer sur ce compte épargne ?");
				}
				this.getListeComptesEpargne()
						.add(new Compte_Epargne(compteCourant.getNumero(), compteCourant.getTitulaire(), solde, taux));
			}
		}

		switch (n) {
			case 0: {
				System.out.println(TextColor.GREEN + "Aucun compte n'a été créé !" + TextColor.RESET);
				break;
			}
			case 1: {
				System.out.println(TextColor.GREEN + n + " compte a été créé !" + TextColor.RESET);
				break;
			}
			default: {
				System.out.println(TextColor.GREEN + n + " comptes ont été créés !" + TextColor.RESET);
				break;
			}
		}
	}

	// Consulter un compte
	// (appelée depuis Menu.menuClient)
	public final void consulterCompte(int numCompte) {
		if (this.getListeComptesCourants().isEmpty()) {
			System.out.println(
					TextColor.RED + "Il n'y a aucun compte dans ce portefeuille pour le moment !" + TextColor.RESET);
		} else {
			Compte_Courant unCompteCourant = getCompteCourant(numCompte);
			if (unCompteCourant != null) {
				System.out.println(unCompteCourant.toString());

				Compte_Epargne unCompteEpargne = getCompteEpargne(numCompte);
				if (unCompteEpargne != null) {
					System.out.println(unCompteEpargne.toString());
				} else {
					System.out.println("Ce client n'a aucun compte épargne.");
				}
			} else {
				System.out.println(TextColor.RED + "Aucun compte ne correspond à ce numéro de compte !"
						+ TextColor.RESET);
			}
		}
	}

	// Supprimer un compte
	// (appelée depuis this.menuPortefeuille())
	private final void supprimerCompte() {
		if (this.getListeComptesCourants().isEmpty()) {
			System.out.println(
					TextColor.RED + "Il n'y a aucun compte dans ce portefeuille pour le moment !" + TextColor.RESET);
		} else {
			int numCompte = Saisie.lire_int("Quel est le numéro du compte que vous souhaitez supprimer ?");
			Compte_Courant unCompteCourant = this.getCompteCourant(numCompte);
			while (unCompteCourant == null) {
				System.out.println(
						TextColor.RED + "Ce compte n'existe pas ! Veuillez resaisir un numéro de compte."
								+ TextColor.RESET);
				numCompte = Saisie.lire_int("Quel est le numéro du compte que vous souhaitez supprimer ?");
				unCompteCourant = this.getCompteCourant(numCompte);
			}
			System.out.println(TextColor.GREEN + "Suppression du compte courant" + numCompte + TextColor.RESET);
			this.getListeComptesCourants().remove(unCompteCourant);
			Compte_Epargne unCompteEpargne = this.getCompteEpargne(numCompte);
			if (unCompteEpargne != null) {
				System.out.println(TextColor.GREEN + "Suppression du compte épargne " + numCompte
						+ TextColor.RESET);
				this.getListeComptesEpargne().remove(unCompteEpargne);
			}
		}
	}

	// Afficher les comptes avec un solde négatif
	// (appelée depuis this.menuPortefeuille())
	private final void afficherSoldesNegatifs() {
		String message;
		if (this.getListeComptesCourants().isEmpty()) {
			message = TextColor.RED + "Il n'y a aucun compte dans ce portefeuille pour le moment !" + TextColor.RESET;
		} else {
			boolean found = false;
			message = "\nComptes à solde négatif:\n";
			for (Compte_Courant unCompteCourant : this.getListeComptesCourants()) {
				if (unCompteCourant.getSolde() < 0) {
					message += unCompteCourant.toString();
					// Si un compte épargne existe, afficher ses infos
					Compte_Epargne unCompteEpargne = this.getCompteEpargne(unCompteCourant.getNumero());
					if (unCompteEpargne != null) {
						message += ", " + unCompteEpargne.toString();
					} else {
						message += " (Pas de compte épargne)";
					}
					message += "\n";
					found = true;
				}

			}
			if (!found) {
				message = TextColor.RED + "Il n'y a aucun compte à solde négatif dans cette banque !" + TextColor.RESET;
			}
		}
		System.out.println(message);
	}

	// Afficher les comptes avec à la fois un compte courant et un compte épargne
	// (appelée depuis this.menuPortefeuille())
	private final void afficherDoubleComptes() {
		String message;
		if (this.getListeComptesCourants().isEmpty()) {
			message = TextColor.RED + "Il n'y a aucun compte dans ce portefeuille pour le moment !" + TextColor.RESET;
		} else {
			boolean found = false;
			message = "\nComptes avec un compte épargne:\n";
			for (Compte_Courant unCompteCourant : this.getListeComptesCourants()) {
				// Si un compte épargne existe, afficher tout
				Compte_Epargne unCompteEpargne = this.getCompteEpargne(unCompteCourant.getNumero());
				if (unCompteEpargne != null) {
					message += unCompteCourant.toString()
							+ ", " + this.getCompteEpargne(unCompteEpargne.getNumero()).toString() + "\n";
					found = true;
				}
			}
			if (!found) {
				message = TextColor.RED + "Il n'y a aucun compte épargne associé aux clients de ce portefeuille !"
						+ TextColor.RESET;
			}
		}
		System.out.println(message);
	}

	// Supprimer tous les comptes
	// (appelée depuis this.menuPortefeuille())
	private final void viderComptes() {
		if (this.getListeComptesCourants().isEmpty()) {
			System.out.println(TextColor.RED + "Il n'y a aucun compte courant à supprimer !" + TextColor.RESET);
		} else {
			this.getListeComptesCourants().clear();
			System.out.println(TextColor.GREEN + "Tous les comptes courants ont été supprimés !" + TextColor.RESET);
		}
		if (this.getListeComptesEpargne().isEmpty()) {
			System.out.println(TextColor.RED + "Il n'y a aucun compte épargne à supprimer !" + TextColor.RESET);
		} else {
			this.getListeComptesEpargne().clear();
			System.out.println(TextColor.GREEN + "Tous les comptes épargne ont été supprimés !" + TextColor.RESET);
		}
	}

	// Modifier le taux d'épargne de tous les comptes
	// (appelée depuis this.menuPortefeuille())
	private final void mise_a_jour() {
		if (this.getListeComptesCourants().isEmpty()) {
			System.out.println(
					TextColor.RED + "Il n'y a aucun compte dans ce portefeuille pour le moment !" + TextColor.RESET);
		} else {
			double taux = Saisie.lire_double("Quel est le taux à appliquer sur tous les comptes épargne ?");
			while (taux < 0) {
				System.out.println(TextColor.RED + "Vous ne pouvez pas définir un taux négatif !" + TextColor.RESET);
				taux = Saisie.lire_double("Quel est le taux à appliquer sur tous les comptes épargne ?");
			}
			for (Compte_Epargne unCompteEpargne : this.getListeComptesEpargne()) {
				unCompteEpargne.setTaux(taux);
			}
			System.out.println(TextColor.GREEN + "Tous les comptes épargne ont maintenant un taux de " + taux + "% !"
					+ TextColor.RESET);
		}
	}

	// Effectuer un dépôt
	// (appelée depuis Menu.menuClient())
	public final void depot(int numCompte) {
		Compte_Courant unCompteCourant = this.getCompteCourant(numCompte);
		Compte_Epargne unCompteEpargne = this.getCompteEpargne(numCompte);
		if (unCompteEpargne != null) {
			System.out.println("Ce compte dispose d'un compte épargne.");
			int typeCompte = Saisie.lire_int(
					"Souhaitez-vous effectuer le dépôt sur le compte courant (1) ou le compte épargne (2) ?");
			while (typeCompte != 1 && typeCompte != 2) {
				System.out.println(TextColor.RED + "Veuillez saisir un choix valide ! (1/2)" + TextColor.RESET);
				typeCompte = Saisie.lire_int(
						"Souhaitez-vous effectuer le dépôt sur le compte courant (1) ou le compte épargne (2) ?");
			}
			int depot = Saisie.lire_int("Quelle somme souhaitez-vous déposer ?");
			while (depot < 0) {
				System.out.println(
						TextColor.RED + "Vous devez saisir un nombre décimal positif !" + TextColor.RESET);
				depot = Saisie.lire_int("Quelle somme souhaitez-vous déposer ?");
			}
			if (typeCompte == 1) {
				unCompteCourant.deposer(depot);
				System.out.println(TextColor.GREEN + "Vous avez déposé " + depot
						+ "EUR sur votre compte courant !" + TextColor.RESET);
			}
			if (typeCompte == 2) {
				unCompteEpargne.deposer(depot);
				System.out.println(TextColor.GREEN + "Vous avez déposé " + depot
						+ "EUR sur votre compte épargne !" + TextColor.RESET);
			}
		} else {
			int depot = Saisie.lire_int("Quelle somme souhaitez-vous déposer ?");
			while (depot < 0) {
				System.out.println(
						TextColor.RED + "Vous devez saisir un nombre décimal positif !" + TextColor.RESET);
				depot = Saisie.lire_int("Quelle somme souhaitez-vous déposer ?");
			}
			unCompteCourant.deposer(depot);
			System.out.println(TextColor.GREEN + "Vous avez déposé " + depot + "EUR sur votre compte courant !"
					+ TextColor.RESET);
		}
	}

	// Effectuer un retrait
	// (appelée depuis Menu.menuClient())
	public final void retrait(int numCompte) {
		Compte_Courant unCompteCourant = this.getCompteCourant(numCompte);
		Compte_Epargne unCompteEpargne = this.getCompteEpargne(numCompte);
		if (unCompteEpargne != null) {
			System.out.println("Ce compte dispose d'un compte épargne.");
			int typeCompte = Saisie.lire_int(
					"Souhaitez-vous effectuer le retrait sur le compte courant (1) ou le compte épargne (2) ?");
			while (typeCompte != 1 && typeCompte != 2) {
				System.out.println(TextColor.RED + "Veuillez saisir un choix valide ! (1/2)" + TextColor.RESET);
				typeCompte = Saisie.lire_int(
						"Souhaitez-vous effectuer le retrait sur le compte courant (1) ou le compte épargne (2) ?");
			}
			int retrait = Saisie.lire_int("Quelle somme souhaitez-vous retirer ?");
			while (retrait < 0) {
				System.out.println(
						TextColor.RED + "Vous devez saisir un nombre décimal positif !" + TextColor.RESET);
				retrait = Saisie.lire_int("Quelle somme souhaitez-vous retirer ?");
			}
			if (typeCompte == 1) {
				if (unCompteCourant.retirer(retrait)) {
					System.out.println(TextColor.GREEN + "Vous avez retiré " + retrait
							+ "EUR de votre compte courant !" + TextColor.RESET);
				}
			}
			if (typeCompte == 2) {
				if (unCompteEpargne.retirer(retrait)) {
					System.out.println(TextColor.GREEN + "Vous avez retiré " + retrait
							+ "EUR de votre compte épargne !" + TextColor.RESET);
				}
			}
		} else {
			int retrait = Saisie.lire_int("Quelle somme souhaitez-vous retirer ?");
			while (retrait < 0) {
				System.out.println(
						TextColor.RED + "Vous devez saisir un nombre décimal positif !" + TextColor.RESET);
				retrait = Saisie.lire_int("Quelle somme souhaitez-vous retirer ?");
			}
			if (unCompteCourant.retirer(retrait)) {
				System.out.println(
						TextColor.GREEN + "Vous avez retiré " + retrait + "EUR de votre compte courant !"
								+ TextColor.RESET);
			}
		}
	}

	// Effectuer un transfert entre compte courant et compte épargne & vice-versa
	// (appelée depuis Menu.menuClient())
	public final void transfert(int numCompte) {
		Compte_Courant unCompteCourant = this.getCompteCourant(numCompte);
		Compte_Epargne unCompteEpargne = this.getCompteEpargne(numCompte);
		if (unCompteEpargne == null) {
			System.out.println(
					TextColor.RED + "Aucun transfert n'est possible, ce compte ne dispose d'aucune épargne !"
							+ TextColor.RESET);
		} else {
			int typeCompte = Saisie.lire_int(
					"Souhaitez-vous effectuer le transfert depuis le compte courant (1) ou le compte épargne (2) ?");
			while (typeCompte != 1 && typeCompte != 2) {
				System.out.println(TextColor.RED + "Veuillez saisir un choix valide ! (1/2)" + TextColor.RESET);
				typeCompte = Saisie.lire_int(
						"Souhaitez-vous effectuer le transfert depuis le compte courant (1) ou le compte épargne (2) ?");
			}
			int transfert = Saisie.lire_int("Quelle somme souhaitez-vous transférer ?");
			while (transfert < 0) {
				System.out.println(
						TextColor.RED + "Vous devez saisir un nombre décimal positif !" + TextColor.RESET);
				transfert = Saisie.lire_int("Quelle somme souhaitez-vous retirer ?");
			}
			if (typeCompte == 1) {
				if (unCompteCourant.retirer(transfert)) {
					unCompteEpargne.deposer(transfert);
					System.out.println(TextColor.GREEN + "Vous avez transféré " + transfert
							+ "EUR de votre compte courant à votre compte épargne !" + TextColor.RESET);
				}
			}
			if (typeCompte == 2) {
				if (unCompteEpargne.retirer(transfert)) {
					unCompteCourant.deposer(transfert);
					System.out.println(TextColor.GREEN + "Vous avez transféré " + transfert
							+ "EUR de votre compte épargne à votre compte courant !" + TextColor.RESET);
				}
			}
		}
	}

	// Augmenter le taux d'épargne de tous les comptes
	// (appelée depuis this.menuPortefeuille())
	private final void augmenterTaux() {
		if (this.getListeComptesCourants().isEmpty()) {
			System.out.println(
					TextColor.RED + "Il n'y a aucun compte dans ce portefeuille pour le moment !" + TextColor.RESET);
		} else {
			double taux = Saisie.lire_double("Quel pourcentage est à ajouter sur tous les comptes épargne ?");
			while (taux < 0) {
				System.out.println(TextColor.RED + "Vous ne pouvez pas définir un taux négatif !" + TextColor.RESET);
				taux = Saisie.lire_double("Quel pourcentage est à ajouter sur tous les comptes épargne ?");
			}
			for (Compte_Epargne unCompteEpargne : this.getListeComptesEpargne()) {
				unCompteEpargne.setTaux(unCompteEpargne.getTaux() + taux);
			}
			System.out
					.println(TextColor.GREEN + "Tous les comptes épargne ont vu leur taux augmenter de " + taux + "% !"
							+ TextColor.RESET);
		}
	}

	// Menu de gestion du portefeuille
	// (retourne true si le menu doit être réaffiché à la fin de l'opération)
	// (appelée depuis Menu.menuAdmin())
	public final boolean menuPortefeuille(int id) {
		String menu = TextColor.PURPLE + "[MENU DE GESTION DU PORTEFEUILLE " + id + "]\n" + TextColor.RESET
				+ "Que souhaitez-vous faire ?\n"
				+ "(1) Créer un ou plusieurs comptes\n"
				+ "(2) Modifier un compte\n"
				+ "(3) Supprimer un compte\n"
				+ "(4) Modifier tous les taux d'épargne\n"
				+ "(5) Augmenter tous les taux d'épargne\n"
				+ "(6) Afficher les comptes avec un solde négatif\n"
				+ "(7) Afficher tous les comptes\n"
				+ "(8) Afficher tous les clients avec un compte courant et épargne à la fois\n"
				+ "(9) Supprimer tous les comptes\n"
				+ "(0) Quitter";
		int choix = Saisie.lire_int(menu);
		while (choix < 0) {
			System.out.println(TextColor.RED + "Vous devez saisir un nombre entier positif !" + TextColor.RESET);
			choix = Saisie.lire_int(menu);
		}
		switch (choix) {
			case 0: {
				System.out.println("Sortie du menu de gestion du portefeuille...");
				return false;
			}
			case 1: {
				this.ajouteComptes();
				break;
			}
			case 2: {
				this.menuCompte();
				return true;
			}
			case 3: {
				this.supprimerCompte();
				break;
			}
			case 4: {
				this.mise_a_jour();
				break;
			}
			case 5: {
				this.augmenterTaux();
				break;
			}
			case 6: {
				this.afficherSoldesNegatifs();
				break;
			}
			case 7: {
				System.out.println(this.toString());
				break;
			}
			case 8: {
				this.afficherDoubleComptes();
				break;
			}
			case 9: {
				this.viderComptes();
				break;
			}
			default: {
				System.out.println(TextColor.RED + "Ce choix n'existe pas, veuillez resaisir un choix !"
						+ TextColor.RESET);
				break;
			}
		}
		Saisie.lire_String("Appuyez sur Entrée pour continuer...");
		return true;
	}

	// Menu de gestion des comptes
	// (retourne true si le menu doit être réaffiché à la fin de l'opération)
	// (appelée depuis this.menuPortefeuille())
	private final boolean menuCompte() {
		if (getListeComptesCourants().isEmpty()) {
			System.out.println(
					TextColor.RED + "Il n'y a aucun compte dans ce portefeuille pour le moment !" + TextColor.RESET);
			Saisie.lire_String("Appuyez sur Entrée pour revenir au menu du portefeuille...");
			return false;
		} else {
			int numCompte = Saisie.lire_int("Quel compte souhaitez-vous modifier ?");
			Compte_Courant unCompteCourant = this.getCompteCourant(numCompte);
			while (unCompteCourant == null) {
				System.out.println(TextColor.RED + "Ce compte n'existe pas ! Veuillez resaisir un numéro de compte."
						+ TextColor.RESET);
				numCompte = Saisie.lire_int("Quel compte souhaitez-vous modifier ?");
				unCompteCourant = this.getCompteCourant(numCompte);
			}

			int choix = Saisie.lire_int(
					TextColor.PURPLE + "[MENU DE GESTION DU COMPTE (" + numCompte + ")]\n" + TextColor.RESET
							+ "Que souhaitez-vous faire ?\n"
							+ "(1) Modifier le nom du titulaire\n"
							+ "(2) Modifier le numéro de compte\n"
							+ "(3) Modifier le solde\n"
							+ "(4) Modifier le découvert\n"
							+ "(5) Créer un compte épargne\n"
							+ "(6) Modifier le taux d'épargne de ce compte\n"
							+ "(0) Quitter");
			switch (choix) {
				case 0: {
					System.out.println("Sortie du menu de gestion de comptes...");
					return false;
				}
				case 1: {
					String titulaire = Saisie.lire_String("Quel titulaire voulez-vous définir pour ce compte ?");
					unCompteCourant.setTitulaire(titulaire);
					System.out.println(
							TextColor.GREEN + "Ce compte a maintenant "
									+ unCompteCourant.getTitulaire() + "pour titulaire !"
									+ TextColor.RESET);
					break;
				}
				case 2: {
					int numero = Saisie.lire_int("Quel numéro voulez-vous appliquer à ce compte ?");
					while (numero < 0) {
						System.out.println(
								TextColor.RED + "Vous ne pouvez pas définir un numéro de compte négatif !"
										+ TextColor.RESET);
						numero = Saisie.lire_int("Quel numéro voulez-vous appliquer à ce compte ?");
					}
					unCompteCourant.setNumero(numero);
					System.out.println(
							TextColor.GREEN + "Ce compte a maintenant le numéro "
									+ unCompteCourant.getNumero() + " !"
									+ TextColor.RESET);
					break;
				}
				case 3: {
					Compte_Epargne unCompteEpargne = this.getCompteEpargne(numCompte);
					if (unCompteEpargne != null) {
						System.out.println("Ce compte dispose d'un compte épargne.");
						int typeCompte = Saisie.lire_int(
								"Souhaitez-vous modifier le solde du compte courant (1) ou du compte épargne (2) ?");
						while (typeCompte != 1 && typeCompte != 2) {
							System.out.println(
									TextColor.RED + "Veuillez saisir un choix valide ! (1/2)" + TextColor.RESET);
							typeCompte = Saisie.lire_int(
									"Souhaitez-vous modifier le solde du compte courant (1) ou du compte épargne (2) ?");
						}
						if (typeCompte == 1) {
							double solde = Saisie.lire_double("Quel est le solde à définir pour ce compte ?");
							while (solde < -unCompteCourant.getDecouvert()) {
								System.out.println(
										TextColor.RED
												+ "Le découvert autorisé de ce compte ne permet pas de définir ce solde !"
												+ TextColor.RESET);
								solde = Saisie.lire_double("Quel est le solde à définir pour ce compte ?");
							}
							unCompteCourant.setSolde(solde);
							System.out.println(
									TextColor.GREEN + "Ce compte courant a maintenant un solde de "
											+ unCompteCourant.getSolde() + "EUR !"
											+ TextColor.RESET);
						}
						if (typeCompte == 2) {
							double solde = Saisie.lire_double("Quel est le solde à définir pour ce compte épargne ?");
							while (solde < 0) {
								System.out.println(
										TextColor.RED
												+ "Vous ne pouvez pas définir une épargne négative !"
												+ TextColor.RESET);
								solde = Saisie.lire_double("Quel est le solde à définir pour ce compte épargne ?");
							}
							unCompteCourant.setSolde(solde);
							System.out.println(
									TextColor.GREEN + "Ce compte épargne a maintenant un solde de "
											+ unCompteCourant.getSolde() + "EUR !"
											+ TextColor.RESET);
						}
					} else {
						int retrait = Saisie.lire_int("Quelle somme souhaitez-vous retirer ?");
						while (retrait < 0) {
							System.out.println(
									TextColor.RED + "Vous devez saisir un nombre décimal positif !" + TextColor.RESET);
							retrait = Saisie.lire_int("Quelle somme souhaitez-vous retirer ?");
						}
						if (unCompteCourant.retirer(retrait)) {
							System.out.println(
									TextColor.GREEN + "Vous avez retiré " + retrait + "EUR de votre compte courant !"
											+ TextColor.RESET);
						}
					}
					break;
				}
				case 4: {
					double decouvert_autorise = Saisie.lire_double("Quel est le découvert autorisé pour ce compte ?");
					while (decouvert_autorise < 0) {
						System.out.println(
								TextColor.RED
										+ "Veuillez entrer un nombre positif ! Le calcul en négatif est effectué par l'application."
										+ TextColor.RESET);
						decouvert_autorise = Saisie.lire_double("Quel est le découvert autorisé pour ce compte ?");
					}
					unCompteCourant.setDecouvert(decouvert_autorise);
					System.out.println(
							TextColor.GREEN + "Ce compte a maintenant un découvert autorisé de "
									+ unCompteCourant.getDecouvert() + "EUR !"
									+ TextColor.RESET);
					break;
				}
				case 5: {
					if (this.getCompteEpargne(numCompte) != null) {
						System.out.println(
								TextColor.RED + "Il existe déjà un compte épargne associé à ce compte !"
										+ TextColor.RESET);
					} else {
						double solde = Saisie.lire_double("Quel est le solde initial de ce compte épargne ?");
						while (solde < 0) {
							System.out.println(TextColor.RED + "Vous ne pouvez pas définir un solde d'épargne négatif !"
									+ TextColor.RESET);
							solde = Saisie.lire_double("Quel est le solde initial de ce compte épargne ?");
						}
						double taux = Saisie.lire_double("Quel est le taux à appliquer sur ce compte épargne ?");
						while (taux < 0) {
							System.out.println(
									TextColor.RED + "Vous ne pouvez pas définir un taux négatif !" + TextColor.RESET);
							taux = Saisie.lire_double("Quel est le taux à appliquer sur ce compte épargne ?");
						}
						Compte_Epargne unCompteEpargne = new Compte_Epargne(unCompteCourant.getNumero(),
								unCompteCourant.getTitulaire(), solde, taux);
						this.getListeComptesEpargne().add(unCompteEpargne);
						System.out
								.println(TextColor.GREEN + "Un compte épargne a été créé avec le numéro " + numCompte +
										" pour le titulaire " + unCompteEpargne.getTitulaire() + " avec un taux de "
										+ unCompteEpargne.getTaux() + "% !" + TextColor.RESET);
					}
					break;
				}
				case 6: {
					final Compte_Epargne unCompteEpargne = this.getCompteEpargne(numCompte);
					if (unCompteEpargne == null) {
						System.out.println(
								TextColor.RED + "Il n'existe aucun compte épargne associé à ce compte courant !"
										+ TextColor.RESET);
					} else {
						double taux = Saisie.lire_double("Quel taux souhaitez-vous appliquer à ce compte ?");
						while (numCompte < 0) {
							System.out.println(TextColor.RED + "Vous ne pouvez pas saisir un taux négatif !"
									+ TextColor.RESET);
							taux = Saisie.lire_double("Quel taux souhaitez-vous appliquer à ce compte ?");
						}

						unCompteEpargne.setTaux(taux);
						System.out.println(
								TextColor.GREEN + "Ce compte épargne a maintenant un taux de " + taux + "% !"
										+ TextColor.RESET);
					}
					break;
				}
				default: {
					System.out.println(TextColor.RED + "Ce choix n'existe pas, veuillez resaisir un choix !"
							+ TextColor.RESET);
					break;
				}
			}
		}
		Saisie.lire_String("Appuyez sur Entrée pour revenir au menu du portefeuille...");
		return true;
	}

	/* CONSTRUCTEUR */
	// Crée une liste de comptes courants et une liste de comptes épargne
	public PortefeuilleClients() {
		this.setListeComptesEpargne(new ArrayList<Compte_Epargne>());
		this.setListeComptesCourants(new ArrayList<Compte_Courant>());
	}

	/* toString */ // (= consulter())
	// (Renvoie la liste des clients)
	public final String toString() { // = consulte()
		String liste = "Liste des clients de ce portefeuille:\n";
		if (this.getListeComptesCourants().isEmpty()) {
			liste = TextColor.RED + "Aucun client n'a de compte dans ce portefeuille !" + TextColor.RESET;
		} else {
			for (final Compte_Courant unCompteCourant : this.getListeComptesCourants()) {
				liste += unCompteCourant.toString();
				// Si un compte épargne existe, afficher ses infos
				final Compte_Epargne unCompteEpargne = this.getCompteEpargne(unCompteCourant.getNumero());
				if (unCompteEpargne != null) {
					liste += ", " + this.getCompteEpargne(unCompteEpargne.getNumero()).toString();
				} else {
					liste += " (Pas de compte épargne)";
				}
				liste += "\n";
			}
		}
		return liste;
	}
}