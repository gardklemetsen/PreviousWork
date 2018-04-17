public class Iterator<E>
{
	public static <E> boolean hasNext(E current)
	{
		if(current!=null)
			return true;
		else
			return false;
	}
}