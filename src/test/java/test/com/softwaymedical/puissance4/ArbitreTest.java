package test.com.softwaymedical.puissance4;

import com.sofwaymedical.puissance4.*;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;

public class ArbitreTest {

	private Grille grille;
	private Analyseur analyseur;
	private Arbitre arbitre;

	/*
	 * Quand nouvelle partie, alors partie en cours
	 * Quand nouvelle partie, alors tour à jaune de jouer
	 * Etant donné partie en cours, si jaune joue, alors au tour de rouge
	 * Etant donné partie en cours, si jaune joue et analyseur ne remonte pas de gagnant, alors partie en cours
	 * Etant donné partie en cours, si rouge joue et analyseur ne remonte pas de gagnant, alors partie en cours
	 * Etant donné partie en cours, si jaune joue et analyseur remonte gagnant, alors partie gagnée par jaune
	 * Etant donné partie en cours, si rouge joue et analyseur remonte gagnant, alors partie gagnée par rouge
	 * Etant donné partie terminée, si analyseur remonte partie nulle, alors partie nulle
	 */

	private void quandNouvellePartie() {
		grille = Mockito.mock(Grille.class);
		analyseur = Mockito.mock(Analyseur.class);
		arbitre = new Arbitre(grille, analyseur);
	}

	private void etantDonneJauneAJoue() {
		quandNouvellePartie();
		arbitre.faitJouer(1);
	}

	private void etantDonneRougeAJoue() {
		etantDonneJauneAJoue();
		arbitre.faitJouer(1);
	}

	@Test
	public void quandNouvellePartie_alorsPartieEnCours() {
		quandNouvellePartie();
		assertThat(arbitre.getEtatPartie(), Matchers.is(EtatPartie.ENCOURS));
	}

	@Test
	public void quandNouvellePartie_alorsTourDeJaune() {
		quandNouvellePartie();
		assertThat(arbitre.getTourJoueur(), Matchers.is(Joueur.JAUNE));
	}

	@Test
	public void etantDonnePartieEnCours_siJauneJoueColonne1_alorsGrilleModifieeColonne1_etTourDeRouge() {
		quandNouvellePartie();
		arbitre.faitJouer(1);
		Mockito.verify(grille, Mockito.times(1)).ajouterPion(Joueur.JAUNE, 1);
		assertThat(arbitre.getTourJoueur(), Matchers.is(Joueur.ROUGE));
	}

	@Test
	public void etantDonnePartie_siAnalyseurRemontePartieNulle_alorsPartieTermineeEtNulle() {
		quandNouvellePartie();
		Mockito.when(analyseur.estPartieNulle(grille)).thenReturn(true);
		assertThat(arbitre.getEtatPartie(), Matchers.is(EtatPartie.NULLE));
	}

	@Test
	public void etantDonneRougeAJoue_siJauneJoue_etAnalyseurRemontePartieGagnee_alorsJauneGagne() {
		etantDonneRougeAJoue();
		arbitre.faitJouer(1);
		Mockito.when(analyseur.estPartieGagnee(grille)).thenReturn(true);
		assertThat(arbitre.getEtatPartie(), Matchers.is(EtatPartie.GAGNEE_JAUNE));
	}

	@Test
	public void etantDonneJauneAJoue_siRougeJoue_etAnalyseurRemontePartieGagnee_alorsRougeGagne() {
		etantDonneJauneAJoue();
		arbitre.faitJouer(1);
		Mockito.when(analyseur.estPartieGagnee(grille)).thenReturn(true);
		assertThat(arbitre.getEtatPartie(), Matchers.is(EtatPartie.GAGNEE_ROUGE));
	}
}
