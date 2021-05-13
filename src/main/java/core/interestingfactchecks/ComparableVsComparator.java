package core.interestingfactchecks;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * equals() is a form of limited comparison where we just check if two entities are equal or not. In more broad from of comparison we need
 * to know if two entities are equal and if not then which one is greater or which one is smaller. This forms the basis for sorting objects.
 * On custom objects the sorting is performed based on the members of the class aka class variables. For this Java provides two interfaces
 * to sort objects using data members of the class:
 * 		1. Comparable (defines natural order of sort for a class)(to sort objects of implementing class)
 * 		2. Comparator (is external to any class)(can sort objects of different classes based on common attributes)
 * 
 * 
 */
public class ComparableVsComparator {

	public static void main(String[] args) {
		
		// Comparator Usage:
		formLargest();
		
		
	}

	/**
	 * https://www.geeksforgeeks.org/given-an-array-of-numbers-arrange-the-numbers-to-form-the-biggest-number/
	 */
	private static void formLargest() {
		String arr[] = {"1", "34", "3", "98", "9", "76", "45", "4"};
		List<String> myNums = Arrays.asList(arr);
		
		Collections.sort(myNums, new Comparator<String>() {
			public int compare(String o1, String o2) {
				String a = o1+o2;
				String b = o2+o1;
				
				int res = (a.compareTo(b) > 0) ? -1:1;
				
				return res;
			}
		});
		
		for(String s: myNums) {
			System.out.print(s);
		}
		
	}
}
