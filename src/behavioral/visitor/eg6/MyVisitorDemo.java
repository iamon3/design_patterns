package behavioral.visitor.eg6;

import java.util.ArrayList;
import java.util.List;

/**
 * Object structure is already present. 
 * If we want objects perform different operations at different time, it does not make sense to each time add methods in the elements classes.
 * Open close principle needs to be followed. Thus Visitor pattern solve this problem by providing Operation implementation classes. 
 */ 
public class MyVisitorDemo {
	public static void main(String[] args) {		
		
		List<Visitable> visitableElements = new ArrayList<Visitable>();
		visitableElements.add(new Person());
		visitableElements.add(new Hotel());
		visitableElements.add(new Airport());
		
		// 1st it was decided to display objects
		Display display = new Display();
		for(Visitable v : visitableElements){
			v.excecute(display);
		}
		
		// Before the beginning of the monsoon, it was decided to plan monsoon prevention. 
		// So execute that operation.
		MonsoonPlanner monsoonPlanner = new MonsoonPlanner();
		for(Visitable v : visitableElements){
			v.excecute(monsoonPlanner);			
		}

		// After few months it was decided to perform cleaning operation.
		// So execute the operation
		Cleaning cleaning  = new Cleaning();
		for(Visitable v : visitableElements){
			v.excecute(cleaning);
		}

	}
}

interface Visitor{
	// following methods are same as saying visit(Visitable v);
	void performOperationOn(Person person);
	void performOperationOn(Hotel hotel);
	void performOperationOn(Airport airport);
}

//Element on which Visitor want to perform an operation
interface Visitable{
	// Following method is equivalent to saying accept(Visitor v);
	void excecute(Visitor visitorOperation);
}

// Operation 1. Plan for monsoon. e.g. Heavy rain prevention
class MonsoonPlanner implements Visitor{

	@Override
	public void performOperationOn(Person person) {	
		System.out.println("Buy umbrella.");
	}

	@Override
	public void performOperationOn(Hotel hotel) {
		System.out.println("Provide facility for umbrella keeping.");
	}

	@Override
	public void performOperationOn(Airport airport) {
		System.out.println("Take care take off and landing by checking the forecast.");
	}
}

// Operation 2. How cleaning operation needs to be done. 
class Cleaning implements Visitor{

	@Override
	public void performOperationOn(Person person) {	
		System.out.println("Everyday take a bath.");
	}

	@Override
	public void performOperationOn(Hotel hotel) {
		System.out.println("Everyday clean room, vessels etc. Maintain hygene.");
	}

	@Override
	public void performOperationOn(Airport airport) {
		System.out.println("Clean each and every part. Take care of cameras, software.");
	}
} 

// Operation 3. Print internal state
class Display implements Visitor{

	@Override
	public void performOperationOn(Person person) {
		System.out.println("Person is displayed");
	}

	@Override
	public void performOperationOn(Hotel hotel) {
		System.out.println("Hotel is displayed");
	}

	@Override
	public void performOperationOn(Airport airport) {
		System.out.println("Airport is displayed");
	}
	
}

// Object structure. In future we may add more objects which can be Composite (in same hierarchy) or Heterogeneous (completely different)
// Object 1
class Person implements Visitable{

	@Override
	public void excecute(Visitor visitorOperation) {
		visitorOperation.performOperationOn(this);
	}

}

// Object 2
class Hotel implements Visitable{

	@Override
	public void excecute(Visitor visitorOperation) {
		visitorOperation.performOperationOn(this);
	}
}

//Object 3
class Airport implements Visitable{

	@Override
	public void excecute(Visitor visitorOperation) {
		visitorOperation.performOperationOn(this);
	}
}