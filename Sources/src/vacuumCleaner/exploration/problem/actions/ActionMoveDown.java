package vacuumCleaner.exploration.problem.actions;

import environment.Manor;

public class ActionMoveDown extends Action {

	public ActionMoveDown() {
		super(AllActions.moveDown, 1);
	}

	@Override
	public Manor doAction(Object initialState) {
		if (initialState.getClass() != Manor.class)
			return null;
		else {
			Manor manor = (Manor) initialState;
			if (!manor.vacuumCleanerMoveDown())
				return null;
			else
				return manor;
		}
		// TODO Auto-generated method stub
	}

}
