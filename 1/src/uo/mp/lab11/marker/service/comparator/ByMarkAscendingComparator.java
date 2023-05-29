package uo.mp.lab11.marker.service.comparator;

import java.util.Comparator;

import uo.mp.lab11.marker.model.StudentMark;

public class ByMarkAscendingComparator implements Comparator<StudentMark>{

	@Override
	public int compare(StudentMark o1, StudentMark o2) {
		
		int dif = ((Double)o1.getMark()).compareTo((Double)o2.getMark() );
		
		if( dif == 0 )
			return (o1.getStudentId()).compareTo(o2.getStudentId());
		else
			return dif;
		
	}

}
