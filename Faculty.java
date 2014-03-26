import java.util.Vector;
import java.io.*;
import java.util.*;

/**
 * Faculty class represents a faculty member for a university database.
 * Each Faculty object consists of an ID, name, rank, department,
 * and list of advisees (by ID).
 */
public class Faculty implements Serializable, Comparable {
	private int ID;
	private String name;
	private String rank;
	private String department;
	private Vector<Integer> adviseeID;
	
	public Faculty(int i, String n, String r, String d) {
		ID = i;
		name = n;
		rank = r;
		department = d;
		adviseeID = new Vector(0, 1);
	}
	
	public int getID() {
		return ID;
	}
	
	public void setAdvisee(int oID) {
		if (!(adviseeID.contains(oID))) {
			adviseeID.add(oID);
		}
	}
	
	public int compareTo(Object o) throws ClassCastException {
		if (!(o instanceof Faculty || o instanceof Integer)) {
			throw new ClassCastException("Invalid input!");
		}
		if (o instanceof Faculty) {
			int oID = ((Faculty)o).getID();
			return this.ID - oID;
		}
		else {
			return this.ID - (Integer)o;
		}
	}
	
	public String toString() {
		String str = "";
		str += "\nID: "+ID;
		str += "\nName: "+name;
		str += "\nRank: "+rank;
		str += "\nDepartment: "+department;
		if (adviseeID != null) {
			str += "\nAdvisee(s):";
			for (int i = 0; i < adviseeID.size(); ++i) {
				str += ("\n\t"+adviseeID.elementAt(i));
			}
		}
		return str;
	}
}
