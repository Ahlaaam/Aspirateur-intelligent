package environment;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PerformanceModule {

//Attributes
	final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
	private int energyConsumed = 0;
	private int vacuumError = 0;
	private int roomCleaned = 0;
	private double perfomance = 0;
	private int measureNumber = -1;

//Constructor
	public PerformanceModule() {
		final Runnable task = new Runnable() {

		   @Override
		   public void run() {
			   Manor newManor = Environment.getEnvironment().getCopiOfManor();
			   perfomance = ((double) roomCleaned / (double)energyConsumed) ;
			   int nbDust = 0;
			   for (ArrayList<Boolean> tab2 : newManor.getDustArray()) {
				   for (Boolean dust : tab2) {
					   if (dust)
						   nbDust ++;
				   }
			   }
			   perfomance = perfomance * (1- ((double) nbDust / 25));
			   perfomance *= 100;
			   perfomance -= 5 * vacuumError;
			   
			   roomCleaned = 0;
			   vacuumError = 0;
			   energyConsumed = 0;
			   measureNumber = getMeasureNumber() + 1;
		   }
		};

		executor.scheduleAtFixedRate(task, 10, 10, TimeUnit.SECONDS);
	}

//Getter
	public double getPerformance() {
		return perfomance;
	}

//Methods
	public void consumeEnergy() {
		this.energyConsumed ++;
	}

	public void vacuumError() {
		this.vacuumError ++;
	}

	public void vacuumDust() {
		this.roomCleaned ++;
	}

	public int getMeasureNumber() {
		return measureNumber;
	}
}
