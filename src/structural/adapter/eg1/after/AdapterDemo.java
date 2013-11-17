package structural.adapter.eg1.after;

class LegacyLine
{
	public void draw(int x1, int y1, int x2, int y2)
	{
		System.out.println("line from (" + x1 + ',' + y1 + ") to (" + x2 + ',' 
				+ y2 + ')');
	}
}

class LegacyRectangle
{
	public void draw(int x, int y, int w, int h)
	{
		System.out.println("rectangle at (" + x + ',' + y + ") with width " + w
				+ " and height " + h);
	}
}

// Inclusion of Adapters
interface Shape
{
	void draw(int x1, int y1, int x2, int y2);
}

class Line implements Shape
{
	private LegacyLine adaptee = new LegacyLine();
	public void draw(int x1, int y1, int x2, int y2)
	{
		adaptee.draw(x1, y1, x2, y2);
	}
}

class Rectangle implements Shape
{
	private LegacyRectangle adaptee = new LegacyRectangle();
	public void draw(int x1, int y1, int x2, int y2)
	{
		adaptee.draw(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1),
				Math.abs(y2 - y1));
	}
}

/*
 * The Adapter's "extra level of indirection" takes care of mapping a user-friendly common interface 
 * to legacy-specific peculiar interfaces. Notice that user/client does not need to recover the type of each shape 
 * and does not need to manually supply the correct arguments. 
 */
public class AdapterDemo
{
	public static void main(String[] args)
	{
		Shape[] shapes = 
			{
				new Line(), new Rectangle()
			};
		// A begin and end point from a graphical editor
		int x1 = 10, y1 = 20;
		int x2 = 30, y2 = 60;
		for (int i = 0; i < shapes.length; ++i)
			shapes[i].draw(x1, y1, x2, y2);
	}
}