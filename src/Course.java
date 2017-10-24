
//Reprsents a course
public class Course implements Comparable<Course> {

	// course details
	private String code;
	private String courseName;
	private int count;

	// create course with specified details
	public Course(String code, String courseName) {
		this.code = code;
		this.courseName = courseName;
		count = 0;
	}

	// increase count
	public void incrementCount() {
		count++;
	}
	
	// getters

	public String getCode() {
		return code;
	}

	public String getCourseName() {
		return courseName;
	}

	public int getCount() {
		return count;
	}


	//compare this course with other course
	@Override
	public int compareTo(Course o) {
		//compare counts
		int compare = this.count - o.count;

		//if same, compare codes using alphabetic comparison
		if (compare == 0)
			compare = this.code.compareTo(o.code);

		return compare;
	}

}
