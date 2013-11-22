package structural.bridge.eg4oodesign;

import java.io.File;


public class PersistenceFrameworkDriver {

}

/**
 * Persistence Interface 
 * Abstraction Interface
 */
interface Persistence {

	/**
	 * @param object
	 * @return returns objectID
	 */
	public String persist(Object object);

	/**
	 * 
	 * @param objectId
	 * @return persisted Object
	 */
	public Object findById(String objectId);

	/**
	 * 
	 * @param id
	 */
	public void deleteById(String id);


}


/**
 * Abstraction Imp
 */
class PersistenceImp implements Persistence {

	private PersistenceImplementor implementor = null;

	public PersistenceImp(PersistenceImplementor imp) {

		this.implementor = imp;

	}

	@Override
	public void deleteById(String id) {

		implementor.deleteObject(Long.parseLong(id));

	}

	@Override
	public Object findById(String objectId) {

		return implementor.getObject(Long.parseLong(objectId));
	}



	@Override
	public String persist(Object object) {

		return Long.toString(implementor.saveObject(object));


	}

}

/**
 * Implementor Interface 
 */
interface PersistenceImplementor {

	public long saveObject(Object object);

	public void deleteObject(long objectId);

	public Object getObject(long objectId);

}

/**
 * Concrete Implementor 
 *
 */
class FileSystemPersistenceImplementor implements PersistenceImplementor{

	@Override
	public void deleteObject(long objectId) {

		File f = new File("/persistence/"+Long.toString(objectId));

		f.delete();

		return;
	}

	@Override
	public Object getObject(long objectId) {

		File f = new File("/persistence/"+Long.toString(objectId));

		return readObjectFromFile(f);


	}

	private Object readObjectFromFile(File f) {

		// open file 
		// and load object 
		//return the object
		return null;
	}

	@Override
	public long saveObject(Object object) {

		long fileId = System.currentTimeMillis();

		// open file 
		File f = new File("/persistence/"+Long.toString(fileId));

		// write file to Stream 

		writeObjectToFile(f,object);


		return fileId;

	}

	private void writeObjectToFile(File f, Object object) {

		// serialize object and write it to file

	}

}

class DabatasePersistenceImplementor implements PersistenceImplementor{

	public DabatasePersistenceImplementor() {

		// load database driver


	}

	@Override
	public void deleteObject(long objectId) {

		// open database connection
		// remove record

	}

	@Override
	public Object getObject(long objectId) {

		// open database connection 
		// read records
		// create object from record 
		return null;
	}

	@Override
	public long saveObject(Object object) {

		// open database connection 
		// create records for fields inside the object

		return 0;
	}

}