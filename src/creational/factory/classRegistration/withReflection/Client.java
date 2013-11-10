package creational.factory.classRegistration.withReflection;

public class Client {

	static
	{
		// Load classes before ProductFactory class is loaded, so that, reference to product will not be null. 
		// When these product classes are loaded, those will be registered with the ProductFactory in their respective static blocks.
		try
		{
			Class.forName("Product_A");
			Class.forName("Product_B");
		}
		catch (ClassNotFoundException any)
		{
			any.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		
		// Clients deals with interface type only i.e. Product and not the specific 
		// implementation of a product
		Product product1 = ProductFactory.getInstance().createProduct("Product_A");
		Product product2 = ProductFactory.getInstance().createProduct("Product_B");
		product1.getCost();
		product2.getCost();
	}
}
