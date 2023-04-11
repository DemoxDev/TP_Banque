package common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class Saisie {
	public final static String lire_String(String question) {
		String ligne_lue = null;
		try {
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			System.out.println(question);
			ligne_lue = br.readLine();
		} catch (IOException e) {
			System.out.println(e);
		}
		return ligne_lue;
	}

	public final static int lire_int(String question) {
		int int_lu = -1;
		boolean success = false;
		while (!success) {
			try {
				int_lu = Integer.parseInt(lire_String(question));
				success = true;
			} catch (NumberFormatException e) {
				System.out.println(TextColor.RED + "Vous devez saisir un nombre entier !" + TextColor.RESET);
			}
		}
		return int_lu;
	}

	public final static double lire_double(String question) {
		double double_lu = -1;
		boolean success = false;
		while (!success) {
			try {
				double_lu = Double.parseDouble(lire_String(question));
				success = true;
			} catch (NumberFormatException e) {
				System.out
						.println(TextColor.RED + "Vous devez saisir un nombre décimal au format américain (avec un .) !"
								+ TextColor.RESET);
			}
		}

		return double_lu;
	}

}