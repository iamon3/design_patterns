package creational.factory.classRegistration.withReflection;

public class Product_A extends Product {

	static {
		ProductFactory.getInstance().registerProduct("Product_A",Product_A.class);
	}

	@Override
	public void getCost(){
		System.out.println("Product A price : $5");
	}

}
