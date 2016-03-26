package reactor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.EvaluatedCandidate;
import org.uncommons.watchmaker.framework.SelectionStrategy;

public class CuttingSolutionSelectionStrategy implements SelectionStrategy<CuttingSolution> {

	@Override
	public <S extends CuttingSolution> List<S> select(List<EvaluatedCandidate<S>> population,
			boolean naturalFitnessScores, int selectionSize, Random rng) {
		//
		// List<EvaluatedCandidate<S>> seleccion = new ArrayList<>(population);
		// population.get(0).getFitness()
		// System.out.println(population.get(0));
		// System.out.println(population.get(1));
		// System.out.println(population.get(0).compareTo(population.get(1)));
		//
		// for (int i = 0; i < selectionSize; i++) {
		// for (int j = 0; j < population.size(); j++) {
		// }
		// }
		//

		for (EvaluatedCandidate<S> evaluatedCandidate : population) {
			System.out.println(evaluatedCandidate.getFitness());
		}

		return new ArrayList<>();
	}

}
