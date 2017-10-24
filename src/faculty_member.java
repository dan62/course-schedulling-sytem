import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class faculty_member {
	
	//declaring variables 
	private String name;
	private String course;
	private int count_course;

	//constructor 
	faculty_member(String name){
		super();
		this.name=name;
	}

	public void setname(String name){
		this.name=name;
	}
	public String getname(){
		return this.name;
	}

	@Override
	//toString to return formatted data
	public String toString() {
	   System.out.println("Please enter the course you would like the lecturer to teach:");
	   Scanner console = new Scanner (System.in);
	   course = console.next();
		
		Scanner input1;
		Scanner input2;
		String []courses_offered;
		courses_offered = new String[15];
		File file_name = new File("assigned_courses.txt");
		File courses = new File("courses to open.txt");
		Map<String, String> assigned_courses = new HashMap<String, String>();
		
		
		try {
			input1 = new Scanner(courses);
			input2 = new Scanner(file_name);
			int course_locate = 0;
			
			while (input1.hasNextLine()) {
				   String line = input1.nextLine();
				   courses_offered[course_locate] = line;
				  
				   course_locate=course_locate+1;
			}
			
			if(assigned_courses.containsKey(name) || (assigned_courses.containsValue(course) )){
				return "Sorry this course has already been assigned to Ajarn "+name;
			}
			
			while (input2.hasNextLine()) {
			String line = input2.nextLine();
			course_locate=0;
			
				
				if(!line.contains(courses_offered[course_locate])){
					return "Sorry the course you want to assign is not being offered next term";
				}
				assigned_courses.put(name, course);
				course_locate=course_locate+1;
			}
			
			
			Writer wr = new FileWriter("assigned_courses.txt");
			wr.write(name+" "+course); 
			wr.write("\r\n");
			wr.flush();
			wr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return "Ajarn "+name+" has been assigned to teach "+course;
	}
}


