package com.sofwaymedical.puissance4;

public class Arbitre {

	private Grille grille;
	private Joueur dernierJoueur;
	private final Analyseur analyseur;

	public Arbitre(Grille grille, Analyseur analyseur) {
		this.grille = grille;
		this.dernierJoueur = null;
		this.analyseur = analyseur;
	}

	public Joueur getTourJoueur() {
		if (Joueur.JAUNE.equals(dernierJoueur)) {
			return Joueur.ROUGE;
		}

		return Joueur.JAUNE;
	}

	public EtatPartie getEtatPartie() {
		if (analyseur.estPartieNulle(grille)) {
			return EtatPartie.NULLE;
		}

		if (analyseur.estPartieGagnee(grille)) {
			if (Joueur.JAUNE.equals(dernierJoueur)) {
				return EtatPartie.GAGNEE_JAUNE;
			}

			return EtatPartie.GAGNEE_ROUGE;
		}

		return EtatPartie.ENCOURS;
	}

	public void faitJouer(int colonne) {
		dernierJoueur = getTourJoueur();
		grille.ajouterPion(dernierJoueur, colonne);
	}
}
