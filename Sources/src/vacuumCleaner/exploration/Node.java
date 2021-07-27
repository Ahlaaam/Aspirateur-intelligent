package vacuumCleaner.exploration;

import environment.State;
import vacuumCleaner.exploration.problem.actions.AllActions;

public class Node {
	
//Attributes	
	private Node parent = null;
	private State state = null;
	private int pathCost = 0;
	private AllActions action;
	private int depth = 0;
	
//Getters & setters
	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public int getPathCost() {
		return pathCost;
	}

	public void setPathCost(int pathCost) {
		this.pathCost = pathCost;
	}

	public AllActions getAction() {
		return action;
	}

	public void setAction(AllActions action) {
		this.action = action;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}
