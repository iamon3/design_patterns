package behavioral.commandOrProducerConsumers.eg1;

import java.util.ArrayList;
import java.util.List;

interface Order {
	public abstract void execute ( );
}

// Receiver class.
class StockTrade {
	public void buy() {
		System.out.println("You want to buy stocks");
	}
	public void sell() {
		System.out.println("You want to sell stocks ");
	}
}

// Invoker.
class Agent {
	private List<Order> ordersQueue = new ArrayList<Order>();

	public Agent() {
	}

	void placeOrder(Order order) {
		// Add order in the queue
		ordersQueue.add(order);
		
		// Execution of order depends on the business requirement.
		// Here we are executing it right away, as soon as order is added, 
		// but you can differ the execution
		ordersQueue.get(0).execute();
		ordersQueue.remove(0);
	}    
}

//ConcreteCommand Class.
class BuyStockOrder implements Order {
	private StockTrade stock;
	public BuyStockOrder ( StockTrade st) {
		stock = st;
	}
	public void execute( ) {
		stock . buy( );
	}
}

//ConcreteCommand Class.
class SellStockOrder implements Order {
	private StockTrade stock;
	public SellStockOrder ( StockTrade st) {
		stock = st;
	}
	public void execute( ) {
		stock . sell( );
	}
}

// Client
public class Client {
	public static void main(String[] args) {
		StockTrade stock = new StockTrade();
		BuyStockOrder bsc = new BuyStockOrder (stock);
		SellStockOrder ssc = new SellStockOrder (stock);
		Agent agent = new Agent();

		agent.placeOrder(bsc); // Buy Shares
		agent.placeOrder(ssc); // Sell Shares
	}
}