import java.util.Scanner;

public class Fibonacci
{
	public static void main(String [] args)
	{
		Scanner input = new Scanner(System.in);

		int z;
		int n;

		System.out.println("Type in the Fibonacci number you want to find:");

		do
		{
			n = input.nextInt();
			z = fibo(n, 0, 1, 2);

			System.out.println("Fibonacci number " + n + " is " + z);

		}
		while(n>=0);

	}

	public static int fibo(int n, int i, int j, int teller)
	{
	 	if(n==teller)
	 	{
	 		if(j>=i)
	 			return j;
	 		else
	 			return i;
		}

		if(n==1)
			return 0;

		if(i<=j)
		{
			i = i+j;
			teller++;
		}
		else
		{
			j = i+j;
			teller++;
		}

		return fibo(n, i, j, teller);

	}

}


