package behavioral.visitor.eg4;

import java.util.ArrayList;
import java.util.List;

/**
 * Visitor-Composite :
 * 
 * VisitorComposite1 is a basic Composite implementation with one recursive traversal method. 
 * VisitorComposite2 is a non-Visitor implementation that models "parsing" the hierarchical Composite with the collect() recursive traversal method. 
 * VisitorComposite3 is a Visitor implementation.
 * Highlights. VisitorComposite2 changes interface Component into an abstract class. 
 * It requires protected static members. VisitorComposite3 is "open for extension, but closed for modification". 
 * The interface Component remains an interface. 
 * Now that "collect" is an object, many of them can be created and can operate simultaneously 
 * (the previous static attributes would have required significant extra effort to provide this functionality). 
 * Drawback: the public interface of Leaf and Composite had to be extended.
 */
interface Component { void traverse(); }

class Leaf implements Component {
	private int number;
	public Leaf( int num ) { number = num; }
	public void traverse() { System.out.print( number + " " ); }
}

class Composite implements Component {
	private static char next = 'a';
	private List children = new ArrayList();
	private char letter = next++;

	public void add( Component c ) { children.add( c ); }
	public void traverse() {
		System.out.print( letter + " " );
		for (int i=0; i < children.size(); i++)
			((Component)children.get(i)).traverse();
	} }

public class VisitorComposite1 {
	public static void main( String[] args ) {
		Composite[] containers = new Composite[3];

		for (int i=0; i < containers.length; i++) {
			containers[i] = new Composite();
			for (int j=1; j < 4; j++)
				containers[i].add( new Leaf( i * containers.length + j ) );
		}

		for (int i=1; i < containers.length; i++)
			containers[0].add( containers[i] );

		containers[0].traverse();
		System.out.println();
	} 
}