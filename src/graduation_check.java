//these are the imports used in this class file
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//this is the main part of our class
public class graduation_check {
	
	//these are some variables used in the calculation of credits
	static int credit=0;
    static int grad_required_credit=164;
    static int missed_credit;
    
    //this is a method which calculates the credits left for student to graduate
	public static String graduate_check(String studentID) throws IOException {
     //this is the code that reads the file 
	 BufferedReader read = new BufferedReader( new FileReader(studentID+".csv"));
     String line;
     
     //this code uses condition statements to calculate missed credit
		while ((line = read.readLine()) != null) {
	        
	    	 if(line.contains("4")){
	    	 credit=credit+4; 
	        }
	    	 missed_credit=grad_required_credit-credit;
	    }
		if(missed_credit>0){
			//statement is retured if theres missing credit
			return "This student is currently missing " +missed_credit+" to graduate";
		
		}else {
			//statement is retured if credit is satisfied
			return "This student has earned required credit and is elegible to graduate";
			
		}
		
	}
}
