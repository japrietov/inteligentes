package reactor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

public class CuttingSolutionEvolutionaryOperator implements EvolutionaryOperator<CuttingSolution> {

	@Override
	public List<CuttingSolution> apply(List<CuttingSolution> selectedCandidates, Random rng) {
		List<CuttingSolution> evolvedPopulation = new ArrayList<>(selectedCandidates);
		int i = 0;

		while (i < (evolvedPopulation.size() - 1)) {
			CuttingSolution cuttingSolutionA = evolvedPopulation.get(i);
			i++;
			CuttingSolution cuttingSolutionB = evolvedPopulation.get(i);
			i++;

			crossOver(cuttingSolutionA, cuttingSolutionB, rng);
			mutation(cuttingSolutionA, rng);
			mutation(cuttingSolutionB, rng);
		}

		return evolvedPopulation;
	}

	private void crossOver(CuttingSolution cuttingSolutionA, CuttingSolution cuttingSolutionB, Random rng) {
		int piecesSize = CuttingRequirements.REQUIRED_PIECES.size();
		int crossOverPoint = rng.nextInt(piecesSize);
		List<CuttingPiece> aFirstPieces = new ArrayList<>(cuttingSolutionA.getPieces().subList(0, crossOverPoint));
		List<CuttingPiece> aSecondPieces = new ArrayList<>(
				cuttingSolutionA.getPieces().subList(crossOverPoint, piecesSize));
		List<CuttingPiece> bFirstPieces = new ArrayList<>(cuttingSolutionB.getPieces().subList(0, crossOverPoint));
		List<CuttingPiece> bSecondPieces = new ArrayList<>(
				cuttingSolutionB.getPieces().subList(crossOverPoint, piecesSize));

		aFirstPieces.addAll(bSecondPieces);
		bFirstPieces.addAll(aSecondPieces);

		cuttingSolutionA.setPieces(aFirstPieces);
		cuttingSolutionB.setPieces(bFirstPieces);
	}

	private void mutation(CuttingSolution cuttingSolution, Random rng) {
		for (CuttingPiece piece : cuttingSolution.getPieces()) {
			changeOrientation(piece, rng);
		}
	}

	private void changeOrientation(CuttingPiece piece, Random rng) {
		if (rng.nextBoolean()) {
			double width = piece.getWidth();
			piece.setWidth(piece.getHeight());
			piece.setHeight(width);
		}
	}

}
