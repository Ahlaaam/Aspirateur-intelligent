package main;

import environment.Environment;
import environment.interfaceEnv.ExplorationAlgo;
import vacuumCleaner.Agent;

public class Main {

//Attributes
	private static Agent agent;
	
//Methods
	public static void changeRobotAlgo(ExplorationAlgo explorationAlgo) {
		agent.setExplorationAlgo(explorationAlgo);
	}

	public static void main(String[] args) {
		System.out.println("Start");
		agent = new Agent(ExplorationAlgo.nonInformedIterativeDeepForSearch);
		new Thread(Environment.getEnvironment()).start();
		
		new Thread(agent).start();
	}

	public static Agent getAgent() {
		return agent;
	}
}
