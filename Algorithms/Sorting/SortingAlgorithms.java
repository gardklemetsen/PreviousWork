public class SortingAlgorithms{

	public static void main(String [] args){

		int []table = {2, 9, -2, -2, 10, 12};

		table = bubbleSort(table);

		for(int i= 0; i<table.length; i++){
			System.out.println(table[i]);
		}

	}

	public static int [] insertionSort(int [] table){

		int temp = 0;
		int i;
		int k = 0;
		for(i = 1; i<table.length; i++){
			k=i;
			while(k>=1 && table[k]<table[k-1]){
				temp = table[k];
				table[k] = table[k-1];
				table[k-1] = temp;
				k--;
			}
		}

		return table;
	}

	public static void sort(int [] list){

		int currentIndex, currentMin, i, k;

		for(i = 0; i<list.length-1; i++){
			currentIndex = i;
			currentMin = list[i];

			for(k = i+1; k<list.length; k++){

				if(list[k]<currentMin){
					currentIndex = k;
					currentMin = list[k];
				}

			}
			list[currentIndex] = list[i];
			list[i]=currentMin;

		}
	}

	public static int [] bubbleSort(int [] table){

		int temp = 0;
		int k;
		int i;

		for(i=1; i<table.length; i++){
			for(k=0; k<i; k++){
				if(table[i]<table[k]){
					temp = table[i];
					table[i] = table[k];
					table[k] = temp;
				}
			}
		}

		return table;
	}


}