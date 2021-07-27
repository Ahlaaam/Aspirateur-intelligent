package vacuumCleaner;

import environment.Environment;

public class Effector {

//Attributes	
	private Environment environment = null;

//Constructor
	public Effector() {
		environment = Environment.getEnvironment();
	}

//Methods	
	public void moveDown() throws InterruptedException {
		Thread.sleep(300);
		environment.vacuumCleanerMoveDown();
	}

	public void moveLeft() throws InterruptedException {
		Thread.sleep(300);
		environment.vacuumCleanerMoveLeft();
	}

	public void moveRight() throws InterruptedException {
		Thread.sleep(300);
		environment.vacuumCleanerMoveRight();
	}

	public void moveUp() throws InterruptedException {
		Thread.sleep(300);
		environment.vacuumCleanerMoveUp();
	}

	public void pickupObject() throws InterruptedException {
		Thread.sleep(300);
		environment.vacuumCleanerPickUpObject();
	}

	public void vacuum() throws InterruptedException {
		Thread.sleep(300);
		environment.vacuumCleanerVacuum();
	}

}
