package uo.mp.lab11.marker.service.serializers;

import java.util.ArrayList;
import java.util.List;

import uo.mp.lab11.marker.model.StudentMark;
import uo.mp.util.check.ArgumentChecks;

public class StudentMarkSerializer {

	
	public List<String> serialize(List<StudentMark> marks){
		ArgumentChecks.isTrue(marks != null);
		
		List<String> lines = new ArrayList<String>();
		
		for(StudentMark mark: marks) {
			lines.add(mark.serialize());
		}
		
		return lines;
	}
}
