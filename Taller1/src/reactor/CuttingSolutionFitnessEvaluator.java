package reactor;

import java.util.List;

import org.uncommons.watchmaker.framework.FitnessEvaluator;

public class CuttingSolutionFitnessEvaluator implements FitnessEvaluator<CuttingSolution> {
	
	@Override
	public double getFitness(CuttingSolution candidate,
			List<? extends CuttingSolution> population) {
			
		if (candidate.getWastedArea() == 0) {
			System.out.println(candidate);
			System.exit(0);
		}
		
		return candidate.getWastedArea();
	}
	
	@Override
	public boolean isNatural() {
		return false;
	}
	
}
