package reactor;

import org.uncommons.watchmaker.framework.PopulationData;
import org.uncommons.watchmaker.framework.TerminationCondition;

public class CuttingSolutionTerminationCondition implements TerminationCondition {

	@Override
	public boolean shouldTerminate(PopulationData<?> populationData) {

		return populationData.getElapsedTime() > 6e4;
	}

}
