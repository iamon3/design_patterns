package behavioral.observer.eg1;

import java.util.Scanner;

/**
 * 1. Model the "independent" functionality with a "subject" abstraction.
 * 2. Model the "dependent" functionality with "observer" hierarchy.
 * 3. The Subject is coupled only to the Observer base class.
 * 4. Observers register themselves with the Subject.
 * 5. The Subject broadcasts events to all registered Observers.
 * 6. Observers "pull" the information they need from the Subject.
 * 7. Client configures the number and type of Observers.
 */
abstract class Observer { 
	protected Subject subj; 
	public abstract void update();
}

class HexObserver extends Observer { 
	public HexObserver( Subject s ) { 
		subj = s; 
		subj.attach( this ); 
	}

	public void update() { 
		System.out.print( " " + Integer.toHexString( subj.getState() ) ); 
	} 
} // Observers "pull" information 

class OctObserver extends Observer { 
	public OctObserver( Subject s ) {
		subj = s;
		subj.attach( this );
	} 
	public void update() {
		System.out.print( " " + Integer.toOctalString( subj.getState() ) );
	} 
} // Observers "pull" information 

class BinObserver extends Observer { 
	public BinObserver( Subject s ) { 
		subj = s; 
		subj.attach( this ); } // Observers register themselves 
	public void update() { 
		System.out.print( " " + Integer.toBinaryString( subj.getState() ) ); 
	} 
}

class Subject { 
	private Observer[] observers = new Observer[9];
	private int totalObs = 0;
	private int state;
	public void attach( Observer o ) {
		observers[totalObs++] = o;
	}

	public int getState() {
		return state;
	}

	public void setState( int in ) {
		state = in;
		notifyToObservers();
	}

	private void notifyToObservers() {
		for (int i=0; i < totalObs; i++) {
			observers[i].update();
		}
	}
}

public class ObserverDemo {
	public static void main( String[] args ) {
		Subject sub = new Subject();
		// Client configures the number and type of Observers
		new HexObserver( sub );
		new OctObserver( sub );
		new BinObserver( sub );
		Scanner scan = new Scanner(System.in);
		try{
			while (true) {
				System.out.print( "\nEnter a number: " );
				sub.setState( scan.nextInt() );
			}
		}
		catch (Exception e)
		{	
			e.printStackTrace();
		}
		finally{
			scan.close();
		}
	}
}