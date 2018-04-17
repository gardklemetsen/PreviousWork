public class Node
{
	int value;
	Node next;

	Node()
	{
		value = 0;
		next = null;
	}

	Node(int value, Node next)
	{
		this.value = value;
		this.next = next;
	}
}