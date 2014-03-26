import java.io.*; 
import java.util.*; 
import java.lang.*;

/**
 * University student/faculty database simulation.
 *
 */
public class DB implements Serializable {
	private BufferedReader br;
	private BST[] past5;
	private Student student;
	private Faculty prof;
	private File facultyFile;
	private File studentFile;
	private BST masterStudent;
	private BST masterFaculty;
	private Integer[] stud0fact1;
	
	// CONSTRUCTOR
	public DB() {
		loadFile();
		past5 = new BST[5];
		stud0fact1 = new Integer[5];
		br = new BufferedReader(new InputStreamReader(System.in));
	}
	
	/**
	 * Searches for a given Student or Faculty and returns the object
	 * @param int x - determines which database to search: 0 = student, 1 = faculty
	 * @param int ID - ID of the constituent to find
	 * @return Student or Faculty with given ID
	 */
	public Comparable find(int x, int ID) {
		if (x == 0) {
			return (Student)masterStudent.find(ID);
		}
		else {
			return (Faculty)masterFaculty.find(ID);
		}
	}
	
	/**
	 * Creates a backup of up to 5 previous actions
	 * @param BST x - the tree saved
	 * @param int y - lets the method know whether it is a student or faculty tree
	 */
	public void createBackup(BST x, int y) {
		for (int i = 0; i < 4; ++i) {
			past5[i] = past5[i+1];
			stud0fact1[i] = stud0fact1[i+1];
		}
		past5[4] = x;
		stud0fact1[4] = y;
	}
	
	/**
	 * Undoes the last action. Can be done up to 5 times in a row.
	 */
	public void undo() {
		if (stud0fact1[4] == 0) {
			masterStudent = past5[4];
		}
		else if (stud0fact1[4] == 1) {
			masterFaculty = past5[4];
		}
		else {
			System.out.println("Cannot undo any further!");
		}
		for (int i = 4; i > 0; --i) {
			past5[i] = past5[i-1];
			stud0fact1[i] = stud0fact1[i-1];
		}
		past5[0] = null;
		stud0fact1[0] = null;
		System.out.println("Undone!");
	}
	
	/**
	 * Inserts a new Comparable object (Student or Faculty) into the BST
	 * @param Comparable o - represents either a Student or Faculty object
	 */
	public void insert(Comparable o) {
		if (o instanceof Student) {
			masterStudent.insert(o);
			createBackup(masterStudent, 0);
		}
		else if (o instanceof Faculty) {
			masterFaculty.insert(o);
			createBackup(masterFaculty, 1);
		}
		else {
			System.out.println("This item is not a student or faculty!");
		}
	}
	
	/**
	 * Prints the student master tree
	 */
	public void printStudents() {
		masterStudent.printTree();
	}
	
	/**
	 * Prints the faculty master tree
	 */
	public void printFaculty() {
		masterFaculty.printTree();
	}
	
	/**
	 * Loads the file with the given filename
	 */
	public void loadFile() {
		try {
			studentFile = new File("studentTable");
			facultyFile = new File("facultyTable");
			studentFile.createNewFile();
			facultyFile.createNewFile();
			
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(studentFile));
			masterStudent = (BST)in.readObject();
			in.close();
			in = new ObjectInputStream(new FileInputStream(facultyFile));
			masterFaculty = (BST)in.readObject();
			in.close();
		}
		catch (Exception e) {
			masterFaculty = new BST();
			masterStudent = new BST();
		}
	}
	
	/**
	 * Saves the database to a file with the given filename
	 */
	public void saveFile() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(facultyFile));
			oos.writeObject(masterFaculty);
			oos.flush();
			oos.close();
			oos = new ObjectOutputStream(new FileOutputStream(studentFile));
			oos.writeObject(masterStudent);
			oos.flush();
			oos.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("FILENOTFOUNDEXCEPTION!");
		}
		catch (IOException f) {
			System.out.println("IOEXCEPTION!");
		}
	}
	
	/**
	 * Creates a menu by which the user can insert objects into a database
	 * Calls the insert method
	 */
	public void insertMenu() {
		int select;
		boolean isStudent;
		while (true) {
			System.out.println("0 = Student\n1 = Faculty\n");
			try {
				select = Integer.parseInt(br.readLine());
				if (select == 0) {
					isStudent = true;
					break;
				}
				else if (select == 1) {
					isStudent = false;
					break;
				}
				else {
					System.out.println("Invalid input!");
				}
			}
			catch (Exception e) {
				System.out.println("Invalid input!");
				continue;
			}
		}
		if (isStudent) {
			int ID;
			String name;
			String year;
			String major;
			double GPA;
			int advisor;
			
			while (true) {
				try {
					System.out.println("ID of new student?");
					ID = Integer.parseInt(br.readLine());
					System.out.println("Name of new student?");
					name = br.readLine();
					System.out.println("Year of new student?");
					year = br.readLine();
					System.out.println("Major of new student?");
					major = br.readLine();
					System.out.println("Current GPA of new student?");
					GPA = Double.parseDouble(br.readLine());
					System.out.println("ID of new student's faculty advisor?");
					advisor = Integer.parseInt(br.readLine());
					break;
				}
				catch (Exception f) {
					System.out.println("Invalid input!");
					continue;
				}
			}
			insert(new Student(name, year, major, ID, GPA, advisor));
		}
		else {
			int ID;
			String name;
			String rank;
			String dept;
			
			while (true) {
				try {
					System.out.println("ID of new faculty?");
					ID = Integer.parseInt(br.readLine());
					System.out.println("Name of new faculty?");
					name = br.readLine();
					System.out.println("Rank of new faculty?");
					rank = br.readLine();
					System.out.println("Department of new faculty?");
					dept = br.readLine();
					break;
				}
				catch (IOException x) {
					System.out.println("Invalid input!");
					continue;
				}
				catch (NumberFormatException y) {
					System.out.println("Invalid input!");
					continue;
				}
				catch (Exception z) {
					System.out.println("Invalid input!");
					continue;
				}
			}
			insert(new Faculty(ID, name, rank, dept));
		}
	}
	
	/**
	 * Creates a menu by which the user can delete objects from a database
	 * Deletes objects directly from the BST
	 */
	public void deleteMenu() {
		int select;
		boolean isStudent;
		while (true) {
			System.out.println("0 = Student\n1 = Faculty\n");
			try {
				select = Integer.parseInt(br.readLine());
				if (select == 0) {
					isStudent = true;
					break;
				}
				else if (select == 1) {
					isStudent = false;
					break;
				}
				else {
					System.out.println("Invalid input!");
				}
			}
			catch (Exception e) {
				System.out.println("Invalid input!");
				continue;
			}
		}
		int ID;
		while (true) {
			System.out.println("ID of the constituent to delete? (0 to go back to main menu)");
			try {
				ID = Integer.parseInt(br.readLine());
				if (ID == 0) {
					return;
				}
				if (isStudent) {
					masterStudent.delete(ID);
					createBackup(masterStudent, 0);
					break;
				}
				else {
					masterFaculty.delete(ID);
					createBackup(masterFaculty, 1);
					break;
				}
			}
			catch (Exception f) {
				System.out.println("Invalid ID!");
				continue;
			}
		}
	}
	
	/**
	 * Creates a menu by which the user can modify objects in a database
	 * Calls the modify method
	 */
	public void modifyMenu() {
		int select;
		boolean isStudent;
		while (true) {
			System.out.println("0 = Student\n1 = Faculty\n");
			try {
				select = Integer.parseInt(br.readLine());
				if (select == 0) {
					isStudent = true;
					break;
				}
				else if (select == 1) {
					isStudent = false;
					break;
				}
				else {
					System.out.println("Invalid input!");
				}
			}
			catch (Exception e) {
				System.out.println("Invalid input!");
				continue;
			}
		}
		modify(isStudent);
	}
	
	/**
	 * A continuation of the modifyMenu method
	 * Allows the user to change a student advisor or add a faculty advisee
	 * @param boolean isStudent - determines which database to use
	 */
	public void modify(boolean isStudent) {
		int ID;
		Student temp = null;
		Faculty temp1 = null;
		while (true) {
			System.out.println("Type the ID of the constituent.");
			try {
				ID = Integer.parseInt(br.readLine());
				if (isStudent) {
					temp = (Student)masterStudent.find(ID);
					break;
				}
				else {
					temp1 = (Faculty)masterFaculty.find(ID);
					break;
				}
			}
			catch (Exception f) {
				System.out.println("Invalid input!");
				f.printStackTrace();
				continue;
			}
		}
		// It's obviously just as possible to change other aspects in the same way (even less complicated, actually).
		// This one was just included as it demonstrates how other modifications can be made, and then some.
		int ID2;
		while (true) {
			if (isStudent) {
				System.out.println("What is the new Faculty Advisor ID? 0 to go back to main menu.");
			}
			else {
				System.out.println("What is the new Student Advisee ID? 0 to go back to main menu.");
			}
			try {
				ID2 = Integer.parseInt(br.readLine());
				if (ID2 == 0) {
					break;
				}
				if (isStudent) {
					Faculty ftemp = (Faculty)masterFaculty.find(ID2);
					temp.setAdvisor(ID2);
					ftemp.setAdvisee(ID);
					createBackup(masterStudent, 0);
					break;
				}
				else {
					Student stemp = (Student)masterStudent.find(ID2);
					temp1.setAdvisee(ID2);
					stemp.setAdvisor(ID);
					createBackup(masterFaculty, 1);
					break;
				}
			}
			catch (Exception f) {
				System.out.println("Advisor or Advisee ID is incorrect. Please try again.");
				modifyMenu();
				break;
			}
		}
	}
	
	/**
	 * Creates a menu by which the user can find for an object from a database
	 * Calls the find method
	 * If found, prints the found constituent
	 */
	public void findMenu() {
		int select;
		boolean isStudent;
		while (true) {
			System.out.println("0 = Student\n1 = Faculty\n");
			try {
				select = Integer.parseInt(br.readLine());
				if (select == 0) {
					isStudent = true;
					break;
				}
				else if (select == 1) {
					isStudent = false;
					break;
				}
				else {
					System.out.println("Invalid input!");
				}
			}
			catch (Exception e) {
				System.out.println("Invalid input!");
				continue;
			}
		}
		int ID;
		while (true) {
			try {
				System.out.println("What is the ID of the constituent?");
				ID = Integer.parseInt(br.readLine());
				break;
			}
			catch (Exception x) {
				System.out.print("Invalid input!");
				continue;
			}
		}
		if (isStudent) {
			System.out.println(find(0, ID).toString()+"\n"); // Student ID
		}
		else {
			System.out.println(find(1, ID).toString()+"\n"); // Faculty ID
		}
	}
	
	/**
	 * Creates a menu by which the user can print a given database
	 * Prints the database sorted by ID
	 */
	public void printMenu() {
		int select;
		while (true) {
			System.out.println("0 = Student\n1 = Faculty\n");
			try {
				select = Integer.parseInt(br.readLine());
				if (select == 0) {
					masterStudent.printTree();
					System.out.println("");
					break;
				}
				else if (select == 1) {
					masterFaculty.printTree();
					System.out.println("");
					break;
				}
				else {
					System.out.println("Invalid input!");
				}
			}
			catch (Exception e) {
				System.out.println("Invalid input!");
				continue;
			}
		}
	}
	
	/**
	 * Creates a main menu by which the user can perform certain actions on the Student or Faculty databases
	 */
	public void mainMenu() {
		int select;
		while (true) {
			System.out.println("~ MAIN MENU ~");
			System.out.println("0 = Quit\n1 = Insert\n2 = Delete\n3 = Modify\n4 = Find\n5 = Rollback\n6 = Print\n");
			try {
				select = Integer.parseInt(br.readLine());
				if (select == 0) {
					saveFile();
					br.close();
					System.exit(0);
				}
				else if (select == 1) {
					insertMenu();
				}
				else if (select == 2) {
					deleteMenu();
				}
				else if (select == 3) {
					modifyMenu();
				}
				else if (select == 4) {
					findMenu();
				}
				else if (select == 5) {
					undo();
				}
				else if (select == 6) {
					printMenu();
				}
				else {
					System.out.println("Invalid input! Line 442");
				}
			}
			catch (Exception e) {
				System.out.println("Invalid input! Line 446: "+e.getMessage());
				continue;
			}
		}
	}
	
	/**
	 * Main method.....
	 * Constructs the database;
	 * Loads the file;
	 * Creates the main menu screen.
	 */
	public static void main(String[] args) {
		DB db = new DB();
		db.loadFile();
		db.mainMenu();
	}
}
