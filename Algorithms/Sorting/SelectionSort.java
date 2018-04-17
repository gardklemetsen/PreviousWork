public class SelectionSort{

	public static void main(String[] args){

		int[] list = {10, 2, 3, 2, -1, -9, -2, 1000, 9, -2};

		sort(list);

		for(int i = 0; i<list.length; i++){
			System.out.print(list[i] + " ");
		}
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
}