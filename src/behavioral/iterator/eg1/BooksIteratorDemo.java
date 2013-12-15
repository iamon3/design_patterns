package behavioral.iterator.eg1;

interface IIterator
{
	public boolean hasNext();
	public Object next();
}

interface IContainer
{
	public IIterator createIterator();
}

class BooksCollection implements IContainer
{
	private String m_titles[] = {"Design Patterns","1","2","3","4"};

	public IIterator createIterator()
	{
		BookIterator result = new BookIterator();
		return result;
	}


	private class BookIterator implements IIterator
	{
		private int m_position;

		public boolean hasNext()
		{
			if (m_position < m_titles.length)
				return true;
			else
				return false;
		}
		public Object next()
		{
			if (this.hasNext())
				return m_titles[m_position++];
			else
				return null;
		}
	}
}

public class BooksIteratorDemo {	
	public static void main(String[] args){		
		// External Iterator
		/**
		 * // using iterators for a collection of String objects:
			// using in a for loop
			for (Iterator it = options.iterator(); it.hasNext(); ) {
   				String name = (String)it.next();
   				System.out.println(name);
			}

			// using in while loop
				Iterator name = options.iterator();
    			while (name.hasNext() ){
      			System.out.println(name.next() );
    		}

			// using in a for-each loop (syntax available from java 1.5 and above)
    		for (Object item : options)
        		System.out.println(((String)item));
		 */
		
		// Internal Iterator
		/**
		 * collection do: [:each | each doSomething] (Smalltalk) 
		 */
	}	
}
