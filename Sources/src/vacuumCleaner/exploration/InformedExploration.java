/**
 * 
 */
package vacuumCleaner.exploration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;
import java.util.Map.Entry;

import environment.State;
import vacuumCleaner.exploration.problem.Problem;
import vacuumCleaner.exploration.problem.actions.Action;
import vacuumCleaner.exploration.problem.actions.AllActions;

public class InformedExploration {

//Methods	
	public Stack<AllActions> algoAStar(Problem problem){
		Stack<NodeAStar> fringe = new Stack<NodeAStar>();
		HashSet<State> closedState = new HashSet<State>();
		NodeAStar solution = null;
		NodeAStar node = new NodeAStar();
		node.setAction(null);
		node.setState(problem.getInitialState());
		node.setParent(null);
		node.setDepth(0);
		node.setPathCost(0);
		node.setNodeValue(problem.calculateHeuristic(problem.getInitialState()) + node.getPathCost());
		fringe.add(node);
		
		while (!fringe.isEmpty()) {
			node = fringe.pop();
			if (problem.equalsToGoal(node.getState())) {
				solution = node;
				break;
			}
			if (closedState.contains(node.getState()))
				continue;
			closedState.add(node.getState());
			for (NodeAStar newNode : expand(node, problem)) {
				Boolean inserted = false;
				/* Insert All */
				for(int index = 0; index < fringe.size(); ++index) {
					if(newNode.compareTo(fringe.get(index)) >= 0) {
						fringe.add(index,newNode);
						inserted = true;
						break;
					}
				}
				if (!inserted)
					fringe.add(newNode);
			}
		}
		
		return createSolution(solution);
	}
	
	private Stack<AllActions> createSolution(NodeAStar solution){
		Stack<AllActions> solitionPath = new Stack<AllActions>();

		if (solution != null) {
			NodeAStar iterativeNode = solution;
			while (iterativeNode.getParent() != null) {
				solitionPath.add(iterativeNode.getAction());
				iterativeNode = (NodeAStar) iterativeNode.getParent();
			}
		}
		return solitionPath;
	}
	
	private ArrayList<NodeAStar> expand(NodeAStar node,Problem problem) {
		ArrayList<NodeAStar> successors = new ArrayList<NodeAStar>();
		for (Entry<Action, State> entry: successorsFn(problem,node.getState()).entrySet()) {
			NodeAStar newNode = new NodeAStar();
			newNode.setAction(entry.getKey().getActionType());
			newNode.setState(entry.getValue());
			newNode.setParent(node);
			newNode.setDepth(node.getDepth() + 1);
			newNode.setPathCost(node.getPathCost() + entry.getKey().getCost());
			newNode.setNodeValue(problem.calculateHeuristic(newNode.getState()) + node.getPathCost());
			successors.add(newNode);
		}
		return successors;
	}
	
	private HashMap<Action, State> successorsFn(Problem problem, State state){
		HashMap<Action, State> actions = new HashMap<Action, State>();
		for(Action action : problem.getActions()) {
			State result = action.doAction(state.clone());
			if (result != null) {
				actions.put(action, result);
			}
		}
		return actions;
	}
}
