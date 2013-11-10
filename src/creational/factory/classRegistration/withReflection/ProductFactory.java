package creational.factory.classRegistration.withReflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

class ProductFactory
{
	private static ProductFactory pf = new ProductFactory();
	
	private Map<String, Class> registeredProducts = new HashMap<String, Class>();

	private ProductFactory(){
		
	}
	
	public static ProductFactory getInstance(){
	  if(null == pf){
		  pf = new ProductFactory();
	  }
	  return pf;
	}
	
	public void registerProduct (String productID, Class productClass)
	{
		registeredProducts.put(productID, productClass);
	}

	public Product createProduct(String productID)
	{
		Product p = null;
		Class productClass = (Class)registeredProducts.get(productID);
		try {
		Constructor productConstructor = productClass.getDeclaredConstructor(new Class[] { String.class });
			p= (Product)productConstructor.newInstance(new Object[] { });
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return p;
	}
}