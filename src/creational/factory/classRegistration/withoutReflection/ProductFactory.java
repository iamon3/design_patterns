package creational.factory.classRegistration.withoutReflection;

import java.util.HashMap;
import java.util.Map;

class ProductFactory
{
	private static ProductFactory pf = new ProductFactory();
	
	private Map<String, Product> registeredProducts = new HashMap<String, Product>();

	private ProductFactory(){
		
	}
	
	public static ProductFactory getInstance(){
	  if(null == pf){
		  pf = new ProductFactory();
	  }
	  return pf;
	}
	
	public void registerProduct (String productID, Product product)
	{
		registeredProducts.put(productID, product);
	}

	public Product createProduct(String productID)
	{
		return ((Product)registeredProducts.get(productID)).createProduct();
	}
}