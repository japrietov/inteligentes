package cajasMetalicas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;

public class SteelSheetCandidateFactory extends AbstractCandidateFactory<BoxSolution> {

	@Override
	public BoxSolution generateRandomCandidate(Random random) {
		return createBoxSolution(random);
	}

	private BoxSolution createBoxSolution(Random random) {
		List<Piece> requiredPieces = new ArrayList<>(
				BoxRequirements.getInstance().getRequiredPieces());

		randomizePieces(random, requiredPieces);

		return new BoxSolution(requiredPieces);
	}

	private void randomizePieces(Random random, List<Piece> requiredPieces) {
		for (Piece piece : requiredPieces) {
			changeOrientation(piece, random);
		}

		Collections.shuffle(requiredPieces, random);
	}

	private void changeOrientation(Piece piece, Random random) {
		if (random.nextDouble() < 0.5f) {
			double width = piece.getWidth();
			piece.setWidth(piece.getHeight());
			piece.setHeight(width);
		}
	}
}
