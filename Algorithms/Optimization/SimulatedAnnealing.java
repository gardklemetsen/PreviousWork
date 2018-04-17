/* This class will try to find the suboptimum or global optimum solution for the travelling salesmanproblem.
 * This will be done with the simulated annealing algorithm.
*/
import static java.lang.Math.*;
import java.lang.*;
import java.util.Scanner;

public class SimulatedAnnealing{

	//main
	public static void main(String [] args){

		int i, cities;

		Scanner s = new Scanner(System.in);
		System.out.print("Type in the number of cities: ");
		cities = s.nextInt();
		System.out.println();

		int [][] cost  = new int[cities][cities];
		int [] randomRoute = new int[cities];
		int [] bestSolution = new int[cities];

		//Generating random costs for all the edges
		cost = costRandomGeneration(cost);
		randomRoute = random(cost);

		bestSolution = simulatedAnnealing(randomRoute, cost);
		System.out.println("Initial solution cost: " + costCalculation(randomRoute, cost));
		System.out.println("Best solution cost: " + costCalculation(bestSolution, cost));

	//end of main
	}

	public static int [][] costRandomGeneration(int cost [][]){
		int i;
		int k;

		for(i=0; i<cost.length; i++){
			for(k=i; k<cost.length; k++){
				if(k==i){
					cost[i][k] = 0;
				}
				else{
					cost[i][k] = (int)(Math.random()*10+1);
					cost[k][i] = cost[i][k];
				}
			}
		}

		return cost;
	}

	public static int costCalculation(int[] route, int [][] cost){

		int i;
		int totalCost = 0;

		for(i=0; i<route.length-1; i++){
				totalCost += cost[route[i]][route[i+1]];
		}

		totalCost += cost[route[route.length-1]][route[0]];

		return totalCost;
	}

	public static int [] random(int [][] cost){

		int i;
		int randomPosition;
		int temp;
		int [] route = new int[cost.length];

		for(i=0; i<route.length; i++){
			route[i] = i;
		}

		for (i=0; i<route.length; i++) {
			randomPosition = (int)(Math.random()*(route.length));
			temp = route[i];
			route[i] = route[randomPosition];
			route[randomPosition] = temp;
		}

		return route;
	}

	public static int [] simulatedAnnealing(int [] route, int [][] cost){
		//e^x = Math.exp();
		//probability of acceptance = 90 % as initial.
		//After doing pre processing I found that to achieve an approximate
		//90 % initial acception ratio, we need it to be 50.

		int [] bestSolution = new int[route.length];
		int [] newSolution = new int[route.length];
		int [] oldSolution = new int[route.length];

		//Variables used for parameter tuning only, uncomment all comments with //-- to do parameter tuning instead
		//and comment out the outer while loop.

		//--int badSol = 0;
		//--int badSolAcc = 0;
		//--double p;
		//--int check = 0;

		int equilibrium = 0;
		int counter = 0;
		int i;
		int change = 0;
		double t = 50;
		int newCost, oldCost;


		//copying routes
		for(i=0; i<route.length; i++){
			newSolution[i] = route[i];
			oldSolution[i] = route[i];
		}

		while(counter<10000){
			while(equilibrium<100){

				newSolution = randomMoves(newSolution);
				newCost = costCalculation(newSolution, cost);
				oldCost = costCalculation(oldSolution, cost);
				change = newCost - oldCost;

				if(change<0){
					//--check=1;
					for(i=0; i<bestSolution.length; i++){
						bestSolution[i] = newSolution[i];
						oldSolution[i] = newSolution[i];
					}
				}
				else if(Math.random() <= Math.exp((-change)/t)){
					for(i=0; i<bestSolution.length; i++){
						newSolution[i] = oldSolution[i];
					}
					//--check=0;
					//--badSolAcc++;
				}
				//--if(check!=1){
				//--	check=0;
				//--	badSol++;
				//--}
				equilibrium++;
			}

			t = t*0.99999;
			counter++;
			equilibrium = 0;
			//--p = (double)badSolAcc/(double)badSol;
			//--System.out.println("badSol: " + badSol);
			//--System.out.println("badSolAcc: " + badSolAcc);
			//--System.out.println("Prob: " + p);
		}

		return bestSolution;
	}

	public static int [] randomMoves(int [] newSolution){

		int random1 = (int)(Math.random()*newSolution.length);
		int random2 = (int)(Math.random()*newSolution.length);
		int [] array = new int[newSolution.length];
		int temp, i;

		for(i=0; i<newSolution.length; i++){
			array[i] = newSolution[i];
		}

		temp = array[random1];
		array[random1] = array[random2];
		array[random2] = temp;

		return array;

	}

//end of class
}