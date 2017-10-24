//These are the imports we used to check the file path
import java.io.File;

//This is the begining of the class 
public class Update_Cirriculum {
	
	//here we have declared a string to store the ciriculum 
	private static String Cirriculum="sylabus.txt";
	
	//This is a constructor so that the ciriculum path can be input from the main class
    public Update_Cirriculum(String cirriculum) {
		super();
		this.Cirriculum = cirriculum;
	}

   public static String getCirriculum(){
    return Cirriculum;
    }

  //This is the toString method where we process the input and return an output
  @Override
  public String toString() {
	
	//This code checks to see if the file path is valid, if it is not an error will be returned
	File f = new File(Cirriculum);
	if(!f.exists()){
		return "Your file path does not exist on your computer please check and try again!";
	}else{
		return "Your syllabus has been successfully updated to a new syllabus!";
	}
}
}

