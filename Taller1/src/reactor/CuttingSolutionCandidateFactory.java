package reactor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;

public class CuttingSolutionCandidateFactory extends AbstractCandidateFactory<CuttingSolution> {

	@Override
	public CuttingSolution generateRandomCandidate(Random random) {
		return createCuttingSolution(random);
	}

	private CuttingSolution createCuttingSolution(Random random) {
		List<CuttingPiece> requiredPieces = new ArrayList<>(CuttingRequirements.REQUIRED_PIECES);

		randomizePieces(random, requiredPieces);

		return new CuttingSolution(requiredPieces);
	}

	private void randomizePieces(Random random, List<CuttingPiece> requiredPieces) {
		for (CuttingPiece piece : requiredPieces) {
			changeOrientation(piece, random);
		}

		Collections.shuffle(requiredPieces, random);
	}

	private void changeOrientation(CuttingPiece piece, Random random) {
		if (random.nextDouble() < 0.5f) {
			double width = piece.getWidth();
			piece.setWidth(piece.getHeight());
			piece.setHeight(width);
		}
	}
}
