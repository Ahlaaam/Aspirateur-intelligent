package vacuumCleaner.exploration.problem.actions;

import environment.Manor;

public class ActionMoveLeft extends Action {

	public ActionMoveLeft() {
		super(AllActions.moveLeft, 1);
	}

	@Override
	public Manor doAction(Object initialState) {
		if (initialState.getClass() != Manor.class)
			return null;
		else {
			Manor manor = (Manor) initialState;
			if (!manor.vacuumCleanerMoveLeft())
				return null;
			else
				return manor;
		}
	}

}
