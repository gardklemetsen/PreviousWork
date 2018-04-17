public class SingledLinkedList
{
	private int value;
	private Node head;
	Iterator it = new Iterator();

	SingledLinkedList()
	{
		value = 0;
		head = null;
	}

	SingledLinkedList(int value, Node head)
	{
		this.value = value;
		this.head = head;
	}

	public void addFirst(int value)
	{
		Node aNode = new Node(value, null);

		aNode.next = head;
		head = aNode;
	}

	public void removeFirst()
	{
		if(Iterator.<Node>hasNext(head))
		{
			Node current = head.next;
			head = current;
		}
	}

	public void display()
	{
		Node current = head;

		while(it.<Node>hasNext(current))
		{
			System.out.println(current.value);
			current = current.next;
		}
	}
}