public class BinarySearch
{
	public static void main(String [] args)
	{
		int[] array = {0,1,2,3,4,5,6,7,8,9};
		int find = 13;
		int arrayIndex = 0;

		int index = search(array, find, array.length, arrayIndex);

		System.out.println("The number " + find + " was located in index " + index);
	}

	public static int search(int[] a, int find, int n, int m)
	{
		int bin = (n+m)/2;

		if(m-n<=0)
			return -1;

		if(find==a[bin])
			return bin;

		else if(find>a[bin])
		{
				return search(a, find, n, bin);

		}
		else
			return search(a, find, bin, m);

	}

}

//Summen av en rekke tall fra n - 1 = n(n+1)/2
//T(n)=n(n+1)/2

