package java.core.javadoc;

// Java doc is typically written above a "class" "method" or "variable"
// To write java doc in eclipse just type "/**" above your class method or variable and press enter
// Make additions to above generated java doc and save
// This info now will be visible when you hover over the so class name or function wherever used.

// To generate HTML java doc:
// In the package explorer right click on the package name -> Show In -> Terminal
// Execute the command "javadoc Addition.java"
// If no errors were encountered then javadoc is successfully generated
// Refresh the package so that contents reflect
// Open index-all.html in Eclipse browser

public class AdditionMain {

	public static void main(String[] args) {

		int two = Addition.addTwo(10, 18);
		System.out.println(two);

		Addition add = new Addition();
		int three = add.addThree(10, 18, 500);
		System.out.println(three);

	}

// To know how to use java doc more meaningfully
// Ctrl+hover over println and have a look at its Declaration

}
