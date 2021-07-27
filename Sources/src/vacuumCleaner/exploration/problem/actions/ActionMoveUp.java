package vacuumCleaner.exploration.problem.actions;

import environment.Manor;

public class ActionMoveUp extends Action {

	public ActionMoveUp() {
		super(AllActions.moveUp, 1);
	}

	@Override
	public Manor doAction(Object initialState) {
		if (initialState.getClass() != Manor.class)
			return null;
		else {
			Manor manor = (Manor) initialState;
			if (!manor.vacuumCleanerMoveUp())
				return null;
			else
				return manor;
		}
	}

}
