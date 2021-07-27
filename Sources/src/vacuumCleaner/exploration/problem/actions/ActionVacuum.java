package vacuumCleaner.exploration.problem.actions;

import environment.Manor;

public class ActionVacuum extends Action {

	public ActionVacuum() {
		super(AllActions.vacuum, 1);
	}

	@Override
	public Manor doAction(Object initialState) {
		if (initialState.getClass() != Manor.class)
			return null;
		else {
			Manor manor = (Manor) initialState;
			if (manor.vacuumCleanerVacuum() == 1)
				return manor;
			else
				return null;
		}
	}

}
