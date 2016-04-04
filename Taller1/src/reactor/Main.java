package reactor;

import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.CandidateFactory;
import org.uncommons.watchmaker.framework.EvolutionEngine;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.FitnessEvaluator;
import org.uncommons.watchmaker.framework.GenerationalEvolutionEngine;
import org.uncommons.watchmaker.framework.SelectionStrategy;
import org.uncommons.watchmaker.framework.TerminationCondition;
import org.uncommons.watchmaker.framework.selection.TournamentSelection;

import gui.Window;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		MersenneTwisterRNG random = new MersenneTwisterRNG();
		CandidateFactory<CuttingSolution> candidateFactory = new CuttingSolutionCandidateFactory();
		EvolutionaryOperator<CuttingSolution> evolutionScheme = new CuttingSolutionEvolutionaryOperator();
		FitnessEvaluator<CuttingSolution> fitnessEvaluator = new CuttingSolutionFitnessEvaluator();
		SelectionStrategy<Object> selectionStrategy = new TournamentSelection(new Probability(0.7));
		TerminationCondition condition = new CuttingSolutionTerminationCondition();
		EvolutionEngine<CuttingSolution> evolutionEngine = new GenerationalEvolutionEngine<CuttingSolution>(
				candidateFactory, evolutionScheme, fitnessEvaluator, selectionStrategy, random);

		CuttingRequirements.calculateHeuristics();
		CuttingSolution cuttingSolution = evolutionEngine.evolve(100, 10, condition);

		Window.getInstance().clear();
		cuttingSolution.calculateSolutionFitness();
		System.out.println("Best Solution");
		System.out.println("Wasted area: " + cuttingSolution.getWastedArea());
		System.out.println("Needed steel sheets: " + cuttingSolution.getRequiredSteelSheets());
	}

}
