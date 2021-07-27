package vacuumCleaner;

import environment.Environment;
import environment.Manor;

public class Sensor {
	
//Attributes	
	private Manor manor = null;
	private double performance = 0;
	private int measureNumber = 0;
	private Environment environment = null;

//Constructor
	public Sensor() {
		environment = Environment.getEnvironment();
	}
	
//Method	
	public void observeEnvironment() {
		manor = environment.getCopiOfManor();
		performance = environment.getPerformance();
		measureNumber = environment.getMeasureNumber();
	}

//Getters	
	public Manor getManor() {
		return manor;
	}

	public double getPerformance() {
		return performance;
	}

	public int getMeasureNumber() {
		return measureNumber;
	}
}
