package behavioral.observer.eg2;

/**
 *  SensorSystem is the "subject". 
 *  Lighting, Gates, and Surveillance are the "views". 
 *  The subject is only coupled to the "abstraction" of AlarmListener.
 *  An object's class defines how the object is implemented. 
 *  In contrast, an object's type only refers to its interface. 
 *  Class inheritance defines an object's implementation in terms of another object's implementation. 
 *  Type inheritance describes when an object can be used in place of another.
 */

interface AlarmListener { public void alarm(); }

class SensorSystem {
	private java.util.Vector listeners = new java.util.Vector();

	public void register( AlarmListener al ) { listeners.addElement( al ); }
	public void soundTheAlarm() {
		for (java.util.Enumeration e=listeners.elements(); e.hasMoreElements(); )
			((AlarmListener)e.nextElement()).alarm();
	}  
}

class Lighting implements AlarmListener {
	public void alarm() { System.out.println( "lights up" ); }
}

class Gates implements AlarmListener {
	public void alarm() { System.out.println( "gates close" ); }
}

class CheckList {
	public void byTheNumbers() {  // Template Method design pattern
		localize();
		isolate();
		identify(); }
	protected void localize() {
		System.out.println( "   establish a perimeter" ); }
	protected void isolate()  {
		System.out.println( "   isolate the grid" ); }
	protected void identify() {
		System.out.println( "   identify the source" ); }
}

// class inherit.  // type inheritance
class Surveillance extends CheckList implements AlarmListener {
	public void alarm() {
		System.out.println( "Surveillance - by the numbers:" );
		byTheNumbers(); }
	protected void isolate() { System.out.println( "   train the cameras" ); }
}

public class ClassVersusInterface {
	public static void main( String[] args ) {
		SensorSystem ss = new SensorSystem();
		ss.register( new Gates()        );
		ss.register( new Lighting()     );
		ss.register( new Surveillance() );
		ss.soundTheAlarm();
	}  
}