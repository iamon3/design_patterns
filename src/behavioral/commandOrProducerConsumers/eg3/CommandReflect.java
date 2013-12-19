package behavioral.commandOrProducerConsumers.eg3;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Motivation: "Sometimes it is necessary to issue requests to objects 
 * without knowing anything about the operation being requested or the receiver of the request." 
 * The Command design pattern suggests encapsulating ("wrapping") in an object all (or some) of the following: 
 * an object, a method name, and some arguments. 
 * Java does not support "pointers to methods", but its reflection capability will do nicely. 
 * The "command" is a black box to the "client". 
 * All the client does is call "execute()" on the opaque object. 
 */
public class CommandReflect {
	private int state;

	public CommandReflect( int in ) {
		state = in;
	}

	public int addOne( Integer one ) {
		return state + one.intValue();
	}

	public int addTwo( Integer one, Integer two ) {
		return state + one.intValue() + two.intValue();
	}

	static public class Command {
		private Object   receiver;               // the "encapsulated" object
		private Method   action;                 // the "pre-registered" request
		private Object[] args;                   // the "pre-registered" arg list

		public Command( Object obj, String methodName, Object[] arguments ) {
			receiver = obj;
			args = arguments;
			Class cls = obj.getClass();           // get the object's "Class"
			Class[] argTypes = new Class[args.length];
			for (int i=0; i < args.length; i++)   // get the "Class" for each
				argTypes[i] = args[i].getClass();  //    supplied argument
			// get the "Method" data structure with the correct name and signature
			try {      action = cls.getMethod( methodName, argTypes );      }
			catch( NoSuchMethodException e ) { System.out.println( e ); }
		}

		public Object execute() {
			// in C++, you do something like --- return receiver->action( args ); 
			try {     return action.invoke( receiver, args );     }
			catch( IllegalAccessException e    ) { System.out.println( e ); }
			catch( InvocationTargetException e ) { System.out.println( e ); }
			return null;
		}  
	}

	public static void main( String[] args ) {
		CommandReflect[] objs = { new CommandReflect(1), new CommandReflect(2) };
		System.out.print( "Normal call results: " );
		System.out.print( objs[0].addOne( new Integer(3) ) + " " );
		System.out.print( objs[1].addTwo( new Integer(4),
				new Integer(5) ) + " " );
		Command[] cmds = {
				new Command( objs[0], "addOne", new Integer[] { new Integer(3) } ),
				new Command( objs[1], "addTwo", new Integer[] { new Integer(4),
						new Integer(5) } ) };
		System.out.print( "\nReflection results:  " );
		for (int i=0; i < cmds.length; i++)
			System.out.print( cmds[i].execute() + " " );
		System.out.println();
	}  
}

/**
 * Expected Output
 * Normal call results: 4 11     // 1 + 3 = 4     // 2 + 4 + 5 = 11
 * Reflection results:  4 11
 */