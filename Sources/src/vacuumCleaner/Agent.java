package vacuumCleaner;

import java.util.Stack;

import environment.Manor;
import environment.interfaceEnv.ExplorationAlgo;
import vacuumCleaner.exploration.InformedExploration;
import vacuumCleaner.exploration.UninformedExploration;
import vacuumCleaner.exploration.problem.Problem;
import vacuumCleaner.exploration.problem.actions.AllActions;

public class Agent implements Runnable{

//Attributes	
	private Sensor sensor;
	private Effector effector;
	private Manor belief = null;
	private Manor desire = null;
	private Stack<AllActions> intention = null;
	private ExplorationAlgo explorationAlgo = null;
	private UninformedExploration uninformedExploration = null;
	private InformedExploration informedExploration = null;
	private LearningModule learningModule = null;
	private int nbActionBeforLearn = 0;

//Constructor	
	public Agent(ExplorationAlgo explorationAlgo) {
		sensor = new Sensor();
		effector = new Effector();
		this.explorationAlgo = explorationAlgo;
		uninformedExploration = new UninformedExploration();
		informedExploration = new InformedExploration();
		learningModule = new LearningModule(sensor);
		
		/* set Desire */
		desire = new Manor(5,5);
		desire.setPosAspiratorX(-1);
		desire.setPosAspiratorY(-1);
		desire.setJewelryArray(null);
	}

//Methods	
	@Override
	public void run() {
		System.out.println("début de la vie de l'agent.");	
		AllActions nextAction = null;
		while(true) {
			sensor.observeEnvironment();
			updateInternalState();
			nextAction = chooseNextAction();
			try {
				doAction(nextAction);
			} catch (InterruptedException e) {
				System.out.println("Action impossible !");
				e.printStackTrace();
			}	
		}
	}
	
	private void doAction(AllActions action) throws InterruptedException {
		switch (action) {
		case moveDown:
			effector.moveDown();
			break;
		case moveLeft:
			effector.moveLeft();
			break;
		case moveRight:
			effector.moveRight();
			break;
		case moveUp:
			effector.moveUp();
			break;
		case pickUpObject:
			effector.pickupObject();
			break;
		case vacuum:
			effector.vacuum();
			break;
		default:
			break;
		}
	}

	private AllActions chooseNextAction() {
		if (intention == null || intention.isEmpty() || nbActionBeforLearn == 0) {
			nbActionBeforLearn  = learningModule.learn();
			Problem problem = new Problem(belief, desire);
			switch (explorationAlgo) {
			case informedAStar:
				intention = informedExploration.algoAStar(problem);
				break;
			case nonInformedIterativeDeepForSearch:
				intention = uninformedExploration.algoIterativeDeepeningSearch(problem);
				break;
			}
			
		}
		--nbActionBeforLearn;
		if (intention.isEmpty())
			return AllActions.doNothing;
		
		return intention.pop();
	}

	private void updateInternalState() {
		belief = sensor.getManor();
	}
	
	public void setExplorationAlgo(ExplorationAlgo explorationAlgo) {
		this.explorationAlgo = explorationAlgo;
		learningModule.resetLeanrning();
	}

	public int getNbActionBeforLearn() {
		return nbActionBeforLearn;
	}

	public Boolean getLerninigEnd() {
		return learningModule.isLearningCompleted();
	}
}



















