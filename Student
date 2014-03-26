import java.io.*;
import java.util.*;

/**
 * Student class represents a student for a university database.
 * Each Student object consists of an ID, name, year, major, GPA, and advisor (ID).
 */
public class Student implements Comparable, Serializable {
	private int ID;
	private String name;
	private String year;
	private String major;
	private double GPA;
	private int advisorID;
	
	public Student(String n, String y, String m, int i, double g, int a) {
		name = n;
		year = y;
		major = m;
		ID = i;
		GPA = g;
		advisorID = a;
	}
	
	public int getID() {
		return ID;
	}
	
	public String toString() {
		String str = new String("");
		str += "\nID: "+ID;
		str += "\nName: "+name;
		str += "\nYear: "+year;
		str += "\nMajor: "+major;
		str += "\nGPA: "+GPA;
		str += "\nAdvisor ID: "+advisorID;
		return str;
	}
	
	// Props to Cole for the idea of two possible comparisons
	public int compareTo(Object o) throws ClassCastException {
		if (!(o instanceof Student || o instanceof Integer)) {
			throw new ClassCastException("Invalid input!");
		}
		if (o instanceof Student) {
			int oID = ((Student)o).getID();
			return this.ID - oID;
		}
		else {
			return this.ID - (Integer)o;
		}
	}
	
	public void setAdvisor(int oID) {
		advisorID = oID;
	}
}
