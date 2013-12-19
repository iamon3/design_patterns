package behavioral.chainOfResponsibilty.eg1;

class Request {	
	private int m_value;
	private String m_description;

	public Request(String description, int value)
	{
		m_description = description;
		m_value = value;
	}

	public int getValue()
	{
		return m_value;
	}

	public String getDescription()
	{
		return m_description;
	}          
}

abstract class Handler
{
	protected Handler m_successor;
	public void setSuccessor(Handler successor)
	{
		m_successor = successor;
	}

	public abstract void handleRequest(Request request);
}

class ConcreteHandlerOne extends Handler
{
	@Override
	public void handleRequest(Request request)
	{
		if (request.getValue() < 0)
		{           //if request is eligible handle it
			System.out.println("Negative values are handled by ConcreteHandlerOne:");
			System.out.println("\tConcreteHandlerOne.HandleRequest : " + request.getDescription()
						 + request.getValue());
		}
		else
		{
			// Issue will be fixed soon.
			super.handleRequest(request);
		}
	}
 }

class ConcreteHandlerThree extends Handler
{
	@Override
	public void handleRequest(Request request)
	{
		if (request.getValue() >= 0)
		{           //if request is eligible handle it
			System.out.println("Zero values are handled by ConcreteHandlerThree:");
			System.out.println("\tConcreteHandlerThree.HandleRequest : " + request.getDescription()
						 + request.getValue());
		}
        else
		{
        	// Issue will be fixed soon.
			super.handleRequest(request);
		}
	}
}

class ConcreteHandlerTwo extends Handler
{
	@Override
	public void handleRequest(Request request)
	{
		if (request.getValue() > 0)
		{           //if request is eligible handle it
			System.out.println("Positive values are handled by ConcreteHandlerTwo:");
			System.out.println("\tConcreteHandlerTwo.HandleRequest : " + request.getDescription()
						 + request.getValue());
		}
        else
		{
        	// Issue will be fixed soon.
			super.handleRequest(request);
		}
	}
}

public class Main 
{
	public static void main(String[] args) 
	{
		// Setup Chain of Responsibility
		Handler h1 = new ConcreteHandlerOne();
		Handler h2 = new ConcreteHandlerTwo();
		Handler h3 = new ConcreteHandlerThree();
		h1.setSuccessor(h2);
		h2.setSuccessor(h3);

		// Send requests to the chain
		h1.handleRequest(new Request("Negative Value ", -1));
		h1.handleRequest(new Request("Negative Value ",  0));
		h1.handleRequest(new Request("Negative Value ",  1));
		h1.handleRequest(new Request("Negative Value ",  2));
		h1.handleRequest(new Request("Negative Value ", -5));	    
	}
}