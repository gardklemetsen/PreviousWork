import java.lang.Math.*;
import java.lang.*;
import java.util.Scanner;

/**
 * This program will try to find the optimal solution for "the travelling salesman problem"
 * The program uses 3 different methods that randomly generates a route, after the routes are generated
 * they are passed into the last method that will randomly arrange the routes to find a better solution.
 * The program is doing this 1000 times, and calculates the total times each method had the best cost and best time.
 */
public class TravellingSalesmanProblem{

	//main
	public static void main(String [] args){

		int cities, i;
		Scanner s = new Scanner(System.in);

		//user types in how many cities the program will be working with
		System.out.print("Type in the number of cities: ");
		cities = s.nextInt();
		System.out.println();

		int [][] cost  = new int[cities][cities];

		//generating random costs for all the edges
		cost = costRandomGeneration(cost);

		//running a benchmark test
		benchmark(cost);

	//end of main
	}

	/**
     * Generates a random cost for all the edges for each city.
	 * The cost to travel from to the same city that you find yourself in, is 0.
     */
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
	/**
     * Calculates and returns the cost for the route that is passed to this method.
     */
	public static int costCalculation(int[] route, int [][] cost){

		int i;
		int totalCost = 0;

		for(i=0; i<route.length-1; i++){
				totalCost += cost[route[i]][route[i+1]];
		}

		totalCost += cost[route[route.length-1]][route[0]];

		return totalCost;
	}

	/**
     * Generates a random route by swapping the cities randomly for each iteration.
     */
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

	/**
     * This method generates a random route 10 times by calling the random() method.
	 * It then calculates the cost for this route, and the route that has the lowest cost each time is saved.
	 * The best route is returned.
     */
	public static int [] randomIterative(int [][] cost){

		int i, tempCost;
		int currentCost = Integer.MAX_VALUE;
		int [] route = new int[cost.length];
		int [] tempRoute = new int[cost.length];

		for(i=0; i<10; i++){

			tempRoute = random(cost);
			tempCost = costCalculation(tempRoute, cost);

			if(tempCost<currentCost){
				route = tempRoute;
				currentCost = tempCost;
			}
		}

		return route;
	}

	/**
     * This method will rearrange the route depending on the lowest cost from the current city to the next.
	 * This method takes a random starting city, and will choose the next city based on the cost to this city.
	 * It will iterate through all of the remaining cities and choose the path that has the lowest cost each time.
     */
	public static int [] greedy(int [][] cost){

		int i, k, temp, currentCost;
		int city = (int)(Math.random()*cost.length);
		int tempIndex = 0;
		int check = 0;
		int [] route = new int[cost.length];

		for(i=0; i<cost.length; i++){
			route[i] = i;
		}

		temp = route[0];
		route[0] = route[city];
		route[city] = temp;

		for(i=0; i<route.length-1; i++){
			currentCost = cost[route[i]][route[i+1]];

			for(k=i+2; k<route.length; k++){
				if(cost[route[i]][route[k]]<currentCost){
					currentCost = cost[route[i]][route[k]];
					tempIndex = k;
					check = 1;
				}
				else if(cost[route[i]][route[k]]==currentCost){
					if((int)(Math.random()*2)==0){
						currentCost = cost[route[i]][route[k]];
						tempIndex = k;
						check = 1;
					}

				}
			}

			if(check==1){
				temp = route[i+1];
				route[i+1] = route[tempIndex];
				route[tempIndex] = temp;
				check = 0;
			}
		}

		return route;
	}

	/**
     * This is the phase 2 algorithm.
	 * This method will take a initial route (solution) and swap the cities randomly
	 * The best route is saved and returned. All of the phase 1 solutions will be sent to this method to be optimized.
     */
	public static int [] greedyHeuristic(int [] route, int [][] cost){

		int city1, city2, currentCost, bestCost, temp;
		int i;
		int [] bestRoute = new int[route.length];

		bestCost = costCalculation(route, cost);

		//copying route
		for(i=0; i<route.length; i++){
			bestRoute[i] = route[i];
		}

		for(i=0; i<10000; i++){
			city1 = (int)(Math.random()*route.length);
			city2 = (int)(Math.random()*route.length);

			if(city1!=city2){

				//swapping the two cities
				temp = bestRoute[city1];
				bestRoute[city1] = bestRoute[city2];
				bestRoute[city2] = temp;

				//updating cost
				currentCost = costCalculation(bestRoute, cost);

				//if cost is lower, keep the cost
				if(currentCost < bestCost){
					bestCost = currentCost;
				}
				//if the cost is not lower, change the route back to the original
				else{
					temp = bestRoute[city1];
					bestRoute[city1] = bestRoute[city2];
					bestRoute[city2] = temp;
				}
			}
		}

		return bestRoute;
	}

	/**
	 * This method is only for testing purposes. The method will call all of the phase 1 methods 1000 times.
	 * For each iteration it will calculate the time it took and the cost for each of the solutions.
	 * It will then print out how many times each method did the best.
     */
	public static void benchmark(int [][] cost){

		int [] randomOptimized, randomIOptimized, greedyOptimized = new int[cost.length];
		int [] randomRoute, randomIRoute, greedyRoute = new int[cost.length];

		long startTime, endTime, randomTime, randomITime, greedyTime;
		long bestTime = 0;
		int randomCost, randomICost, greedyCost, i;
		int bestCost = 0, rCount = 0, rICount = 0, gCount = 0, rTCount = 0, rITCount = 0, gTCount = 0;
		long avgRTime = 0, avgRITime = 0, avgGTime = 0;

		String fastestMethod = " ";
		String lowestCostMethod = " ";

		for(i = 0; i<100; i++){
			//random
			startTime = System.nanoTime();
			randomRoute = random(cost);
			endTime = System.nanoTime();
			randomTime = endTime - startTime;
			avgRTime += randomTime;

			//random iterative
			startTime = System.nanoTime();
			randomIRoute = randomIterative(cost);
			endTime = System.nanoTime();
			randomITime = endTime - startTime;
			avgRITime += randomITime;

			//greedy
			startTime = System.nanoTime();
			greedyRoute = greedy(cost);
			endTime = System.nanoTime();
			greedyTime = endTime - startTime;
			avgGTime += greedyTime;

			randomOptimized = greedyHeuristic(randomRoute, cost);
			randomCost = costCalculation(randomOptimized, cost);

			randomIOptimized = greedyHeuristic(randomIRoute, cost);
			randomICost = costCalculation(randomIOptimized, cost);

			greedyOptimized = greedyHeuristic(greedyRoute, cost);
			greedyCost = costCalculation(greedyOptimized, cost);

			bestCost = Math.min(randomCost, randomICost);
			bestCost = Math.min(bestCost, greedyCost);

			bestTime = Math.min(randomTime, greedyTime);
			bestTime = Math.min(bestTime, randomITime);

			if(bestCost==randomCost){
				lowestCostMethod = "Random method";
				rCount++;
			}
			else if(bestCost==randomICost){
				lowestCostMethod = "Random iterative method";
				rICount++;
			}
			else if(bestCost==greedyCost){
				lowestCostMethod = "Greedy method";
				gCount++;
			}

			if(randomTime <= randomITime && randomTime <= greedyTime){
				fastestMethod = "Random method";
				rTCount++;
			}
			else if(randomITime <= randomTime && randomITime <= greedyTime){
				fastestMethod = "Random iterative method";
				rITCount++;
			}
			else if(greedyTime <= randomTime && greedyTime <= randomITime){
				fastestMethod = "Greedy method";
				gTCount++;
			}
		}

		System.out.println("Random method had the best calculation time a total of: " + rTCount);
		System.out.println("Random method had the best result a total of: " + rCount);
		System.out.println("Average computation time for 100 iterations: " + (avgRTime/100)/Math.pow(10,6) + "ms\n");

		System.out.println("Random iterative method had the best calculation time a total of: " + rITCount);
		System.out.println("Random iterative method had the best result a total of: " + rICount);
		System.out.println("Average computation time for 100 iterations: " + (avgRITime/100)/Math.pow(10,6) + "ms\n");

		System.out.println("Greedy method had the best calculation time a total of: " + gTCount);
		System.out.println("Greedy method had the best result a total of: " + gCount);
		System.out.println("Average computation time for 100 iterations: " + (avgGTime/100)/Math.pow(10,6) + "ms\n");

	}
//end of class
}