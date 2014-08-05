package behavioral.visitor.eg3;

/**
 * Discussion. We would like to declare a function like: void process( virtual Base object1, virtual Base object2 ) 
 * that does the right thing based on the type of 2 objects that come from a single inheritance hierarchy. 
 * The only problem is that the keyword "virtual" may not be used to request dynamic binding for an object being passed as an argument. 
 * Java will only "discriminate" the type of an object being messaged, not the type of an object being passed.
 * So in order for the type of 2 objects to be discriminated, each object must be the receiver of a virtual function call. 
 * Here, when process1() is called on the first object, its type becomes "known" at runtime, 
 * but the type of the second is still UNknown. 
 * process2() is then called on the second object, and the identity (and type) of the first object is passed as an argument. 
 * Flow of control has now been vectored to the spot where the type (and identity) of both objects are known.
 */
public class VisitorSingle {

	interface Base {
		void process1( Base secondObject );
		void process2(  A   firstObject  );
		void process2(  B   firstObject  );
		void process2(  C   firstObject  );
	}

	static class A implements Base {
		public void process1( Base second ) { second.process2( this ); }
		public void process2( A first ) {
			System.out.println( "first is A, second is A" ); }
		public void process2( B first ) {
			System.out.println( "first is B, second is A" ); }
		public void process2( C first ) {
			System.out.println( "first is C, second is A" ); }
	}

	static class B implements Base {
		public void process1( Base second ) { second.process2( this ); }
		public void process2( A first ) {
			System.out.println( "first is A, second is B" ); }
		public void process2( B first ) {
			System.out.println( "first is B, second is B" ); }
		public void process2( C first ) {
			System.out.println( "first is C, second is B" ); }
	}

	static class C implements Base {
		public void process1( Base second ) { second.process2( this ); }
		public void process2( A first ) {
			System.out.println( "first is A, second is C" ); }
		public void process2( B first ) {
			System.out.println( "first is B, second is C" ); }
		public void process2( C first ) {
			System.out.println( "first is C, second is C" ); }
	}

	public static void main( String[] args ) {
		Base array[] = { new A(), new B(), new C() };
		for (int i=0; i < array.length; i++)
			for (int j=0; j < 3; j++)
				array[i].process1( array[j] );
	}
}