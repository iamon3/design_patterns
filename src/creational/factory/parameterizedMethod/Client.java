package creational.factory.parameterizedMethod;

public class Client {

	public static void main(String[] args) {
		
		// Clients deals with interface type only i.e. Product and not the specific 
		// implementation of a product
		Product product1 = ProductFactory.createProduct("product_A");
		Product product2 = ProductFactory.createProduct("product_B");
		product1.getCost();
		product2.getCost();
	}
}
