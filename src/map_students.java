//These are all the imports used in our class
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//This is our main class
public class map_students {
	
	//The following is a variable 
	private String student_id;
	
	//This is a constructor used to set and get the student id
	map_students(String student_id){
		super();
		this.student_id=student_id;
	}
		
	public void setstudent_id(String student_id){
		this.student_id=student_id;
	}
	public String getstudent_id(){
		return this.student_id;
	}
	
	//This is a toString method to return maped student subjects
	@Override
	public String toString() {

		//here we read the file in a try catch block so that we catch any exceptions
		BufferedReader read;
		try {
			//the file name contains student id since the name f the file is the student id
			read = new BufferedReader( new FileReader(student_id+".csv"));
		
	    //these are some variables which have been declared
        String line;
        Boolean check=false;
		
        //This is a small message to tell the user what type of data that they are seeing
        System.out.println("----------------------------------------------------------*");
		System.out.println("       The following courses have been substituted:       |");
		System.out.println("----------------------------------------------------------|");
		System.out.println("                                                          |");
        
		//This is a loop with conditional statements to check for subjects which can be substituted
        while ((line = read.readLine()) != null) {
  	        
        	if(line.contains("ITE235")){
				System.out.println("ITE235 Mobile Application Architecture has substituted    |");
				System.out.println("for ITE240 Principles of Operating Systems                |");
				check=true;
			} 
        }
        //this logical statement checks if there is no need for substitution then it returns a statement
        if(check==false){
        	 System.out.println("The student does not require any course substitution      |"); 	
        }
        System.out.println("                                                          |");
        System.out.println("----------------------------------------------------------*");
 		
        read.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
}
