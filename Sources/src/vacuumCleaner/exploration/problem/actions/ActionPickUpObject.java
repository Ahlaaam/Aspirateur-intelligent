package vacuumCleaner.exploration.problem.actions;

import environment.Manor;

public class ActionPickUpObject extends Action {

	public ActionPickUpObject() {
		super(AllActions.pickUpObject, 1);
	}

	@Override
	public Manor doAction(Object initialState) {
		if (initialState.getClass() != Manor.class)
			return null;
		else {
			Manor manor = (Manor) initialState;
			if (!manor.vacuumCleanerPickUpObject())
				return null;
			else
				return manor;
		}
	}

}
