package core.interestingfactchecks;

/**
 * There are a lot of times when one needs to work on a copy of an object and retain the original. To create a copy of an object clone()
 * is the way to go in Java. But except the primitive types and Strings, most of the Java objects are mutable. As cloning in Java by default
 * is shallow cloning and shallow cloning have major drawbacks when it comes to cloning mutable complex data types. To overcome those
 * drawbacks we have to write custom logic in the overriding clone method body to perform deep cloning of the object.
 */
public class CloningObjects {

	public static void main(String[] args) throws CloneNotSupportedException {
		
		Cat cat = new Cat(100, "Caramel");
		Cat catClone = (Cat) cat.clone();
		Comparisons.performChecks(cat, catClone, "-------Comparing original with its clone-------");	// expecting equal as true
		
		cat.reprice(222);	// lets change just the price of the original 
		Comparisons.performChecks(cat, catClone, "-------Comparing after changing originals price-------");	// expecting equal as false
		
		cat.reprice(100); // put the original price back
		cat.rename("karamel");	// lets change just the name of the original 
		Comparisons.performChecks(cat, catClone, "-------Comparing after changing originals name-------");	// expecting equal as false
		/*
		 * Above we observe that renaming original affects clone as well. This is happening because unlike price name is not primitive.
		 * This is shallow copying where member objects are not cloned but just the references gets copied and results in affecting the clone.
		 * To get around this we need to go for deep cloning. Deep cloning is where the member objects are also cloned and hence changes in
		 * one doesn't reflect in the other. To implement deep cloning one has to write custom logic in the overriding clone method.
		 */
		
		Dog dog = new Dog(100, "Caramel");
		Dog dogClone = (Dog) dog.clone();
		Comparisons.performChecks(dog, dogClone, "-------Comparing original with its clone-------");	// expecting equal as true
		
		dog.rename("karamel");	// lets change just the name of original 
		Comparisons.performChecks(dog, dogClone, "-------Comparing after changing originals name-------");	// expecting equal as false

	}
}

/**
 * A cloneable class that supports simple cloning.
 */
class Cat implements Cloneable {
	private int price;
	private StringBuilder name = new StringBuilder();
	
	Cat(int p, String n) {
		price = p;
		name.append(n);
	}
	
	public void rename(String newName) {
		name.setLength(0);	// delete old name
		name.append(newName);
	}
	
	public void reprice(int newPrice) {
		price = newPrice;
	}
	
	@Override
	public String toString() {	// to check if rename worked or not we need toString method.
		return "Cat [price=" + price + ", name=" + name + "]";
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {	// this is eclipse generated default cloning logic
		// TODO Auto-generated method stub
		return super.clone();
	}
	
	@Override
	public boolean equals(Object obj) {	// we will be comparing clones. Whenever there is comparison then one must override equals.
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cat other = (Cat) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))	// Since, StringBuilder doesn't override equals so here the references are being compared.
			return false;						// ideally values should be compared using (!name.toString().equals(other.name.toString()))
		if (price != other.price)
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {	// Whenever equals is overridden one must override hashcode as well.
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.toString().hashCode());	// since StringBuilder doesn't override hashcode so
		result = prime * result + price;										// converted toString and then called hashcode.
		return result;								// This ensures as far as name and price are same then objects are also same.
	}
}

/**
 * A cloneable class that does deep cloning.
 */
class Dog implements Cloneable {
	private int price;
	private StringBuilder name = new StringBuilder();
	
	public Dog(int p, String n) {
		price = p;
		name.append(n);
	}
	
	public void rename(String newName) {
		name.setLength(0);
		name.append(newName);
	}

	@Override
	public String toString() {
		return "Dog [price=" + price + ", name=" + name + "]";
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// custom clone logic
		Dog d = (Dog) super.clone();
		d.name = new StringBuilder(this.name.toString());	// 'this' refers to the object on which the clone was called.
		return d;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dog other = (Dog) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.toString().equals(other.name.toString()))	// Since, StringBuilder doesn't override equals
			return false;											// so compare values using toString.
		if (price != other.price)
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.toString().hashCode());
		result = prime * result + price;
		return result;
	}
	
}
