package vacuumCleaner;

import java.util.ArrayList;
import java.util.HashMap;

public class LearningModule {
	private Sensor sensor = null;
	private int numLastMeasure = -1;
	private int currentLearning = 0;
	private int [] numberToLearn = {100, 10, 5, 1}; // 100 is infinity
	private HashMap<Integer, ArrayList<Double>> valueLearn = new HashMap<Integer, ArrayList<Double>>();
	private ArrayList<Double> listAverage = new ArrayList<Double>();
	private boolean learningCompleted = false;


	public LearningModule(Sensor sensor) {
		this.sensor = sensor;
	}
	
	public int learn() {
		if (learningCompleted)
			return numberToLearn[currentLearning];
		
		if (sensor.getMeasureNumber() <= numLastMeasure)
			return numberToLearn[currentLearning];
		numLastMeasure = sensor.getMeasureNumber();
		
		double performance = sensor.getPerformance();
		if (!valueLearn.containsKey(numberToLearn[currentLearning])) {
			valueLearn.put(numberToLearn[currentLearning], new ArrayList<Double>());
		}
		valueLearn.get(numberToLearn[currentLearning]).add(performance);
		System.out.println("Performance pour aux maximum " + numberToLearn[currentLearning] + " action avant exploration = " + performance);
		 if (valueLearn.get(numberToLearn[currentLearning]).size() >= 3) {
			 currentLearning++;
		 }
		 
		 if (currentLearning >= numberToLearn.length) {
			 for(int index = 0; index < numberToLearn.length; ++index) {
				 Double average = 0.0;
				 for (Double value : valueLearn.get(numberToLearn[index])){
					 average += value;
				 }
				 listAverage.add(average / (double) valueLearn.get(numberToLearn[index]).size());
			 }
			 System.out.println(listAverage);
			 Double maxValue = 0.0;
			 int maxPos = -1;
			 for(int index = 0; index < numberToLearn.length; ++index) {
				 if (listAverage.get(index) > maxValue) {
					 maxValue = listAverage.get(index);
					 maxPos = index;
				 }
			 }
			 
			 learningCompleted = true;
			 currentLearning = maxPos;
			 System.out.println("Choix après apprentissage" + numberToLearn[currentLearning]);
		 }
		return numberToLearn[currentLearning];
	}
	
	public void resetLeanrning() {
		learningCompleted = false;
		currentLearning = 0;
		valueLearn.clear();
		listAverage.clear();
	}


	public boolean isLearningCompleted() {
		return learningCompleted;
	}
}
