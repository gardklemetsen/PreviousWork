using System;
using System.Collections;
using System.Collections.Generic;

public static class GraphColoringProblem{
	
	//Global object type Random
	static Random rnd = new Random();
	
	public static void Main(String [] args){
		int counter = 0;
		
		if(args.Length==1){
			//Initial solution
			int [,] parents = Randomparents(10, Int32.Parse(args[0]));
			int [,] graph = RandomGraph(Int32.Parse(args[0]));
			int [,] children;
			int [,] generation;
			
			while(counter<5000){
				children = TwoPointCrossover(parents);
				Mutation(children);
				generation = Concat(parents, children);
				parents = Elimination(generation, graph);
				Console.WriteLine(Calculation(parents, 0, graph));
				counter++;
			}
		}
		
	}
	
	public static int [,] Randomparents(int solutions, int nodes){
		int [,] parents = new int[solutions, nodes];
		
		for(int i=0; i<solutions-1; i++){
			for(int k=0; k<nodes-1; k++){
				parents[i, k] = rnd.Next(0, 3);
			}
		}
		
		return parents;
	}
	
	public static int [,] RandomGraph(int nodes){
		int [,] graph = new int[nodes, nodes];
		
		for(int i=0; i<nodes; i++){
			for(int k=i; k<nodes; k++){
				if(i==k){
					graph[i, k] = 0;
					graph[k, i] = 0;
				}
				else if(rnd.Next(0,50) == 0){
					graph[i, k] = 1;
					graph[k, i] = 1;
				}
				else{
					graph[i, k] = 0;
					graph[k, i] = 0;
				}
				
			}
		}
		
		return graph;	
	}
	
	public static int [,] TwoPointCrossover(int [,] parents){
		int [,] children = new int[parents.GetLength(0), parents.GetLength(1)];
		int start = rnd.Next(0, parents.GetLength(1));
		int stop = rnd.Next(start, parents.GetLength(1));
		int i;
		
		for(i=0; i<children.GetLength(1); i++){
			if(i<=start || i>=stop){
				children[0, i] = parents[0, i];
				children[1, i] = parents[1, i];
			}
			else{
				children[0, i] = parents[1, i];
				children[1, i] = parents[0, i];
			}
		}
		
		return children;
	}
	
	public static void Mutation(int [,] children){
		int probability = 25;
		int random = rnd.Next(0, 100);
		int i, k;
		
		for(i=0; i<children.GetLength(0); i++){
			for(k=0; k<children.GetLength(1); k++){
				random = rnd.Next(0, 100);
					
				if(random < probability){	
					children[i, k] = rnd.Next(0, 3);
				}
			}
		}	
	}
	
	public static int [,] Concat(int [,] parents, int [,] children){
		int [,] generation = new int[parents.GetLength(0) + children.GetLength(0), parents.GetLength(1)];
		int i, k;
		
		for(i=0; i<generation.GetLength(0)/2; i++){
			for(k=0; k<generation.GetLength(1); k++){
				generation[i, k] = parents[i, k];
				generation[i+parents.GetLength(0), k] = children[i, k];
			}
		}
		return generation;
    }
	
	public static int [,] Elimination(int [,] generation, int [,] graph){
		int [] fitness = new int[generation.GetLength(0)];
		int [] sortedFitness = new int[generation.GetLength(0)];
		int [,] survivors = new int[generation.GetLength(0)/2, generation.GetLength(1)];
		int i = 0;
		int k = 0;
		
		for(i=0; i<generation.GetLength(0); i++){
			fitness[i] = Calculation(generation, i, graph);
			sortedFitness[i] = i;
		}
		
		for(i=1; i<sortedFitness.GetLength(0); i++){
			int j = i;
			
			while(j>0){
				if(fitness[sortedFitness[j - 1]]>fitness[sortedFitness[j]]){
					int temp = sortedFitness[j - 1];
					sortedFitness[j - 1] = sortedFitness[j];
					sortedFitness[j] = temp;
					j--;
				}
				else
					break;
			}
		}
		
		for(i=0; i<generation.GetLength(0)/2; i++){
			for(k=0; k<generation.GetLength(1); k++){
				survivors[i, k] = generation[sortedFitness[i], k];
			}
		}
		
		return survivors;
	}
	
	public static int Calculation(int [,] parents, int row, int [,] graph){
		int fitness = 0;
		int i, k;
		
		int [] solution = new int[parents.GetLength(1)];
		
		for(i=0; i<solution.GetLength(0); i++){
			solution[i] = parents[row, i];
		}
		
		for(i=0; i<graph.GetLength(0); i++){
			for(k=i; k<graph.GetLength(1); k++){
				if(graph[i, k] != 0 && solution[i] == solution[k]){
					fitness += 1;
				}
			}
		}
		
		return fitness;
	}
}