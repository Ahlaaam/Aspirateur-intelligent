package vacuumCleaner.exploration.problem;

import java.util.ArrayList;
import java.util.Objects;


import environment.Manor;
import environment.State;
import vacuumCleaner.exploration.problem.actions.*;

public class Problem {
//Attributes
	/**
	 * list of all possible actions
	 */
	private ArrayList<Action> actions = new ArrayList<Action>();

	/**
	 * Objective : No dust in the manor.
	 */
	private Manor goal = null;
	
	private Manor initialState = null;
	
//Constructor
	public Problem(Manor initialState, Manor objectif) {
		actions.add(new ActionVacuum());
		actions.add(new ActionPickUpObject());
		actions.add(new ActionMoveDown());
		actions.add(new ActionMoveUp());
		actions.add(new ActionMoveLeft());
		actions.add(new ActionMoveRight());
		
		goal = objectif;
		
		this.initialState = initialState;
	}
	
//Methods
	/**
	 * 
	 * @param obj
	 * @return true or false
	 */
	public boolean equalsToGoal(Object obj) {
		if (obj == null) {
			return false;
		}
		
		if (Manor.class != obj.getClass()) {
			return false;
		}
		Manor other = (Manor) obj;
		
		/* if the vacuum cleaner has a position equals to -1 as a goal, the position of the vacuum cleaner has no importance*/
		if (goal.getPosAspiratorX() != -1)
			if (goal.getPosAspiratorX() != other.getPosAspiratorX())
				return false;
		
		if (goal.getPosAspiratorY() != -1)
			if (goal.getPosAspiratorY() != other.getPosAspiratorY())
				return false;
		
		/* if the table JewelryArray is null in the goal, the Jewelry has no importance*/
		if (goal.getJewelryArray() != null)
			if (!Objects.equals(goal.getJewelryArray(), other.getJewelryArray()))
				return false;

		/* if the table DustArray is null in the goal, the Dust has no importance*/
		if (goal.getDustArray() != null)
			if (!Objects.equals(goal.getDustArray(), other.getDustArray()))
				return false;
		
		return true;
	}
	
	/**
	 * 
	 * @param state
	 * @return the heuristic calculated
	 */
	public int calculateHeuristic(State state) {
		if (state.getClass() != Manor.class)
			return Integer.MAX_VALUE;
		int heuristic = 0;
		Manor manor = (Manor)state;
		int distanceMax = 0;
		for (int index = 0; index < manor.getLength(); index++) {
			for (int indexW = 0; indexW < manor.getWidth(); indexW++) {
				if (manor.getDustArray().get(index).get(indexW)) {
					if (manor.getJewelryArray().get(index).get(indexW)) {
						heuristic += 2;
					}else {
						++heuristic;
					}
					
					if(manor.getPosAspiratorX() != index || manor.getPosAspiratorY() != indexW) {
						++heuristic;
					}
					
					distanceMax = Math.max(distanceMax, Math.abs(manor.getPosAspiratorX() - index) + Math.abs(manor.getPosAspiratorY() - indexW));
				}
			}
		}
		heuristic += distanceMax;
		return heuristic;
	}

//Getters and setters
	public ArrayList<Action> getActions() {
		return actions;
	}

	public Manor getInitialState() {
		return initialState;
	}

	public void setInitialState(Manor initialState) {
		this.initialState = initialState;
	}
}
