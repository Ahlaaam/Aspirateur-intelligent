package vacuumCleaner.exploration.problem.actions;

import environment.Manor;

public class ActionMoveRight extends Action {

	public ActionMoveRight() {
		super(AllActions.moveRight, 1);
	}

	@Override
	public Manor doAction(Object initialState) {
		if (initialState.getClass() != Manor.class)
			return null;
		else {
			Manor manor = (Manor) initialState;
			if (!manor.vacuumCleanerMoveRight())
				return null;
			else
				return manor;
		}
	}

}
