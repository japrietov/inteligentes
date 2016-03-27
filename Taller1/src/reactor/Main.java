package reactor;

import java.awt.Color;
import java.util.List;

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
		Window.getInstance();
		MersenneTwisterRNG random = new MersenneTwisterRNG();
		CandidateFactory<CuttingSolution> candidateFactory = new CuttingSolutionCandidateFactory();
		EvolutionaryOperator<CuttingSolution> evolutionScheme = new CuttingSolutionEvolutionaryOperator();
		FitnessEvaluator<CuttingSolution> fitnessEvaluator = new CuttingSolutionFitnessEvaluator();
		SelectionStrategy<Object> selectionStrategy = new TournamentSelection(new Probability(0.7));
		TerminationCondition condition = new CuttingSolutionTerminationCondition();
		EvolutionEngine<CuttingSolution> evolutionEngine = new GenerationalEvolutionEngine<CuttingSolution>(
				candidateFactory, evolutionScheme, fitnessEvaluator, selectionStrategy, random);
				
		CuttingRequirements.calculateHeuristics();
		// evolutionEngine.evolve(2, 0, condition);
		List<CuttingSolution> generateRandomCandidate = candidateFactory
				.generateInitialPopulation(10, random);
				
		for (CuttingSolution boxSolution : generateRandomCandidate) {
			Window.getInstance().setBackground(Color.BLACK);
			
			System.out.println(
					boxSolution.getRequiredSteelSheets() + "--" + boxSolution.getWastedArea());
		}
		
	}
	
}
