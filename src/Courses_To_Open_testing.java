import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class Courses_To_Open_testing {

	//to hold courses
	private static HashMap<String, Course> courses = new HashMap<>();

	
	//read sylabus file
	private static void readCoursesFromSylabus(String path) throws IOException {
		// to read file
		Scanner scanner = new Scanner(new File(path));
		
		while (scanner.hasNextLine()) {
			//read line
			String line = scanner.nextLine();

			//break line using tab into code and name
			int index = line.indexOf("\t");
			String code = line.substring(0, index);
			String name = line.substring(index + 1);
			
			//add to map
			courses.put(code, new Course(code, name));
		}

		scanner.close();
	}

	
	//count courses
	private static void countCourses(String path) throws FileNotFoundException {

		File[] files = new File(path).listFiles();

		//for each file
		for (File file : files) {
			
			//for each file thjat is csv
			if (file.isFile() && file.getName().endsWith(".csv")) {
				Scanner scanner = new Scanner(file);

				while (scanner.hasNextLine()) {
					//read line
					String line = scanner.nextLine();
					
					//split columns
					String[] columns = line.split(",");
					if (columns.length >= 12) { //if course details
						
						//read code and status
						String courseCode = columns[7].replaceAll("\"", "").trim();
						String status = columns[columns.length - 1].replaceAll("\"", "").trim();
						
						//if header line, or no code, or status is W or F or empty, dont do anything
						if(courseCode.equalsIgnoreCase("COURSECODE1") 
								|| courseCode.isEmpty() 
								|| status.equalsIgnoreCase("W") 
								|| status.equalsIgnoreCase("F")
								|| status.isEmpty())
							continue;
						
						//get course
						Course course = courses.get(courseCode);
						
						//if found
						if(course != null)
							course.incrementCount();
					}
				}
				
				scanner.close();

			}
		}
	}

	public static void check_open() throws IOException {
		//read files
		readCoursesFromSylabus("./sylabus.txt");
		countCourses("./");
		
		//creates set that is sorted based on the count (coamparison method defined in course class)
		SortedSet<Course> coursesSet = new TreeSet<>(courses.values());
		
		
		//print first 15 courses
		int i = 0;
		
		  System.out.println("----------------------------------------------------------------------");
		  System.out.println(" The following are the courses that should get opened next semester:");
		  System.out.println("----------------------------------------------------------------------");
		for (Course course : coursesSet) {
		    i++;
			System.out.println(course.getCode()+"	"+course.getCourseName()+" ");
			//+course.getCount());
			if(i == 15)
				break;
		}
		System.out.println("----------------------------------------------------------------------");
	}
}
