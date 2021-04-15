package test.com.softwaymedical.puissance4;

import com.sofwaymedical.puissance4.Analyseur;
import com.sofwaymedical.puissance4.Grille;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

public class AnalyeurTest {

	/*
	 * Etant donné grille vide, quand analyse alors partie non nulle et non gagnée.
	 * Etant donné grille avec colonne 1 avec 4 rouges d'affilé, quand analyse, alors partie gagnée
	 * Etant donné grille avec ligne 1 avec 4 rouges d'affilé, quand analyse, alors partie gagnée
	 * TODO diagonale
	 * Etant donné grille pleine et partie non gagnée, quand analyse, alors partie nulle
	 */
	private Grille grille;
	private Analyseur analyseur;

	private void etantDonneGrille() {
		grille = Mockito.mock(Grille.class);
		analyseur = new Analyseur();
	}

	@Test
	public void etantDonneGrilleVide_quandAnalyse_alorsPartieNonNulle_etNonGagnee() {
		etantDonneGrille();
		String contenuGrille = ".......\n.......\n.......\n.......\n.......\n.......\n.......";
		Mockito.when(grille.getGrille()).thenReturn(contenuGrille);
		MatcherAssert.assertThat(analyseur.estPartieNulle(grille), Matchers.is(false));
		MatcherAssert.assertThat(analyseur.estPartieGagnee(grille), Matchers.is(false));
	}

	@Test
	public void etantDonneGrilleAvecColonne1Avec4RougesDAffile_quandAnalyse_alorsPartieGagnee() {
		etantDonneGrille();
		String contenuGrille = "J......\nR......\nR......\nR......\nR......\nJ......";
		Mockito.when(grille.getGrille()).thenReturn(contenuGrille);
		MatcherAssert.assertThat(analyseur.estPartieNulle(grille), Matchers.is(false));
		MatcherAssert.assertThat(analyseur.estPartieGagnee(grille), Matchers.is(true));
	}

	@Test
	public void etantDonneGrilleAvecUneColonneAvec4JaunesDAffile_quandAnalyse_alorsPartieGagnee() {
		etantDonneGrille();
		String contenuGrille = "...J...\n...J...\n...J...\n...J...\n...R...\n...J...";
		Mockito.when(grille.getGrille()).thenReturn(contenuGrille);
		MatcherAssert.assertThat(analyseur.estPartieNulle(grille), Matchers.is(false));
		MatcherAssert.assertThat(analyseur.estPartieGagnee(grille), Matchers.is(true));
	}

	@Test
	public void etantDonneUneLigneAvec4JaunesDAffile_quandAnalyse_alorsPartieGagnee() {
		etantDonneGrille();
		String contenuGrille = ".......\n.......\n.......\n.......\n.......\nJJJJ...";
		Mockito.when(grille.getGrille()).thenReturn(contenuGrille);
		MatcherAssert.assertThat(analyseur.estPartieNulle(grille), Matchers.is(false));
		MatcherAssert.assertThat(analyseur.estPartieGagnee(grille), Matchers.is(true));
	}

	@Test
	public void etantDonneUneDiagonnaleMontanteAvec4RougesDAffile_quandAnalyse_alorsPartieGagnee() {
		etantDonneGrille();
		String contenuGrille =
				  "...R..." +
				"\n..R...." +
				"\n.R....." +
				"\nR......" +
				"\n......." +
				"\n......." +
				"\n.......";
		Mockito.when(grille.getGrille()).thenReturn(contenuGrille);
		MatcherAssert.assertThat(analyseur.estPartieNulle(grille), Matchers.is(false));
		MatcherAssert.assertThat(analyseur.estPartieGagnee(grille), Matchers.is(true));
	}
}
