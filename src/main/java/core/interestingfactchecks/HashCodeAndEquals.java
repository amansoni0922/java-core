package core.interestingfactchecks;

/**
 * Why Java have hash-codes ?
 * => In computer science the concept of bucketing data is extremely useful in improving the performance of look ups. A given data can be
 * bucketized which improves the look up on the data significantly. Data in its basic form is object in Java. To bucketize objects, Java uses
 * hashcodes to determine which bucket a particular object should go to.
 * 
 * Hashcodes are calculated from the data. In Java, object variables are used to calculate hashcodes. Therefore, hashcodes can be perceived as
 * a property of a Java object. We can decide on what values the hashcode should get calculated. A good hashcode function is the one that
 * distributes over the input evenly and for that prime numbers are used to calculate hashcodes. Prime numbers has this mathematical property
 * of distributing evenly and causing less collision. Collision is said when two different input to hashfunction results in same output i.e..
 * same hashcode. So in simple words, a good hashfunction is the one that reduces the chances of collision by spreading evenly.
 * 
 * HashMap is a very simple yet very powerful data structure that is based off bucketing. Hashcodes main usage and purpose is to make HashMap
 * bucketing possible. HashMap further forms base for other Java data structures like HashSet, TreeMap, HashTable etc. 
 * 
 * In bucketing logic we can calculate the hashcode using the key and look directly into that bucket for its respective value. But the bucket
 * may contain more than one value. In terms of hashmap all the collided key-values will reside in one bucket. In such cases we need to use
 * equals on the key to figure out the right value to be returned. Since, hashcode and equals both will be used to figure out the value to be
 * returned we need to have a common understanding of relation between hashcode and equals. This common understanding is achieved by
 * following a contract between hashcode and equals. This contract goes like:
 * 1. Two equal objects must return equal hashcodes as well.
 * 2. Two distinct objects can have equal hashcodes. (and so collision happens)
 * 3. Hashcode value may vary application to application but should remain consistent in one instance and should return same value every
 * 		single time for the same input to hashfunction.
 * 
 * To put data into hashmap only hashcode is needed. But to retrieve data from hashmap we need hashcode and might need equals as well. 
 * So the above contract needs to be maintained to make sure that the data is retrieved correctly.
 * 
 * When should you not bother about hashcode ?
 * => If its known that your application doesn't use any bucket based data structure or nor will it use in near future then in such cases
 * 		hashcode is of no use for you.
 * 
 * [IMPORTANT]: Hashcodes are purely used for bucketing and are never used for any form of equality or comparison operation.
 *
 * References: 
 *		1. https://javarevisited.blogspot.com/2011/02/how-to-write-equals-method-in-java.html
 *		2. https://www.journaldev.com/21095/java-equals-hashcode
 */
public class HashCodeAndEquals {

	public static void main(String[] args) {
		
		//Comparisons.usingInbuiltObject();
		
		//Comparisons.usingSimpleCustomObject();
		
		Comparisons.usingSophisticatedCustomObject();
		
	}
}


class Comparisons {
	
	/**
	 * NOTE: String is not a typical example of objects in Java. Most of the real time Java objects are mutable. Strings are immutable
	 * 		and behave very similar to primitive data types. Alternatively, StringBuilder and StringBuffer are typical Java objects.
	 */
	public static void usingInbuiltObject() {
		StringBuilder sb;	// this tells compiler that sb will hold reference to a StringBuilder object. Java is strongly typed.
		sb = new StringBuilder("zero");	// we created a StringBuilder object using the 'new' keyword and assigned its reference to sb.
		
		StringBuilder one  = new StringBuilder("one");
		StringBuilder two  = new StringBuilder("two");
		performChecks(one, two, "two very obvious distinct objects");	// diff references diff values
		System.out.println("------------------------------------------------------");
		
		sb = one;
		performChecks(one, sb, "two references pointing to same object");	// same references same values
		System.out.println("------------------------------------------------------");
		
		StringBuilder ONE = new StringBuilder("one");		// diff references same values
		performChecks(one, ONE, "two objects having same value");	// StringBuilder doesn't override equals() of Object class. So when
																// we call equals() over StringBuilder it invokes equals() from Object class
																// and as we know Object class equals() method just checks reference.
																// So if references are equal then it returns true else it returns false.
		System.out.print("Corrected comparison: ");					// StringBuilder does override toString() method and so we can compare
		System.out.println(one.toString().equals(ONE.toString()));	// values utilizing the toString() method.
		/*
		 * Note: above hash-codes are different even though values are same. This is expected and is as per hash-code equals contract.
		 * Since, StringBuilder doesn't override equals so as per the Object's equals these two objects are not equal and they are expected
		 * to have distinct hash-code. Collision caused by hashfunction is the only possible reason if these two un-equal object end up
		 * having same hash-codes.
		 */
		System.out.println("------------------------------------------------------");
		
		System.out.println();		// same reference diff values: CASE NOT POSSIBLE
	}
	
	
	/**
	 * Uses a simple custom class Employee.
	 */
	public static void usingSimpleCustomObject() {
		
		Employee one  = new Employee();
		Employee two  = new Employee();
		performChecks(one, two, "two very obvious distinct objects");	// diff references diff values
		System.out.println("------------------------------------------------------");
		
		Employee e = one;
		performChecks(one, e, "two references pointing to same object");	// same references same values
		System.out.println("------------------------------------------------------");
		
		Employee ONE = new Employee();		// diff references same values
		performChecks(one, ONE, "two objects having same value");	// Neither overrides hashcode nor equals
		System.out.println("------------------------------------------------------");
		
		System.out.println();		// same reference diff values: CASE NOT POSSIBLE
	}
	
	
	/**
	 * Uses a sophisticated custom class that have overridden equals() and hashcode() methods.
	 */
	public static void usingSophisticatedCustomObject() {
		Animal dog  = new Animal(4, "land", false);
		Animal whale = new Animal(0, "water", true);
		
		performChecks(dog, whale, "two very obvious distinct objects");	// diff references diff values
		System.out.println("------------------------------------------------------");
		
		Animal a = dog;
		performChecks(dog, a, "two references pointing to same object");	// same references same values
		System.out.println("------------------------------------------------------");
		
		Animal DOG  = new Animal(4, "land", false);		// diff references same values
		performChecks(dog, DOG, "two objects having same value");	// Animal class overrides equals() method and as per its logic both are
																	// equal from which it follows that their hash-code must also be equal.
		
		/*
		 * Note: DOG and dog are equal and have same hash-code but DOG and dog are two object references which are pointing to different
		 * 			location in the heap memory of JVM and so '==' operator over those returns false.
		 * 		 Another observation is that when we don't override toString method in custom class then passing its object to println
		 * 			will print the fully qualified class name along with its hashcode preceded by '@'. This hash-code is in hexadecimal.
		 * 		 Unlike C, Java has no way to print the memory address of an object location. The reason for this is because all the memory
		 * 			management tasks are handled by JVM and programmer will never need to know the object location in system RAM in Java.
		 */
		System.out.println("------------------------------------------------------");
		
		System.out.println();		// same reference diff values: CASE NOT POSSIBLE
	}
	
	
	/**
	 * A generic method where type is say T and objects o1 and o2 of type T are passed as arguments.
	 */
	public static <T> void performChecks(T o1, T o2, String msg) {
		System.out.println("\n---------" + msg + "--------");
		
		// check references
		System.out.print("o1==o2\t\t\t");
		System.out.print(o1 + "==" + o2 + "\t\t");	// here if the passed object doesn't override toString then the address of the object
		System.out.println(o1==o2);					// that is stored in the reference variable gets printed.
		
		// check values
		System.out.print("o1.equals(o2)\t\t");
		System.out.print(o1 + ".equals(" + o2 + ")" + "\t\t");
		System.out.println(o1.equals(o2));
		
		
		// check hash-codes
		int o1Hash = o1.hashCode();
		int o2Hash = o2.hashCode();
		System.out.print("o1Hash==o2Hash\t\t");
		System.out.print(o1Hash + "==" + o2Hash + "\t");
		System.out.println(o1Hash==o2Hash);
	}
}


class Employee {
}


class Animal {
	int legs;		// no. of legs. Suggestion: use byte.
	String type;	// only three types: water, land, air. Suggestion: use enum.
	boolean isWild;	// true for wild, false for domestic
	
	Animal(int l, String t, boolean wild) {	// one and only constructor to create Animal
		legs = l;
		type = t;
		isWild = wild;
	}

	/**
	 * Let's say two animals are equal if they have same no. of legs, same type and same nature (wild or domestic)
	 * So for that we override the equals methods as below. (Code auto-generated using eclipse)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)	// if same references then they are obviously equal
			return true;
		if (obj == null)	// if argument passed is null then false. From above we observe two nulls are equal.
			return false;
		if (getClass() != obj.getClass())	// check if they are objects of same class
			return false;
		Animal other = (Animal) obj;	// object passed in argument is down-casted to Animal so as to do member wise comparison.
		if (isWild != other.isWild)	
			return false;
		if (legs != other.legs)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;	// if till now it has not returned in any of the above cases then the objects are equal.
	}
	
	/**
	 * Since above we have overridden the equals method to implement our logic of Animal comparison so we also must
	 * override hashcode to maintain the equals-hashcode contract. Not overriding hashcode will not cause any compile or runtime
	 * exception or error but will cause logical errors if objects of Animal are used in hashcode based data structures.
	 * 
	 * Below is the eclipse auto-generated hash-code. This can be completely replaced with a custom implementation of your own.
	 * But whatever be implementation it should make sure that the hashcode calculation is done on exactly those fields which are used
	 * to establish equality in the equals method above.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isWild ? 1231 : 1237);
		result = prime * result + legs;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
		//return 3333;	// This experiment shows that '==' does not compare hashcode instead it just compares the references i.e.. are the
						// object references in stack memory are pointing to same address in the heap memory.
	}
	
}

