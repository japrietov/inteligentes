package cajasMetalicas;

import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.CandidateFactory;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.FitnessEvaluator;

import processing.core.PApplet;

public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		PApplet.main(GUI.class.getName());
		Thread.sleep(1000);

		CandidateFactory<BoxSolution> candidateFactory = new SteelSheetCandidateFactory();
		EvolutionaryOperator<SteelSheet> evolutionScheme;
		FitnessEvaluator<SteelSheet> selectionStrategy;
		Random random = new Random();
		// EvolutionEngine<SteelSheet> evolutionEngine = new
		// GenerationalEvolutionEngine<>(
		// candidateFactory, evolutionScheme, selectionStrategy, rng);
		
		List<BoxSolution> generateRandomCandidate = candidateFactory.generateInitialPopulation(1,
				random);
				
		BoxRequirements.getInstance().calculateIdealWastedArea();
		
		for (BoxSolution boxSolution : generateRandomCandidate) {
			boxSolution.calculateSolutionProperties();
			
			System.out.println(
					boxSolution.getRequiredSteelSheets() + "--" + boxSolution.getWastedArea());
					
		}
	}
	
}
