package cajasMetalicas;

import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.CandidateFactory;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.FitnessEvaluator;

public class Main {

	public static void main(String[] args) {
		CandidateFactory<BoxSolution> candidateFactory = new SteelSheetCandidateFactory();
		EvolutionaryOperator<SteelSheet> evolutionScheme;
		FitnessEvaluator<SteelSheet> selectionStrategy;
		Random random = new Random();
		// EvolutionEngine<SteelSheet> evolutionEngine = new
		// GenerationalEvolutionEngine<>(
		// candidateFactory, evolutionScheme, selectionStrategy, rng);

		List<BoxSolution> generateRandomCandidate = candidateFactory.generateInitialPopulation(100,
				random);

		BoxRequirements.getInstance().calculateIdealWastedArea();

		for (BoxSolution boxSolution : generateRandomCandidate) {
			boxSolution.calculateSolutionProperties();

			System.out.println(
					boxSolution.getRequiredSteelSheets() + "--" + boxSolution.getWastedArea());

		}
	}

}
