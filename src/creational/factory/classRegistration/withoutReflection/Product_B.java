package creational.factory.classRegistration.withoutReflection;

public class Product_B extends Product {

	static {
		ProductFactory.getInstance().registerProduct("Product_B",new Product_B());
	}
	
	@Override
	public void getCost() {
		System.out.println("Product B price : $10");
	}

	@Override
	public Product createProduct() {
		return new Product_B();	}

}
