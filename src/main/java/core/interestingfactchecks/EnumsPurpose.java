package core.interestingfactchecks;

/**
 * What is the purpose of enums ?
 * => The main objective of enum is to define our own data types(Enumerated Data Types).
 * => Enum constants are static and so can be accessed using the enum name.
 * => Most used methods of enums are: values(), ordinal() and valueOf().
 * 
 * Implementation details ?
 * => Enum declaration can be done outside a Class or inside a Class but not inside a Method.
 * => Enum constants by convention should be named in capital letters.
 * => Enum constants are always implicitly public static final.
 * => Just like regular classes enum can also implement many interfaces.
 * => Enum can contain constructor and it is executed separately for each enum constant at the time of enum class
 * 		loading. We can’t create enum objects explicitly and hence we can’t invoke enum constructor directly.
 * => Instance of Enum in Java is created when any Enum constants are first called or referenced in code.
 * => Enum constructors can NEVER be invoked directly in code. They are always called automatically when an enum is initialized.
 * 
 * Pros ?
 * => Enums provide high level understanding of the business logic. {ex: instead of using 0,1 use HEAD,TAIL}
 * => Switch statement supports enum. This significantly improves readability of code.
 * => Enum in Java are type-safe: Enum has their own name-space. It means your enum will have a type for example “Shade” in below
 * 		example and you can not assign any value other than specified in Enum Constants list.
 * 
 * Cons ?
 * => All enums implicitly extend java.lang.Enum class. As a class can only extend one parent in Java, so an enum
 * 		cannot extend anything else. Say your enum is Shade then internally java creates a final class Shade and extends java.lang.Enum
 * 
 * When to use enums ?
 * => Suppose that we are writing a Scissor-Paper-Stone game. We could use three arbitrary integers (e.g., 0, 1, 2; or 88, 128, 168),
 * 		three strings ("Scissor", "Paper", "Stone"), or three characters ('s', 'p', 't') to represent the three hand-signs. The main
 * 		drawback is we need to check the other infeasible values (e.g. 3, "Rock", 'q', etc.) in our program to ensure correctness.
 * 		A better approach is to define our own list of permissible values in a construct called enumeration defined by enum in Java.
 * => You should always use enums when a variable (especially a method parameter) can only take one out of a small set of possible
 * 		values. Examples would be things like type constants (contract status: “permanent”, “temp”, “apprentice”), or flags 
 * 		(“execute now”, “defer execution”).
 * => Other relevant examples where enums are a best choice are:
 * 		1. Universals like {Days of week, Months of year, Zodiac signs, Planet names}
 * 		2. Game outcomes {Card suites, ChessPieces, Rock-Paper-Scissor}
 * 		3. Categories like [ {Big Medium Small} {Fair Good Best} ]
 * 		4. Mutually exclusive tags like [ {Food, Beverage, Refreshments} {Sports, Studies, Entertainment, Work} ]
 * 
 * Where to avoid using ?
 * 		1. Heads and Tails (best use enums is realized when the number of possible values are fixed and greater than 2.)
 * 							(So this example is not one of the the best use cases)
 * 
 *
 * References:
 *		1. https://www.geeksforgeeks.org/enum-in-java/
 *		2. https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaEnum.html
 *		3. https://crunchify.com/why-and-for-what-should-i-use-enum-java-enum-examples/
 */
public class EnumsPurpose {

	/**
	 * Declaring enum inside a class. Now "Shade" is your created data type which
	 * can take values that are defined in the enum. So Shade can be GREY, SLATE or
	 * BLACK.
	 */
	enum Shade {
	GREY, SLATE, BLACK;
	}

	public static void main(String[] args) {

		// Accessing the enum that is declared inside a class.
		Shade shade = Shade.GREY;
		System.out.println(shade);
		
		// Accessing the enum that is declared outside a class.
		Color color = Color.RED;
		System.out.println(color + "\n");
		
		Shade blackShade = Shade.BLACK;
		Color blackColor = Color.BLACK;
		
		//System.out.println(blackShade==blackColor);	// NOT ALLOWED: different enums are incompatible types
		
		System.out.println( blackShade.toString().equals( blackColor.toString() ) );	// we see strings are same
		System.out.println( (blackShade.toString()==blackColor.toString() ) );	// so JVM assigned same reference from string pool
		System.out.println();
		
		// enums values() method: used to iterate over the enum
		System.out.println("----Iterating over enum Color----");
		Color carr[] = Color.values();
		for(Color c: carr) {
			System.out.print(c + " ");
		}
		
		// enums valueOf() method: used to return the enum constant for a given string.
		Color myColor = Color.valueOf("GREEN");
		System.out.println("\n\n" + myColor);
		//System.out.println(Color.valueOf("GREY"));	// will result in exception
		
		// enums ordinal method: used to know the index of an enum constant 
		System.out.println(myColor.ordinal() + "\n");
		
		// Using custom value enum
		Country myCountry = Country.INDIA;
		System.out.println(myCountry + "\t" + myCountry.getCurrency());
		
		System.out.println(Country.valueOf("US").getCurrency());	// can access custom value using enum constant name
		//System.out.println(Country.valueOf("Dollar"));	// cannot access enum constant using custom value. results in exception

	}
}

/**
 * Declaring enum outside a class. Now "Color" is your created data type which
 * can take values that are defined in the enum. So Color can be RED, BLUE,
 * GREEN or BLACK.
 */
enum Color {
	RED, BLUE, GREEN, BLACK;
}

/**
 * Custom value enum.
 */
enum Country {
	US("Dollar"), INDIA("Rupees"), RUSSIA("Ruble");	// defined custom values in parenthesis
	
	private String currency;
	
	Country (String currency) {	// whenever we define custom values then we also need to write constructor for enum.
		this.currency = currency;
	}
	
	String getCurrency() {	// need getter method since currency variable is private.
		return currency;
	}
}

