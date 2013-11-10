package creational.factory.classRegistration.withoutReflection;

public class Product_A extends Product {

	static {
		ProductFactory.getInstance().registerProduct("Product_A",new Product_A());
	}

	@Override
	public void getCost(){
		System.out.println("Product A price : $5");
	}

	@Override
	public Product createProduct() {
		return new Product_A();
	}

}
