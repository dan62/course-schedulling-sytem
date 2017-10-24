import java.io.IOException;
import java.util.Scanner;

public class course_menu {
	
	public static void main(String[] args) throws IOException {
		
		//variables i shall use in this class
		Scanner console = new Scanner (System.in);
	    int option = 0;
	    String student_id;
	    String lecturer_name;
	    String cirriculum_path="";
		
		//welcoming message
		System.out.println("--------------------------------------------");
		System.out.println("Welcome to Stamford Course Scheduling System");
		System.out.println("--------------------------------------------");
		System.out.println("");
		
		//while loop to look through the menu until an option is chosen
		 while (option!=-1) {
			   System.out.println("Please choose an option you would like to proceed with from the list below:");
			   
			   //menu of what users can do on my system
		       System.out.println("[1] Update I.T Cirriculum");
		       System.out.println("[2] View Courses to be opened");
			   System.out.println("[3] Advise Student"); 
			   System.out.println("[4] Assign Courses To Lecturers");
			   System.out.println("[5] Map old students to new syllabus");
			   System.out.println("[6] Graduation check");
			   System.out.println("If you would like to exit type (-1):");
			  
			   //assigning a variable to input
			   option = console.nextInt();
			   console.nextLine();
			   
			   //switch option to check option selection
			   switch (option){
			   
			   case 1:
				   //we created an opject then passed the new cirriculum path through it
				   System.out.println("Please type in the file path of the new cirriculum:");
					 cirriculum_path = console.nextLine();
					 Update_Cirriculum update;
					 update = new Update_Cirriculum(cirriculum_path);
					 System.out.println(update);
					 System.out.println("");   
			   break;
			   
			   case 2:
				   //we called a method in the courses to open class 
				   Courses_To_Open_testing.check_open();
				   System.out.println("");  
			   break;
			   
			   case 3:
				   //we create an object and pass a value through it
				   System.out.println("Please enter student ID to start advising:");					 student_id = console.next();  
				    advise advise = new advise(); 
					 advise.format_transcript(student_id);
					 System.out.println("");  
			   break;
			   
			   case 4:
				   //we created an opject and passed the variable as parameters
				   System.out.println("Please enter Lecturer Name to start assigning courses:");
				   lecturer_name = console.next();
				   faculty_member f1 = new faculty_member(lecturer_name);
				   f1.setname(lecturer_name);
				   System.out.println(f1);
				   System.out.println("");  
			   break;
			   
			   case 5:
				   //we created an object called map and passed a varriable through it
				   System.out.println("Please enter Student ID of student still enrolled in old sylabus:");
				   student_id = console.next();
				   map_students map;
				   map = new map_students(student_id);
				   System.out.println(map);
			
			   break;
			   
			   case 6:
				   //we called a method within the graduation check class and passed student id through it
				   System.out.println("Please enter the student id you would like do a graduation check on:");
	        	   student_id = console.next();
	        	   System.out.println(graduation_check.graduate_check(student_id));
	        	   System.out.println("");  
			   break;
			   }
		 }
		        //goodbye message
		        System.out.println("Thank you! Have a nice day.");
		   }
}
	
//

