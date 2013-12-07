package structural.facade;

/**
 * Let’s take an example of Order processing online website. 
 * The client has placed an order without having knowledge of how internal classes are functioning. 
 * Once the order is placed the façade class layer calls 
 * the methods of the subsystems like Inventory for inventory check and 
 * Payment for processing of the payment. 
 * After processing it returns the control to the client class with the confirmation 
 * about the order being processed.
 */
public class Client1 {
	public static void main(String args[]){
		OrderFacade orderFacade = new OrderFacade();
		orderFacade.placeOrder("OR123456");
		System.out.println("Order processing completed");
	}
}

class OrderFacade {
	private Payment pymt = new Payment();
	private Inventory inventry = new Inventory();

	public void placeOrder(String orderId) {
		String step1 = inventry.checkInventory(orderId);
		String step2 = pymt.deductPayment(orderId);
		System.out
		.println("Following steps completed:" + step1 
				+ " & " + step2);
	}
}

class Payment {
	public String deductPayment(String orderID) {
		return "Payment deducted successfully";

	}
}

class Inventory {
	public String checkInventory(String OrderId) {
		return "Inventory checked";
	}
}