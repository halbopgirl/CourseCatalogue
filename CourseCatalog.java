package courseCatalogue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class CourseCatalog {
	static File thisFile = new File("courses.dat");
	static int count = 5;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Course psyc300 = new Course(001, "PSYC", 300, "Research Methods in Psychology", " A survey of research " + 
				"methods focusing on the fundamentals of research design " + 
				"and behavior. The aim is to apply research methodologies " + 
				"critically and creatively to communicate effectively about the " + 
				"domains of psychology. Topics include scientific writing using " + 
				"APA style, evaluation of research literature, and ethical issues " + 
				"in research. Practice is provided in asking research questions, " + 
				"formulating research hypotheses, designing and conducting " + 
				"a simulated research study, and presenting results.");
		Course nsci170 = new Course(002, "NSCI", 170, "Concepts of Meteorology", " An introduction to the basic " + 
				"principles of atmospheric science. The goal is to use scientific " + 
				"and quantitative reasoning to make informed decisions about " + 
				"topics related to atmospheric science. Topics include the effect " + 
				"of different weather elements (such as temperature, pressure, " + 
				"winds, and humidity) on weather patterns and climate. Discussion also covers weather phenomena " +
				"such as El Niño, thunderstorms, tornadoes, tropical cyclones, and midaltitude cyclones, " + 
				"as well as the impact of humans on Earth’s atmosphere.");
		Course math140 = new Course(003, "MATH", 140, "Calculus I", " An introduction to " + 
				"calculus. The goal is to demonstrate fluency in the language of " + 
				"calculus; discuss mathematical ideas appropriately; and solve " + 
				"problems by identifying, representing, and modeling functional " + 
				"relationships. Topics include functions, the sketching of graphs " + 
				"of functions, limits, continuity, derivatives and applications of " + 
				"the derivative, definite and indefinite integrals, and calculation " + 
				"of area.");
		Course mrkt310 = new Course(004, "MRKT", 310, "Marketing Principles", "A foundation in the principles of marketing used to manage " + 
				"profitable customer relationships. The objective is to understand the pivotal role of marketing within both an organization’s " +
				"strategic plan and the marketing process and determine " + 
				"marketing strategies and tactics. Topics include consumer " + 
				"behavior, competitive analysis, segmentation, target marketing, " + 
				"positioning, branding, new product development, pricing, value " + 
				"chains, and marketing communications.");
		Course humn344 = new Course(005, "HUMN", 344, "Technology and Culture", "An overview of the impact of technology on culture. " +
				"The goal is to interpret, evaluate, and respond " + 
				"to the role of technology in daily life. Topics include the nature " + 
				"of technology; how technology influences events; how events " + 
				"influence the development of technology; and the interaction " + 
				"between technology and human welfare in medicine, warfare, " + 
				"daily life, entertainment, government, and science.");
		
		ArrayList<Course> courses = new ArrayList<Course>();
		courses.add(psyc300);
		courses.add(nsci170);
		courses.add(mrkt310);
		courses.add(humn344);
		writeCourses(courses);
		

			Scanner scanIn = new Scanner(System.in);
			System.out.println("What would you like to do? 1. Find Course 2. Delete Course 3. Add Course 4. Exit");
			int i = 0;
			while (scanIn.hasNextInt() && i != 4) {
				i = scanIn.nextInt();
				if (i == 4) {
					break;
				}
				input(scanIn, i);
				System.out.println("What would you like to do? 1. Find Course 2. Delete Course 3. Add Course 4. Exit");
			}
			scanIn.close();
	}
	
	private static void input(Scanner scanIn, int i) throws FileNotFoundException, ClassNotFoundException, IOException {
			switch (i) {
			case 1:
				search();
				break;
			case 2:
				delete();
				break;
			case 3: 
				add();
				break;
			default:
				//do nothing
				break;
			}
	}
	
	private static void search() throws FileNotFoundException, ClassNotFoundException, IOException {
		ArrayList<Course> courseList = retrieveCourses();
		System.out.println("What would you like to search by? 1.Class Name 2. Class type 3. Class Number");
		Scanner scanIn = new Scanner(System.in);
		if (scanIn.hasNextInt()) {
			int i = scanIn.nextInt();
			switch (i) {
			case 1:
				System.out.println("Enter class name: ");
				String className = scanIn.next();
				System.out.println(searchByName(className, courseList));
				break;
			case 2:
				System.out.println("Enter class type: ");
				String classType = scanIn.next();
				System.out.println(searchByType(classType, courseList));
				break;
			case 3: 
				System.out.println("Enter class number: ");
				int j;
				if (scanIn.hasNextInt()) {
				j = scanIn.nextInt();
				} else {
					j = 0;
				}
				System.out.println(searchByNumber(j, courseList));
				break;
			}
		} else {
			System.out.println("Not a valid integer, exiting.");
		}
		//scanIn.close();
	}
	
	private static void delete() throws FileNotFoundException, ClassNotFoundException, IOException {
		ArrayList<Course> courseList = retrieveCourses();
		System.out.println("What would you like to delete by? 1.Class Name 2. Class type 3. Class Number");
		Scanner scanIn = new Scanner(System.in);
		if (scanIn.hasNextInt()) {
			int i = scanIn.nextInt();
			switch (i) {
			case 1:
				System.out.println("Enter class name: ");
				String className = scanIn.next();
				System.out.println(deleteByName(className, courseList));
				break;
			case 2:
				System.out.println("Enter class type: ");
				String classType = scanIn.next();
				System.out.println(deleteByType(classType, courseList));
				break;
			case 3: 
				System.out.println("Enter class number: ");
				int j;
				if (scanIn.hasNextInt()) {
				j = scanIn.nextInt();
				} else {
					j = 0;
				}
				System.out.println(deleteByNumber(j, courseList));
				break;
			}
		} else {
			System.out.println("Not a valid integer, exiting.");
		}
		//scanIn.close();
	}
	
	private static void add() throws FileNotFoundException, ClassNotFoundException, IOException {
		int courseID;
		String subject;
		int number;
		String name;
		String description;
		ArrayList<Course> courseList = retrieveCourses();
		System.out.println("What is the course ID?");
		Scanner scanIn = new Scanner(System.in);
		try {
			courseID = Integer.parseInt(scanIn.nextLine());
		} catch (Exception e) {
			courseID = count;
		}
		count++;
		System.out.println("What is the subject?");
		subject = scanIn.nextLine();
		System.out.println("What is the number?");
		try {
			number = Integer.parseInt(scanIn.nextLine());
		} catch (Exception e) {
			number = 100;
		}
		System.out.println("What is the course name?");
		name = scanIn.nextLine();
		System.out.println("What is the course description?");
		description = scanIn.nextLine();
		Course newCourse = new Course(courseID, subject, number, name, description);
		courseList.add(newCourse);
		System.out.println(newCourse.toString() + " has been added.");
		writeCourses(courseList);
		//scanIn.close();
	}
	
	private static void writeCourses(ArrayList<Course> courses) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(thisFile);
		ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
		objectOut.writeObject(courses);
		fileOut.close();
		objectOut.close();
	}
	
	private static ArrayList<Course> retrieveCourses() throws FileNotFoundException, IOException, ClassNotFoundException{
		FileInputStream fileIn = new FileInputStream(thisFile);
		ObjectInputStream objectIn = new ObjectInputStream(fileIn);
		try {
		ArrayList<Course> retrievedCourses = (ArrayList<Course>) objectIn.readObject();
		fileIn.close();
		objectIn.close();
		return retrievedCourses;
		} catch (Exception e){
			fileIn.close();
			objectIn.close();
			System.out.println("No ArrayList Found");
		}
		return new ArrayList<Course>();
	}
	
	private static String searchByType(String type, ArrayList<Course> courses) {
		String outString = "";
		for (Course course : courses) {
			if (course.getSubject().equalsIgnoreCase(type)) {
				outString += course.toString();
				outString += "\r\n";
			}
		}
		return outString;
	}
	
	private static String searchByNumber(int number, ArrayList<Course> courses) {
		String outString = "";
		for (Course course : courses) {
			if (course.getNumber() == number) {
				outString += course.toString();
				outString += "\r\n";
			}
		}
		return outString;
	}

	private static String searchByName(String name, ArrayList<Course> courses) {
		String outString = "";
		for (Course course : courses) {
			if (course.getName().contains(name)) {
				outString += course.toString();
				outString += "\r\n";
			}
		}
		return outString;
	}
	
	private static String deleteByType(String type, ArrayList<Course> courses) {
		String outString = "";
		Iterator<Course> iter = courses.iterator();
		while (iter.hasNext()) {
		    Course thisCourse = iter.next();

		    if (thisCourse.getSubject().equalsIgnoreCase(type)) {
		    	outString += thisCourse.toString();
		        iter.remove();
		        outString += " has been removed.";
				outString += "\r\n";
		    }
		}
		return outString;
	}
	
	private static String deleteByNumber(int number, ArrayList<Course> courses) {
		String outString = "";
		Iterator<Course> iter = courses.iterator();
		while (iter.hasNext()) {
		    Course thisCourse = iter.next();
		    if (thisCourse.getNumber() == number) {
		    	outString += thisCourse.toString();
		        iter.remove();
		        outString += " has been removed.";
				outString += "\r\n";
		    }
		}
		return outString;
	}

	private static String deleteByName(String name, ArrayList<Course> courses) {
		String outString = "";
		Iterator<Course> iter = courses.iterator();
		while (iter.hasNext()) {
		    Course thisCourse = iter.next();

		    if (thisCourse.getName().contains(name)) {
		    	outString += thisCourse.toString();
		        iter.remove();
		        outString += " has been removed.";
				outString += "\r\n";
		    }
		}
		return outString;
	}
}
