package creational.factory.parameterizedMethod;

/**
 * Either ProductFactory should be singleton
 * or it should expose static method which handles a instance creation
 */
public class ProductFactory {

	/**
	 * Note that method is returning Generic type Product and not the specific implementation of the product. 
	 */
	public static Product createProduct(String productType){
		Product p = null;
		if(productType == "product_A"){
			p = new Product_A();
		}
		else if(productType == "product_B"){
			p = new Product_B();
		}
		return p;
	}
}
