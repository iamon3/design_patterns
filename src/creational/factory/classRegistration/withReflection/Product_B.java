package creational.factory.classRegistration.withReflection;

public class Product_B extends Product {

	static {
		ProductFactory.getInstance().registerProduct("Product_B",Product_B.class);
	}
	
	@Override
	public void getCost() {
		System.out.println("Product B price : $10");
	}

}
