package vacuumCleaner.exploration.problem.actions;

import environment.Manor;

public abstract class Action {
	
	private int actionCost = 1;
	private AllActions actionType = null;

	public Action(AllActions actionType, int actionCost) {
		this.actionType = actionType;
		this.actionCost = actionCost;
	}

	public abstract Manor doAction(Object initialState);

	public int getCost() {
		return actionCost;
	}
	
	public AllActions getActionType() {
		return actionType;
	}
}
