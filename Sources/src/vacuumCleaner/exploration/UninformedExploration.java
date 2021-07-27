package vacuumCleaner.exploration;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Stack;

import environment.State;
import vacuumCleaner.exploration.problem.Problem;
import vacuumCleaner.exploration.problem.actions.Action;
import vacuumCleaner.exploration.problem.actions.AllActions;

public class UninformedExploration {
	
//Attributes	
	/**
	 * the solution when a solution is find
	 */
	private Node solution = null; 
	
	private HashSet<State> ancestorState;
	
//Methods
	public Stack<AllActions> algoIterativeDeepeningSearch(Problem problem) {
		solution = null;
		SearchResult result;
		int limite = 0;
		ancestorState = new HashSet<State>();
		do {
			result = depthLimitedSearch(problem, limite);
			++limite;

		}while(result == SearchResult.cutoff);
		
		return createSolution();
	}
	
	private Stack<AllActions> createSolution(){
		Stack<AllActions> solitionPath = new Stack<AllActions>();

		if (solution != null) {
			Node iterativeNode = solution;
			while (iterativeNode.getParent() != null) {
				solitionPath.add(iterativeNode.getAction());
				iterativeNode = iterativeNode.getParent();
			}
		}
		return solitionPath;
	}
	
	private SearchResult depthLimitedSearch(Problem problem, int limite){
		Node node = new Node();
		node.setAction(null);
		node.setState(problem.getInitialState());
		node.setParent(null);
		node.setDepth(0);
		node.setPathCost(0);
		return recursiveDLS(node, problem, limite);
	}
	
	private SearchResult recursiveDLS(Node node, Problem problem, int limite) {
		if (ancestorState.contains(node.getState())/*isAncestor(node)*/) {
			return SearchResult.fail;
		}
		Boolean cutoffOccured = false;
		if (problem.equalsToGoal(node.getState())) {
			solution = node;
			return SearchResult.sucess;
		}
		else if(node.getDepth() == limite) {
			return SearchResult.cutoff;
		}
		else {
			ancestorState.add(node.getState());
			for (Node childNode : expand(node,problem)) {
				SearchResult result = recursiveDLS(childNode, problem, limite);
				if (result == SearchResult.cutoff) {
					cutoffOccured = true;
				}
				else if(result == SearchResult.sucess) {
					return SearchResult.sucess;
				}
			}
			ancestorState.remove(node.getState());
			if (cutoffOccured) {
				return SearchResult.cutoff;
			}
			else {
				return SearchResult.fail;
			}
		}
	}
	
	private ArrayList<Node> expand(Node node,Problem problem) {
		ArrayList<Node> successors = new ArrayList<Node>();
		for (Entry<Action, State> entry: successorsFn(problem,node.getState()).entrySet()) {
			Node newNode = new Node();
			newNode.setAction(entry.getKey().getActionType());
			newNode.setState(entry.getValue());
			newNode.setParent(node);
			newNode.setDepth(node.getDepth() + 1);
			newNode.setPathCost(node.getPathCost() + entry.getKey().getCost());
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
	
//Enum
	private enum SearchResult{
		sucess,
		fail,
		cutoff,
	}
}
