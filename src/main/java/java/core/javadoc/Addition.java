package java.core.javadoc;

/**
 * 
 * <h1>Addition</h1> This Addition class has two methods.
 * <p>
 * Typically this section can be used to tell about the class or purpose of the
 * class and functionality in brief.
 * 
 * @author amans
 * @version 1.0
 * 
 */
public class Addition {

	/**
	 * 
	 * Describe the method purpose or functionality in brief here.
	 * 
	 * @param a First argument
	 * @param b Second argument
	 * @return The sum of the above given arguments
	 */
	protected static int addTwo(int a, int b) {
		return a + b;
	}

	/**
	 * 
	 * @param a First argument
	 * @param b Second argument
	 * @param c Third argument
	 * @return The sum of the above given arguments
	 */
	protected int addThree(int a, int b, int c) {
		return a + b + c;
	}

}
