package structural.decorator.eg2;

/**
 * 1. Create a "lowest common denominator" that makes classes interchangeable
 * 2. Create a second level base class for optional functionality
 * 3. "Core" class and "Decorator" class declare an "is a" relationship
 * 4. Decorator class "has a" instance of the "lowest common denominator"
 * 5. Decorator class delegates to the "has a" object
 * 6. Create a Decorator derived class for each optional embellishment
 * 7. Decorator derived classes delegate to base class AND add extra stuff
 * 8. Client has the responsibility to compose desired configurations
 */

public class DecoratorDemo {
	public static void main( String[] args ) {
		// 8. Client has the responsibility to compose desired configurations
		Widget aWidget = new BorderDecorator(
				new BorderDecorator(
						new ScrollDecorator(
								new TextField( 80, 24 ))));
		aWidget.draw();
	}
}
//1. "lowest common denominator"
interface Widget {
	void draw();
}

//3. "Core" class with "is a" relationship
class TextField implements Widget {
	private int width, height;
	public TextField( int w, int h ) {
		width  = w;
		height = h;
	}
	public void draw() {
		System.out.println( "TextField: " + width + ", " + height );
	}
}

//2. Second level base class with "isa" relationship
abstract class Decorator implements Widget {
	private Widget wid; // 4. "has a" relationship

	public Decorator( Widget w ) {
		wid = w;
	}

	// 5. Delegation
	public void draw() {
		wid.draw();
	}
}

//6. Optional embellishment
class BorderDecorator extends Decorator {
	public BorderDecorator( Widget w ) {
		super( w );
	}
	public void draw() {
		super.draw(); // 7. Delegate to base class and add extra stuff
		System.out.println("  BorderDecorator");
	}
}

//6. Optional embellishment
class ScrollDecorator extends Decorator {
	public ScrollDecorator( Widget w ) {
		super( w );
	}
	public void draw() {
		super.draw(); // 7. Delegate to base class and add extra stuff
		System.out.println( "  ScrollDecorator" );
	}
}