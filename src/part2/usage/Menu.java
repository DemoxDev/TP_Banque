package part2.usage;

import java.util.ArrayList;

import common.Saisie;
import common.TextColor;
import part2.banque.Compte_Courant;
import part2.banque.PortefeuilleClients;

public final class Menu {
	private static ArrayList<PortefeuilleClients> listePortefeuilles = new ArrayList<PortefeuilleClients>();

	// Menu de sélection d'interface (Admin/Client)
	// (retourne true si le menu doit être réaffiché à la fin de l'opération)
	private final static boolean menuSelection() {
		String menu = TextColor.PURPLE + "[MENU PRINCIPAL]\n" + TextColor.RESET
				+ "Que souhaitez-vous faire ?\n"
				+ "(1) Accéder à l'interface d'administration\n"
				+ "(2) Accéder à l'interface client\n"
				+ "(0) Quitter";
		int choix = Saisie.lire_int(menu);
		switch (choix) {
			case 0: {
				System.out.println("Sortie du menu principal...");
				return false;
			}
			case 1: {
				boolean runMenu = Menu.menuAdmin();
				while (runMenu) {
					runMenu = Menu.menuAdmin();
				}
				return true;
			}
			case 2: {
				Menu.selectCompte();
				return true;
			}
			default: {
				System.out.println(TextColor.RED + "Ce choix n'existe pas, veuillez resaisir un choix !"
						+ TextColor.RESET);
				return true;
			}
		}
	}

	// Programme principal:
	// lance le menu de sélection d'interface (Admin/Client)
	private final static void selectCompte() {
		if (listePortefeuilles.isEmpty()) {
			System.out.println(
					TextColor.RED + "Il n'y a aucun portefeuille de créé pour l'instant !" + TextColor.RESET);
		} else {
			System.out.println("Liste des portefeuilles:");
			for (int indexPF = 0; indexPF < listePortefeuilles.size(); indexPF++) {
				System.out.println("Portefeuille N°" + indexPF);
			}
			int numPortefeuille = Saisie.lire_int(
					"Quel est le numéro du portefeuille dans lequel se trouve le compte auquel vous souhaitez accéder ?");
			while (numPortefeuille < 0) {
				System.out.println(TextColor.RED + "Vous devez saisir un nombre entier positif !" + TextColor.RESET);
				numPortefeuille = Saisie.lire_int(
						"Quel est le numéro du portefeuille dans lequel se trouve le compte auquel vous souhaitez accéder ?");
			}
			while (numPortefeuille < 0 || numPortefeuille > listePortefeuilles.size() - 1) {
				System.out
						.println(TextColor.RED + "Ce portefeuille n'existe pas !"
								+ TextColor.RESET);
				numPortefeuille = Saisie.lire_int(
						"Veuillez saisir le numéro du portefeuille auquel vous souhaitez accéder:");
			}
			PortefeuilleClients Portefeuille = Menu.listePortefeuilles.get(numPortefeuille);
			String liste = "Liste des clients de ce portefeuille:\n";
			if (Portefeuille.getListeComptesCourants().isEmpty()) {
				liste = TextColor.RED + "Aucun client n'a de compte dans ce portefeuille !" + TextColor.RESET;
			} else {
				for (final Compte_Courant unCompteCourant : Portefeuille.getListeComptesCourants()) {
					liste += unCompteCourant.getNumero() + "\n";
				}
			}
			System.out.println(liste);

			int numCompte = Saisie.lire_int("Quel est le numéro du compte auquel vous souhaitez accéder ?");
			Compte_Courant unCompteCourant = Portefeuille.getCompteCourant(numCompte);
			while (unCompteCourant == null) {
				System.out.println(TextColor.RED + "Ce compte n'existe pas ! Veuillez resaisir un numéro de compte."
						+ TextColor.RESET);
				numCompte = Saisie.lire_int("Quel est le numéro du compte auquel vous souhaitez accéder ?");
				unCompteCourant = Portefeuille.getCompteCourant(numCompte);
			}
			boolean runMenu = Menu.menuClient(numPortefeuille, numCompte);
			while (runMenu) {
				runMenu = Menu.menuClient(numPortefeuille, numCompte);
			}
		}
	}

	// Menu interface client
	// (retourne true si le menu doit être réaffiché à la fin de l'opération)
	private final static boolean menuClient(int numPortefeuille, int numCompte) {
		PortefeuilleClients unPortefeuilleClients = listePortefeuilles.get(numPortefeuille);
		String menu = TextColor.PURPLE + "[MENU CLIENT (" + numCompte + ")]\n" + TextColor.RESET
				+ "Que souhaitez-vous faire ?\n"
				+ "(1) Consulter ce compte\n"
				+ "(2) Faire un dépôt\n"
				+ "(3) Faire un retrait\n"
				+ "(4) Faire un transfert compte courant/épargne\n"
				+ "(0) Quitter";
		int choix = Saisie.lire_int(menu);
		while (choix < 0) {
			System.out.println(TextColor.RED + "Vous devez saisir un nombre entier positif !" + TextColor.RESET);
			choix = Saisie.lire_int(menu);
		}
		switch (choix) {
			case 0: {
				System.out.println("Sortie du menu client...");
				return false;
			}
			case 1: {
				unPortefeuilleClients.consulterCompte(numCompte);
				break;
			}
			case 2: {
				unPortefeuilleClients.depot(numCompte);
				break;
			}
			case 3: {
				unPortefeuilleClients.retrait(numCompte);
				break;
			}
			case 4: {
				unPortefeuilleClients.transfert(numCompte);
				break;
			}
			default: {
				System.out.println(TextColor.RED + "Ce choix n'existe pas, veuillez resaisir un choix !"
						+ TextColor.RESET);
				return true;
			}
		}
		Saisie.lire_String("Appuyez sur Entrée pour continuer...");
		return true;

	}

	// Menu d'administration
	// (retourne true si le menu doit être réaffiché à la fin de l'opération)
	private final static boolean menuAdmin() {
		String menu = TextColor.PURPLE + "[MENU ADMINISTRATION]\n" + TextColor.RESET
				+ "Que souhaitez-vous faire ?\n"
				+ "(1) Créer un portefeuille\n"
				+ "(2) Accéder à un portefeuille\n"
				+ "(0) Quitter";
		int choix = Saisie.lire_int(menu);
		switch (choix) {
			case 0: {
				System.out.println("Sortie du menu principal...");
				return false;
			}
			case 1: {
				listePortefeuilles.add(new PortefeuilleClients());
				System.out.println(
						TextColor.GREEN + "Le portefeuille " + (listePortefeuilles.size() - 1) + " a été ajouté !"
								+ TextColor.RESET);

				break;
			}
			case 2: {
				if (listePortefeuilles.isEmpty()) {
					System.out.println(
							TextColor.RED + "Il n'y a aucun portefeuille de créé pour l'instant !" + TextColor.RESET);
					return true;
				} else {
					System.out.println("Liste des portefeuilles:");
					for (int indexPF = 0; indexPF < listePortefeuilles.size(); indexPF++) {
						System.out.println("Portefeuille N°" + indexPF);
					}
					choix = Saisie.lire_int("Veuillez saisir le numéro du portefeuille auquel vous souhaitez accéder:");
					while (choix < 0 || choix > listePortefeuilles.size() - 1) {
						System.out
								.println(TextColor.RED + "Ce portefeuille n'existe pas !"
										+ TextColor.RESET);
						choix = Saisie.lire_int(
								"Veuillez saisir le numéro du portefeuille auquel vous souhaitez accéder:");
					}
					PortefeuilleClients Portefeuille = listePortefeuilles.get(choix);
					while (Portefeuille == null) {
						System.out
								.println(TextColor.RED + "Vous devez saisir un nombre entier positif !"
										+ TextColor.RESET);
						choix = Saisie.lire_int(
								"Veuillez saisir le numéro du portefeuille auquel vous souhaitez accéder:");
					}
					boolean runMenu = Portefeuille.menuPortefeuille(choix);
					while (runMenu) {
						runMenu = Portefeuille.menuPortefeuille(choix);
					}
					return true;
				}
			}
			default: {
				System.out.println(TextColor.RED + "Ce choix n'existe pas, veuillez resaisir un choix !"
						+ TextColor.RESET);
				return true;
			}
		}
		Saisie.lire_String("Appuyez sur Entrée pour continuer...");
		return true;
	}

	// Programme principal:
	// lance le menu de sélection d'interface (Admin/Client)
	public final static void main(String[] args) {
		System.out.println(
				TextColor.PURPLE + "[ADMIN] " + TextColor.RESET
						+ "Exécution du programe de gestion de compte (partie 2)");
		boolean runMenu = Menu.menuSelection();
		while (runMenu) {
			runMenu = Menu.menuSelection();
		}
	}
}
