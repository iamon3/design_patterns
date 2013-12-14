package structural.flyweight.eg3;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Vector;

/**
 * 8 shared HandlerThreads in a ThreadPool
 * The ColorBox class has now become a Flyweight: the color changing and painting capability remains "intrinsic",
 * and the threaded behavior has been made "extrinsic".
 * The ThreadPool class plays the role of the Factory. 
 * As ColorBox objects are created, they register themselves with the ThreadPool object. 
 * The latter launches 8 "handler" threads. When each thread is swapped into the CPU, 
 * it selects a random Flyweight from the ThreadPool's cache, and asks the object to changeColor(). 
 */
class ColorBox2 extends Canvas {
	private Color  curColor = getColor();
	private static Color[]  colors = { Color.black, Color.blue, Color.cyan,
		Color.darkGray, Color.gray, Color.green, Color.lightGray, Color.red,
		Color.magenta, Color.orange, Color.pink, Color.white, Color.yellow };

	public ColorBox2( ThreadPool2 tp ) {
		tp.register( this );
	}
	private static Color getColor() {
		return colors[ (int)(Math.random() * 1000) % colors.length ];
	}
	public void changeColor() {
		curColor = getColor();
		repaint();
	}
	public void paint( Graphics g ) {
		g.setColor( curColor );
		g.fillRect( 0, 0, getWidth(), getHeight() );
	}  
}

class ThreadPool2 {
	private final int NUM_THREADS = 8;
	private Vector cboxes = new Vector();
	private int    pause;

	class HandlerThread extends Thread {
		public void run() {
			while (true) {
				((ColorBox2) cboxes.elementAt(
						(int)(Math.random()*1000) % cboxes.size() )).changeColor();
				try { Thread.sleep( pause ); } catch( InterruptedException e ) { }
			}  }  }

	public ThreadPool2( int p ) {
		pause = p;
	}
	public void register( ColorBox2 r ) {
		cboxes.addElement( r );
	}
	public void start() {
		for (int i=0; i < NUM_THREADS; i++) new HandlerThread().start();
	}  
}

public class ColorBoxes2 {
	public static void main( String[] args ) {
		int size = 8, pause = 10;
		if (args.length > 0) size  = Integer.parseInt( args[0] );
		if (args.length > 1) pause = Integer.parseInt( args[1] );

		ThreadPool2 tp = new ThreadPool2( pause );

		Frame f = new Frame/**Close*/( "ColorBoxes2 - 8 shared HandlerThreads" );
		f.setLayout( new GridLayout( size, size ) );
		for (int i=0; i < size*size; i++) 
			f.add( new ColorBox2( tp ) );
		f.setSize( 500, 400 );
		f.setVisible( true );
		tp.start();
	}  
}