import java.io.*;
import java.util.Scanner;

public class Palindrome
{
	public static void main(String [] args)
	{
		Scanner input = new Scanner(System.in);

		String s = new String(input.nextLine());
		boolean a = palindrome(s);

		if(a)
			System.out.println(s + " is a palindrome!");

		else
			System.out.println(s + " is not a palindrome!");
	}

	public static boolean palindrome(String a)
	{
		if(a.length() == 0 || a.length() == 1)
			return true;

		if(a.charAt(0) == a.charAt(a.length()-1))

			// check for first and last char of String:
			// if they are same then do the same thing for a substring
			// with first and last char removed. and carry on this
			// until you string completes or condition fails

			return palindrome(a.substring(1, a.length()-1));
			//agnes i senga
			//gnes i seng osv..

		return false;

	}
}
