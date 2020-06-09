package core.interestingfactchecks;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HashCodeAndEquals {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// https://javarevisited.blogspot.com/2011/02/how-to-write-equals-method-in-java.html
		// https://www.journaldev.com/21095/java-equals-hashcode#:~:text=Java%20Object%20hashCode()%20is,code%20value%20of%20the%20object.&text=If%20two%20objects%20are%20equal,not%20required%20to%20be%20different.
		
		/*
		String s1 = "abc";
		
		String s2 = "abc";
		
		String s3 = s2;
		
		System.out.println((s1==s2) + "\t" + s1.equals(s2));
		
		System.out.println((s1==s3) + "\t" + s1.equals(s3));
		
		System.out.println((s3==s2) + "\t" + s3.equals(s2));
		
		String s4 = "abcd";
		
		System.out.println((s1==s4) + "\t" + s1.equals(s4));
		*/
		String s = "adsad";
		
		
		Integer a = new Integer(1);
		Integer b = new Integer(2);
		Integer c = new Integer(1);
		
		System.out.println((a==b) + "\t" + a.equals(b));	// false	false
		
		System.out.println((c==a) + "\t" + c.equals(a));	// false	true
		
		c = b;
		System.out.println((c==b) + "\t" + c.equals(b));	// true		true
		
		List<Test> l = new ArrayList<Test>();
		
		l.add(new Test());
		l.add(new Test());
		//Test t = new Test();
		//l.sort(c);
		
		if( ( new Test() ) == ( new Test() ) )
		System.out.println("equal");
		
		List<String> r = new ArrayList<String>();
		r.add("");
		r.add("");
		r.sort(new Comparator<String>() {

			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return 0;
			}});
		

	}

}

class Test implements Comparable{
	int i;
	String s;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + i;
		result = prime * result + ((s == null) ? 0 : s.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Test other = (Test) obj;
		if (i != other.i)
			return false;
		if (s == null) {
			if (other.s != null)
				return false;
		} else if (!s.equals(other.s))
			return false;
		return true;
	}
	
	private int compareTo() { // need to implement comparable interface
		// TODO Auto-generated method stub
		return 0;
	}

	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
