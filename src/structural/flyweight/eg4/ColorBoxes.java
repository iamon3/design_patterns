package structural.flyweight.eg4;

import java.awt.*;

/**
 * heavyweight ColorBoxes  ==>  ColorBox Flyweights and a Factory 
 * (1 thread per ColorBox)         of pooled HandlerThreads
 *  Discussion. Creating a thread for each ColorBox is a much more straight- forward approach, 
 *  but it doesn't scale when dozens of ColorBoxes are created. 
 *  Sharing a "pool" of threads across the collection of ColorBoxes requires more thought to set-up, 
 *  but does not saturate "system resources" like the former approach does.
 *  In the implementation below, each ColorBox "wraps" itself with a Thread object. 
 *  The Thread object provides all the "threading functionality magic" and simply calls ColorBox's run() method 
 *  when it is promoted from the "ready" state to the "running" state. 
 *  When each Thread/ColorBox is swapped into the CPU, it causes the ColorBox part of itself to change its color 
 *  and then graciously gives up the CPU [by calling sleep()] so that other Threads may run.
 *  In the ThreadPool implementation, after the ColorBoxes are set-up, 
 *  the ThreadPool creates and starts 8 HandlerThreads. 
 *  When a HandlerThread is swapped into the CPU, it gets a random ColorBox object from ThreadPool's private Vector, 
 *  tells the ColorBox to change its color, and graciously returns to the "asleep" state.
 *  "You can typically make your threaded applications run FASTER by inserting calls to sleep() (with reasonably long durations)." 
 *  This definitely contributes to the perception that Threads are a "black art". 
 *  Not enough calls: monopolization of the CPU. 
 *  Not enough duration: time expiration interrupt events interrupt the running thread before it can finish useful work. 
 */
class ColorBox extends Canvas implements Runnable {
   private int    pause;
   private Color  curColor = getColor();
   private static Color[]  colors = { Color.black, Color.blue, Color.cyan,
      Color.darkGray, Color.gray, Color.green, Color.lightGray, Color.red,
      Color.magenta, Color.orange, Color.pink, Color.white, Color.yellow };

   public ColorBox( int p ) {
      pause = p;
      new Thread( this ).start();
   }
   private static Color getColor() {
      return colors[ (int)(Math.random() * 1000) % colors.length ];
   }
   public void run() {
      while (true) {
         curColor = getColor();
         repaint();
         try { Thread.sleep( pause ); } catch( InterruptedException e ) { }
   }  }
   public void paint( Graphics g ) {
      g.setColor( curColor );
      g.fillRect( 0, 0, getWidth(), getHeight() );
}  }

public class ColorBoxes {
   public static void main( String[] args ) {
      int size = 8, pause = 10;
      if (args.length > 0) size  = Integer.parseInt( args[0] );
      if (args.length > 1) pause = Integer.parseInt( args[1] );
      Frame f = new Frame/**Close*/( "ColorBoxes - 1 thread per ColorBox" );
      f.setLayout( new GridLayout( size, size ) );
      for (int i=0; i < size*size; i++) f.add( new ColorBox( pause ) );
      f.setSize( 500, 400 );
      f.setVisible( true );
}  }