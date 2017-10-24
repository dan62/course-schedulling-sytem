import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//we implement math_functions interface
public class advise implements math_functions{
		      

	/*math functions has 3 actions it can perform which are format transcript, check eligible courses and suggest
	courses for next semester */
	@Override
	
	//this method formats the transcript intoe readble data by removing unwanted data
	public void format_transcript(String student_id) throws IOException {
		String line="";
		  HashMap<String, String> course_info = new HashMap<String, String>();
		  BufferedReader read = new BufferedReader( new FileReader(student_id+".csv"));
		  String[] data;
		  
		  System.out.println("--------------------------------------------------------------");
	        System.out.println("The following are courses the student has taken and passed:");
	        System.out.println("--------------------------------------------------------------");
	        System.out.println("");
		  
	      //we now read and format the students transcript using this loop so that we can compare it latter
		  while ((line = read.readLine()) != null) {
				if(line.contains("ITE")){
			    	
			    	data = line.split(" , ");
			        String courseID2 = data[0];
			        String courseName2 = line.substring(line.indexOf(" ")+1);
			        
			        if(line.contains(" , ")){
			        	courseName2 = courseName2.replace(" , ", "   ");
			        }else if(line.contains("\",\"")){
			        	courseName2 = courseName2.replace("\",\"", " ");
			        }
			        
			        if(line.contains(",")){
			        	courseName2 = courseName2.split(",")[0];
			        }
			        if(line.contains("4")){
			        courseName2 = courseName2.split(" 4")[0];
			        }
			        if(line.contains("12")){
			        courseName2 = courseName2.split(" 12")[0];
			        }
			        
			        if(line.contains("W\"")){
			        	courseName2 = "";
			        }
			        
			        if (line.equals("")) // don't write out blank lines
			        {
			        	courseName2 =courseName2.trim();
			        }
			        
			        if(courseName2!=""){
			        String[] splited = courseName2.split("   ");
						
			            courseID2 = splited[0];
						courseName2 = splited[1];
			        
			        //we stored the course data in a hashmap
			       course_info.put(courseID2, courseName2);
			        }
			       
			        System.out.println(courseName2);

	}
		  }
		  System.out.println("");
		  
		  //after data has been collected we pass the course_info hashmap into the check_prequizits method
		  check_prequizit(course_info);
		
	}

	@Override
	//this method checks all available prequizit courses that have been satisfied 
	public void check_prequizit(HashMap<String, String> set) throws IOException {
		HashMap<String, String> subject = new HashMap<String, String>();
		HashMap<String, String> eligible = new HashMap<String, String>();
		HashMap<String, String> sylabus_formatted = new HashMap<String, String>();
		
		     //we read both prequizits and sylabus to get the eligible courses
		      BufferedReader in = new BufferedReader(new FileReader("prequizits.txt"));
		      BufferedReader sylabus = new BufferedReader(new FileReader("sylabus.txt"));
		      String line;
		      String[] pair;
		      String[] split_again;
		      String[] keys = new String[8];
		      int x = 0;
		      while((line = in.readLine())!=null){
		          pair=null;
		          split_again=null;
		    	  pair = line.split("	");
		          String sub = pair[1];
		          subject.put(pair[0], sub);
		          keys[x]=pair[0];
		          x=x+1;
		      }
		   
		      while ((line = sylabus.readLine()) != null) {
		    	  String [] sylabus_format =line.split("	");
		    	  sylabus_formatted.put(sylabus_format[0], sylabus_format[1]);
		      }
		      
		      //message telling user what the data below represents
		      System.out.println("--------------------------------------------------------------------------");
			     System.out.println("The student is eligible for the following prequizit courses after looking");
			     System.out.println("at his/her transcript:");
			     System.out.println("--------------------------------------------------------------------------");
			     System.out.println("");
		      
			  //we then check and add data to the eligible hashmap 
		      for(int p=0; p<keys.length;p++){
		    	  if(set.containsKey(keys[p])){
		    		  String[] split_it = subject.get(keys[p]).split(" , ");
		    		  for (String element : split_it) {
		    			    int spaceIndex = element.indexOf(" ");
		    			    String code = element.substring(0, spaceIndex);
		    			    eligible.put(code, sylabus_formatted.get(code));
		    			    //we display eligible courses by getting the value using the code
		    			   System.out.println(eligible.get(code));
		    			}
			    	  
		    	  }
		      }
		      
		      //if the student isnt eligible for any prequizit courses then a message is displayed
		      if(eligible.isEmpty()){
	    		  System.out.println("The student does not satisfy any prequizit courses,");
	    	      System.out.println("basic courses should first be taken");
		      }
		      //then we pass the eligible hashmap into the next method called suggest_courses
		      suggest_courses(eligible);
	}

	@Override
	//the last method suggests courses for a student
	public void suggest_courses(Map<String, String> eligible) throws IOException {
		BufferedReader course_offering = new BufferedReader( new FileReader("courses to open.txt"));
		
		String line;
	    String[] data;
	    String[] offered=new String[16];
		int b = 0;
	     while ((line = course_offering.readLine()) != null) {
	    	 String[] delims = line.split(" ");
	    	 offered[b] = delims[0];
	    	 b=b+1;
	     }
		
	     
		// This is another message adivising the user what the data below represents
		  System.out.println("");
		  System.out.println("------------------------------------------------------------------");
		  System.out.println("However we suggest the student to take up the following courses: ");
		  System.out.println("------------------------------------------------------------------");
		  System.out.println("");
		  System.out.println("xxxxxx Any General or Free Elective Course offered");
		 
		  // This code catters for new students who have not satisfied any prequizit courses
		  if(eligible.isEmpty()){
			  System.out.println("xxxxxx Any General or Free Elective Course offered");
			  System.out.println("ITE101  Introduction To Information Technology");
			  System.out.println("ITE120  Web Development I");
		  }
		  
		  //This code cross checks the prequizit courses elegible for the student and the
		  //courses offered for the term so that we can advise student on what to take next term
		  int num_courses_assigned=0;
		  for(int z = 0; z<eligible.size();z++){
			  if(eligible.containsKey(offered[z])){
				  System.out.println(eligible.get(offered[z]));
			      num_courses_assigned=num_courses_assigned+1;
			  }
		  }

		
		  
		//this will add extra courses in the case that student can only take 2 prequizit courses or below
				  if(num_courses_assigned<=2 && !eligible.isEmpty()){
					  if(!eligible.containsKey("ITE321") && num_courses_assigned!=3){
						  System.out.println("Introduction to Information Technology");
						  num_courses_assigned=num_courses_assigned+1;
					  };
					  if(!eligible.containsKey("ITE479") && num_courses_assigned!=3){
						  System.out.println("System Analysis,Design and implementation");
						  num_courses_assigned=num_courses_assigned+1;
					  }
					  if(!eligible.containsKey("ITE442") && num_courses_assigned!=3){
						  System.out.println("Fundimentals of Database System");
						  num_courses_assigned=num_courses_assigned+1;
					  }
					  if(!eligible.containsKey("ITE420") && eligible.containsKey("ITE222") && num_courses_assigned!=3){
						  System.out.println("Data Communication and Networking");
						  num_courses_assigned=num_courses_assigned+1;
					  }
					  if(!eligible.containsKey("ITE420") && !eligible.containsKey("ITE221") && num_courses_assigned!=3){
						  System.out.println("Programming I");
						  System.out.println("Data Communication and Networking");
						  num_courses_assigned=num_courses_assigned+1;
					  }
					  if(!eligible.containsKey("ITE233") && eligible.containsKey("ITE220") && num_courses_assigned!=3){
						  System.out.println("Web Development II");
						  num_courses_assigned=num_courses_assigned+1;
					  }
					  if(!eligible.containsKey("ITE222") && num_courses_assigned!=3){
						  System.out.println("Programming I");
						  num_courses_assigned=num_courses_assigned+1;
					  }
					  if(!eligible.containsKey("ITE254") && num_courses_assigned!=3){
						  System.out.println("Web Development I");
						  num_courses_assigned=num_courses_assigned+1;
					  }
					  if(!eligible.containsKey("ITE240") && num_courses_assigned!=3){
						  System.out.println("Introduction to Information Technology");
						  num_courses_assigned=num_courses_assigned+1;
					  }
					  if(!eligible.containsKey("ITE342") && eligible.containsKey("ITE221") && num_courses_assigned!=3){
						  System.out.println("Programming II");
						  num_courses_assigned=num_courses_assigned+1;
					  }  
					  
	  }
		 
        System.out.println("");
		  
		} 
		
	}
	
	


