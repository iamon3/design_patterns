package structural.decorator.eg1oodesign;

public class GUIDriver {

	public static void main(String[] args) {


		// create a new window 

		Window window = new SimpleWindow();

		window.renderWindow();

		// at some point later 
		// maybe text size becomes larger than the window 
		// thus the scrolling behavior must be added 

		// decorate old window 
		window = new ScrollableWindow(window);

		//  now window object 
		// has additional behavior / state 

		window.renderWindow();

	}
}

/**
 * Window implementation 
 * 
 * Concrete implementation
 */
class SimpleWindow implements Window {

	@Override
	public void renderWindow() {
		// implementation of rendering details

	}
}

/**
 * Window Interface 
 * 
 * Component window
 */
interface Window {

	public void renderWindow();

}

/**
 * Concrete Decorator with extended state 
 * 
 *  Scrollable window creates a window that is scrollable
 * 
 *
 */
class ScrollableWindow extends DecoratedWindow{

	/**
	 * Additional State 
	 */
	private Object scrollBarObjectRepresentation = null;

	public ScrollableWindow(Window windowRefernce) {

		super(windowRefernce);
	}

	@Override
	public void renderWindow() {

		// render scroll bar 
		renderScrollBarObject();

		// render decorated window
		super.renderWindow();
	}

	private void renderScrollBarObject() {

		// prepare scroll bar 
		scrollBarObjectRepresentation = new  Object();


		// render scrollbar 

	}	
}

/**
 *
 */
class DecoratedWindow implements Window{

	/**
	 * private reference to the window being decorated 
	 */
	private Window privateWindowRefernce = null;

	public DecoratedWindow( Window windowRefernce) {

		this.privateWindowRefernce = windowRefernce;
	}

	@Override
	public void renderWindow() {

		privateWindowRefernce.renderWindow();

	}
}