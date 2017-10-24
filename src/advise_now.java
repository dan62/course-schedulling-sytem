

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class advise_now implements math_functions {

	@Override
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
		  check_prequizit(course_info);
	}
	//@Override
	public void check_prequizit(HashMap<String, String> set) throws IOException {
		
		// This is just a message to tell users what the data below represents
		 System.out.println("--------------------------------------------------------------------------");
	     System.out.println("The student is eligible for the following prequizit courses after looking");
	     System.out.println("at his/her transcript:");
	     System.out.println("--------------------------------------------------------------------------");
	     System.out.println("");
		
	    
		Map<String, String> eligible = new HashMap<String, String>();
		String content;
		content = new String(Files.readAllBytes(Paths.get("prequizits.txt")));
		System.out.println(content);
		
	/*	if(set.containsKey("ITE101")){
      	  eligible.put("ITE321","System Analysis,design and Implementation");
      	  eligible.put("ITE240","Principles Of Operating Systems");
      	  
      	  System.out.println("ITE321   System Analysis,design and Implementation");
      	  System.out.println("ITE240   Principles Of Operating Systems");
        }
        if(set.containsKey("ITE321")){
      	  eligible.put("ITE479","IT Planning and Project Managment");
      	  System.out.println("ITE479   IT Planning and Project Managment");
        }
        if(set.containsKey("ITE441")){
      	  eligible.put("ITE442","Database Management Systems");
      	  System.out.println("ITE442   Database Management Systems"); 
        }
        if(set.containsKey("ITE120")){
      	  eligible.put("ITE337","Content Management Systems");
      	  eligible.put("ITE220","Web Development 2");
      	  eligible.put("ITE254","Human Computer Interaction");  
      	  System.out.println("ITE337   Content Management Systems");
      	  System.out.println("ITE220   Web Development 2");
      	  System.out.println("ITE254   Human Computer Interaction");
        }
        if(set.containsKey("ITE221")){
      	  eligible.put("ITE221","Programming 2");
      	  System.out.println("ITE221   Programming 2");
        }
        if(set.containsKey("ITE222")){
      	  eligible.put("ITE342","Application Development-IOS");
      	  eligible.put("ITE343","Application Development-Android");
      	  System.out.println("ITE342   Application Development-IOS"); 
      	  System.out.println("ITE342   Application Development-Android");
        }
        if(set.containsKey("ITE220")){
      	  eligible.put("ITE223","Application Developement");
      	  eligible.put("ITE451","Cloud Computing");
      	  eligible.put("ITE447","Current Topics of Web Technology");
      	  eligible.put("ITE448","Current Topics in Mobile Computing");
      	  System.out.println("ITE233   Application Developement"); 
      	  System.out.println("ITE451   Cloud Computing");
      	  System.out.println("ITE447   Current Topics of Web Technology");
      	  System.out.println("ITE447   Current Topics in Mobile Computing");
        }
        if(set.containsKey("ITE240") && set.containsKey("ITE475")){
      	  eligible.put("ITE420","Information and System Security");
      	  System.out.println("ITE420   Information and System Security");
        }  
        if(eligible.isEmpty()){
        	System.out.println("**Sorry this student does not satisfy any prequizit requirements**");
        }
        
        */
        suggest_courses(eligible);

	}

	@Override
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
		
		

	
