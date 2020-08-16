import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NearestNeighbor {
	//data value arrays
	static double[][] testingValues = new double [75][4];
	static double[][] trainingValues = new double [75][4];
	
	//distances
	static double[] distance = new double [75];
	static double shortestDistance = 0;
	
	//accuracy
	static double accuracy = 0;
	
	//Flower type arrays
	static String[] testingFlowerType = new String[75];
	static String[] trainingFlowerType = new String[75];
	static String[] closestFlowerType = new String [75];
	
	
	//Training data file scan
	static double[][] trainDataScan() throws FileNotFoundException  {
		File training = new File("iris-training-data.csv");
		Scanner trainingData = new Scanner(training);
		
		int row = 0;
		while(trainingData.hasNext()) {
			String exNumLine = trainingData.nextLine();
			String[] exNumLineParts = exNumLine.split(",");
			
			//gather all data of the flower petal and sepal
			for (int col = 0; col <4; col++) {
				trainingValues[row][col] = Double.parseDouble(exNumLineParts[col]);
			}
			//Obtain name of the flower
			trainingFlowerType[row] = exNumLineParts[4];
			row++;
		}
		trainingData.close();
		return trainingValues;
	}
	
	//Test Data file scan
	static double[][] testDataScan() throws FileNotFoundException {
		File test = new File("iris-testing-data.csv");
		Scanner testData = new Scanner(test);
		
		int row = 0;
		while (testData.hasNext()) {
			String exNumLine = testData.nextLine();
			String[] exNumLineParts = exNumLine.split(",");
			
			//gather all data of flower petal and sepal
			for (int col =0; col < 4; col++) {
				testingValues[row][col] = Double.parseDouble(exNumLineParts[col]);
			}
			//Obtain name of flower
			testingFlowerType[row] = exNumLineParts[4];
			row++;
		}
		testData.close();
		return testingValues;
	}
	
	//Find nearest data point
	static double[][] nearestFlower() {
		int rowShort = 0;
		for(int rowTrain=0; rowTrain < 75; rowTrain++) {
			for(int rowTest = 0; rowTest <75; rowTest++) {
				//Distance Calculations
				double sepalLengthDiff = testingValues[rowTest][0] - trainingValues[rowTrain][0];
				double sepalWidthDiff = testingValues[rowTest][1] - trainingValues[rowTrain][1];
				double petalLengthDiff = testingValues[rowTest][2] - trainingValues[rowTrain][2];
				double petalWidthDiff = testingValues[rowTest][3] - trainingValues[rowTrain][3];
				
				double discriminant = Math.pow(sepalLengthDiff, 2) + Math.pow(sepalWidthDiff, 2) + Math.pow(petalLengthDiff,2) + Math.pow(petalWidthDiff, 2);
				
				distance[rowTrain] = Math.sqrt(discriminant);
				
				//distance less than shortest, ==make shortest
				if (rowTest ==0 || distance[rowTrain] < shortestDistance) {
					rowShort = rowTest;
					shortestDistance = distance[rowTrain];
				}
			}
			//closest data point equal to closest training data
			closestFlowerType[rowTrain] = testingFlowerType[rowShort];
		}
		return closestFlowerType;
	}
	//Accuracy of test data
	static double preditionAccuracy() {
		int correctGuess = 0;
		for (int row = 0; row < 75; row++) {
			switch (closestFlowerType[row]) {
			//case when closest flower type is Iris-setosa
			case "Iris-setosa":
				if(closestFlowerType[row].equals(trainingFlowerType[row])) {
					correctGuess++;
				}
				break;
				//closest flower type is Iris-versicolor
			case "Iris-versicolor":
				if(closestFlowerType[row].equals(trainingFlowerType[row])) {
					correctGuess++;
					
				}
				break;
				//Iris-virginica
			case "Iris-virginica":
				if(closestFlowerType[row].equals(trainingFlowerType[row])) {
					correctGuess++;
					
				}
				break;
			}
		}
		accuracy = (double)correctGuess / 75.0;
		return accuracy;
	}
//print results
	static void printResults() {
		//Top Lables
		System.out.println("EX#: TRUE LABEL, PREDICTED LABEL");
		
		//print data reults
		for (int row = 0; row <75; row++) {
			System.out.print(row + 1 + ":");
		//flower labels
			System.out.print(trainingFlowerType[row] + "");
			System.out.print(closestFlowerType[row] + "\n");
		}
		//print out accuracy of test
		preditionAccuracy();
		System.out.println("ACCURACY:" + accuracy + "\n");
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Programming Fundamentals");
		System.out.println("Name: Tera Smith");
		System.out.println("PROGRAMMING ASSIGNMENT 3");
		
		//finding and analyzing data
		trainDataScan();
		testDataScan();
		nearestFlower();
		
		//print results
		printResults();
	
		

	}

}
