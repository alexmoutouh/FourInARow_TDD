package com.sofwaymedical.puissance4;

import java.util.*;

public class Analyseur {

	private boolean verifierCombinaisonGagnante(List<String> grille) {
		for (String serie : grille) {
			if (serie.contains("RRRR") || serie.contains("JJJJ")) {
				return true;
			}
		}

		return false;
	}

	private List<String> getLignes(Grille grille) {
		return Arrays.asList(grille.getGrille().split("\n"));
	}

	private List<String> getColonnes(Grille grille) {
		Map<Integer, String> colonnesParIndex = new HashMap<>();

		int index = 0;
		for (Character emplacement : grille.getGrille().toCharArray()) {
			if ('\n' == (emplacement)) {
				index = 0;
			} else {
				String colonne = colonnesParIndex.get(index);
				if (colonne == null) {
					colonne = emplacement.toString();
				} else {
					colonne = colonne.concat(emplacement.toString());
				}
				colonnesParIndex.put(index, colonne);

				++index;
			}
		}

		return new ArrayList<>(colonnesParIndex.values());
	}

	private List<String> getDiagonnalesMontantes(Grille grille) {
		Map<Integer, String> diagonnalesParIndex = new HashMap<>();

		int ligneIndex = 0;
		int colonneIndex = 0;
		for (Character emplacement : grille.getGrille().toCharArray()) {
			if ('\n' == emplacement) {
				++ligneIndex;
				colonneIndex = 0;
			} else {
				if (!estCoinHautGauche(ligneIndex, colonneIndex) && !estCoinBasDroit(ligneIndex, colonneIndex)) {
					String diagonale = diagonnalesParIndex.get(colonneIndex);
					if (diagonale == null) {
						diagonale = emplacement.toString();
					} else {
						diagonale = diagonale.concat(emplacement.toString());
					}
					diagonnalesParIndex.put(colonneIndex, diagonale);
					++colonneIndex;
				}
			}
		}

		return new ArrayList<>(diagonnalesParIndex.values());
	}

	private boolean estCoinHautGauche(int ligneIndex, int colonneIndex) {
		return ligneIndex * colonneIndex < 2;
	}

	private boolean estCoinBasDroit(int ligneIndex, int colonneIndex) {
		return ligneIndex * colonneIndex >= 18;
	}

	public boolean estPartieGagnee(Grille grille) {
		return verifierCombinaisonGagnante(getLignes(grille))
				|| verifierCombinaisonGagnante(getColonnes(grille))
				|| verifierCombinaisonGagnante(getDiagonnalesMontantes(grille));
	}

	public boolean estPartieNulle(Grille grille) {
		return false;
	}
}
