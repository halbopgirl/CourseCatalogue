package courseCatalogue;

import java.io.Serializable;

public class Course implements Serializable{
	int courseID;
	String subject;
	int number;
	String name;
	String description;
	
	Course(int courseID, String subject, int number, String name, String description) {
		this.courseID = courseID;
		this.subject = subject;
		this.number = number;
		this.name = name;
		this.description = description;
	}
	
	public int getCourseID() {
		return courseID;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public int getNumber() {
		return number;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String toString() {
		return("Course number " + number + ", which is " + subject + number + " - " + name);
	}
}
