public class Demo
{
	public static void main(String [] args)
	{
		SingledLinkedList myList = new SingledLinkedList();

		myList.addFirst(2);
		myList.addFirst(2);

		myList.removeFirst();

		myList.display();
	}
}