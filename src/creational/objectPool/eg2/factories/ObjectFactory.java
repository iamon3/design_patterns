package creational.objectPool.eg2.factories;

/**
 * Represents the mechanism to create 
 * new objects to be used in an object pool.
 * 
 *
 * @param < T > the type of object to create. 
 */
public interface ObjectFactory < T >
{
	/**
	 * Returns a new instance of an object of type T.
	 * 
	 * @return T an new instance of the object of type T
	 */
	public abstract T createNew();
}