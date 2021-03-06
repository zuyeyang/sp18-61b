/** Performs some basic linked list tests. */
public class LinkedListDequeTest {
	
	/* Utility method for printing out empty checks. */
	public static boolean checkEmpty(boolean expected, boolean actual) {
		if (expected != actual) {
			System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}
	/* Utility method for printing out empty checks. */
	public static boolean checkSize(int expected, int actual) {
		if (expected != actual) {
			System.out.println("size() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}
	public static boolean checkget(int expected, int actual) {
		if (expected != actual) {
			System.out.println("get() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}
	/* Prints a nice message based on whether a test passed. 
	 * The \n means newline. */
	public static void printTestStatus(boolean passed) {
		if (passed) {
			System.out.println("Test passed!\n");
		} else {
			System.out.println("Test failed!\n");
		}
	}


	/** Adds a few things to the list, checking isEmpty() and size() are correct, 
	  * finally printing the results. 
	  *
	  * && is the "and" operation. */
	public static void addIsEmptySizeTest() {
		System.out.println("Running add/isEmpty/Size test.");
		System.out.println("Make sure to unwcomment the lines below (and delete this print statement).");

		ArrayDeque<String> lld1 = new ArrayDeque<String>();

		boolean passed = checkEmpty(true, lld1.isEmpty());
		lld1.addFirst("front");
		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
		passed = checkSize(1, lld1.size()) && passed;
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.addLast("middle");
		passed = checkSize(2, lld1.size()) && passed;

		lld1.addLast("back");
		passed = checkSize(3, lld1.size()) && passed;
		lld1.addLast("back3");
		lld1.addLast("back4");
		lld1.addLast("back5");
		lld1.addFirst("back6");
		lld1.addLast("back7");
		lld1.printDeque();
		lld1.addLast("back8");
		lld1.printDeque();
		lld1.addFirst("back9");
		lld1.addLast("back10");

		System.out.println("Printing out deque: ");
		//System.out.println("Full? " + lld1.isfull());
		lld1.printDeque();

		printTestStatus(passed);
		System.out.println(lld1.get(0));
	}

	/** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
	public static void addRemoveTest() {

		System.out.println("Running add/remove test.");

		ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
		// should be empty 
		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst(10);
		lld1.addFirst(9);
		lld1.addFirst(8);
		lld1.addLast(7);
		lld1.removeFirst();

		// should be empty
		lld1.printDeque();

		//printTestStatus(passed);

	}

	public static void getTest() {
		System.out.println("Running get test.");

		ArrayDeque<Integer> ArrayDeque = new ArrayDeque<Integer>();
		// should be empty

		ArrayDeque.addLast(0);
		ArrayDeque.addLast(1);
		ArrayDeque.addFirst(2);
		ArrayDeque.get(0) ;
		ArrayDeque.get(2)  ;
		ArrayDeque.addLast(5);
			ArrayDeque.printDeque();
			System.out.println(ArrayDeque.size());
		ArrayDeque.removeFirst()  ;
			ArrayDeque.printDeque();
			System.out.println(ArrayDeque.size());
		ArrayDeque.addFirst(7);
		ArrayDeque.removeLast()    ;
		ArrayDeque.addFirst(9);
		ArrayDeque.addLast(10);
		ArrayDeque.addFirst(11);
		ArrayDeque.addFirst(12);
		ArrayDeque.addFirst(13);
		ArrayDeque.addLast(14);
		ArrayDeque.addFirst(15);
			ArrayDeque.printDeque();
			System.out.println(ArrayDeque.size());
		ArrayDeque.get(7)      ;
		ArrayDeque.addFirst(17);
			ArrayDeque.printDeque();
			System.out.println(ArrayDeque.size());
		ArrayDeque.get(4)      ;
		ArrayDeque.removeFirst()    ;
			ArrayDeque.printDeque();
			System.out.println(ArrayDeque.size());
		ArrayDeque.get(7)      ;
		ArrayDeque.addFirst(21);
			ArrayDeque.printDeque();
			System.out.println(ArrayDeque.size());
		ArrayDeque.get(7)   ;
		// should be empty
		//boolean passed = checkget( 14, ArrayDeque.get(0));
		//printTestStatus(passed);

	}
	public static void main(String[] args) {
		System.out.println("Running tests.\n");
		//addIsEmptySizeTest();
		//addRemoveTest();
		getTest();
	}
} 