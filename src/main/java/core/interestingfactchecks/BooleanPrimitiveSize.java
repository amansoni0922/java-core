package core.interestingfactchecks;

/**
 * All primitive data types in Java have fixed sizes except boolean. Size of boolean data type is JVM implementation dependent.
 * This program is to uncover what exactly does that mean and what are its implications.
 * 
 * Every release of Java has a Java language specification and Java VM specification.
 * Can be found here: https://docs.oracle.com/javase/specs/#22909
 * 
 * From the latest release (i.e..14) JVM specification for boolean types can be found here:
 * https://docs.oracle.com/javase/specs/jvms/se14/html/jvms-2.html#jvms-2.3.4 at Section 2.3.4
 * Java primitive boolean data type uses int type internally which means a boolean variable is of size 32 bits or 4 bytes. 
 * But for boolean arrays, JVM uses byte type internally which means a boolean array of size n will be of n*8 bits or n bytes.
 * 
 * So now the question is why the above approach ? and why can't they simply have a bit for boolean type ?
 * For two reasons:
 * Reason1: The JVM stack size:- JVM uses a 32-bit stack cell, used to hold local variables, method arguments, and expression values.
 * Primitives that are smaller than 1 cell are padded out, primitives larger than 32 bits (long and double) take 2 cells.
 * The fixed size stack cell of 32 bit does have some peculiar side-effects (such as the need to mask bytes) but 
 * this technique minimizes the number of opcodes thus improving performance speed wise (not memory wise though). Since Java is high
 * level general purpose programming language so speed is the priority.
 * 
 * Reason2: CPU register size:- CPUs operate on a specific data type length. In case of 32bit CPUs the registers are 32 bits long
 * which is also the size of int in Java. Everything below/above that must be filled/split to this length before the CPU can process it. 
 * This doesn't take much time, but if you need 2 CPU cycles instead of 1 for basic operations, this means doubled cost(time-wise).
 * This specification is dedicated for 32bit CPUs so that they can process booleans with their native data type.
 * In conclusion, you can only have one here: speed or memory - SUN/Oracle decided for speed. It's a limitation
 * that is expected to be overcome soon in future releases. 
 * But for now this is the most efficient way speed wise (not memory wise though).
 * 
 * Reference: http://www.programmr.com/blogs/two-things-every-java-developer-should-know-about-booleans
 * 
 * 
 * So storing single bits though takes up just the required space but are time costly when being operated upon and for this reason
 * storing single bits is not going to help in improving performance.
 * However, there are times when we want to store single bits or operate over single bits in Java. For that there is an 
 * in-built library of Java called "java.util.BitSet". This library can be used to do bit storage/manipulation/operation.
 * 
 * Bonus: BitSet can also be used as an alternative to boolean[] if one wishes. This will potentially save space but not time.
 * Reference: https://stackoverflow.com/questions/605226/boolean-vs-bitset-which-is-more-efficient
 */
public class BooleanPrimitiveSize {

	public static void main(String[] args) {
		
		/*
		 * Java has no sizeof operator to find the size of primitive data types but all Java primitive wrappers
		 * except Boolean provide a SIZE constant in bits that could be divided by eight to get the size of the data type in bytes.
		 */
		System.out.println("Size of byte: " + (Byte.SIZE/8) + " bytes.");
	    System.out.println("Size of short: " + (Short.SIZE/8) + " bytes.");
	    System.out.println("Size of int: " + (Integer.SIZE/8) + " bytes.");
	    System.out.println("Size of long: " + (Long.SIZE/8) + " bytes.");
	    System.out.println("Size of char: " + (Character.SIZE/8) + " bytes.");
	    System.out.println("Size of float: " + (Float.SIZE/8) + " bytes.");
	    System.out.println("Size of double: " + (Double.SIZE/8) + " bytes.");
	    
	    //System.out.println("Size of double: " + (Boolean.SIZE/8) + " bytes.");	// Boolean has no such constant
	    
	    /*
	     * Although, your machine might be 64 bit, but its the JVM that decides what will be the size of int and thats the reason
	     * no matter which system you run this code it will always give 4 bytes for int and thats the magic of JVM which makes Java
	     * platform independent. JVM establishes the compatibility between your Java code and machine architecture.
	     * 
	     * Further Read: JIT (just-in-time compiler)
	     */
	}

}
