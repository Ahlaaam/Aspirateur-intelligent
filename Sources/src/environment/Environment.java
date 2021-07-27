package environment;


import environment.interfaceEnv.ExplorationAlgo;
import environment.interfaceEnv.MainInterface;
import main.Main;

public class Environment implements Runnable{

//Attributes
	private Manor manor;
	private PerformanceModule performanceModule;
	private MainInterface mainInterface = null;

	static private Environment environment = null;

//Constructor & getter
	private Environment() {
		manor = new Manor(5, 5);
		performanceModule = new PerformanceModule();
		mainInterface = new MainInterface();
		mainInterface.setVisible(true);
	}

	static public Environment getEnvironment() {
		if (environment == null) {
			environment = new Environment();
		}
		return environment;
	}

//Methods
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("D�but de la vie de l'environnement.");
		double randomNumber = 0;
		double randomNumberPercent = 0;
		while(true) {
			randomNumber = Math.random();
			randomNumberPercent = randomNumber*100;
			if(randomNumberPercent < 9) {
				this.manor.putDust((int)((Math.random()*100) % 5), (int)((Math.random()*100) % 5));
			}
			randomNumber = Math.random();
			randomNumberPercent = randomNumber*100;
			if(randomNumberPercent < 4) {
				this.manor.putJewelry(((int)(Math.random()*100) % 5), (int)((Math.random()*100) % 5));
			}
			mainInterface.update(manor);
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				System.out.println("The environment has been interrupted.");
				e.printStackTrace();
			}
		}
	}

	public void vacuumCleanerMoveDown() {
		manor.vacuumCleanerMoveDown();
		performanceModule.consumeEnergy();
	}

	public void vacuumCleanerMoveLeft() {
		manor.vacuumCleanerMoveLeft();
		performanceModule.consumeEnergy();
	}

	public void vacuumCleanerMoveRight() {
		manor.vacuumCleanerMoveRight();
		performanceModule.consumeEnergy();
	}

	public void vacuumCleanerMoveUp() {
		manor.vacuumCleanerMoveUp();
		performanceModule.consumeEnergy();
	}

	public void vacuumCleanerPickUpObject() {
		manor.vacuumCleanerPickUpObject();
		performanceModule.consumeEnergy();
	}

	public void vacuumCleanerVacuum() {
		int res = manor.vacuumCleanerVacuum();
		performanceModule.consumeEnergy();
		if (res == -2) {
			performanceModule.vacuumError();
		}
		else if (res == -1) {
			performanceModule.vacuumError();
			performanceModule.vacuumDust();
		}
		else if (res == 1) {
			performanceModule.vacuumDust();
		}

	}

	public void changeAlgo(ExplorationAlgo newAlgo) {
		Main.changeRobotAlgo(newAlgo);
		
	}
	
//Getters
	public Manor getCopiOfManor() {
		return (Manor) manor.clone();
	}

	public double getPerformance() {
		return performanceModule.getPerformance();
	}
	
	public int getMeasureNumber() {
		return performanceModule.getMeasureNumber();
	}

}
