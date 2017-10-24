

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public interface math_functions {

    void format_transcript(String student_id) throws IOException;
	void check_prequizit(HashMap<String, String> set) throws IOException;
	void suggest_courses(Map<String, String> eligible) throws IOException;
	
}
