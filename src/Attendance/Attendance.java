package Attendance;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

interface classAttendance{
	public void toAdd(String ID); //check in student
	public String allResult();//print out result in string 
	public String toResult();//print out attendance student
	
	public String skipResult(); //print out no attendance student
	public void updateDataBase(String ID, String student) throws Exception; // original database for double check
}

public class Attendance implements classAttendance{
	
	private static final Exception DuplicateKeyException = null;
	private HashMap<String, String> map = new HashMap<>();
	private Set<String> swipe = new HashSet<>();
	
	public Attendance(HashMap<String, String> dataBase) {
		this.map = dataBase;
	}
	
	public void toAdd(String ID) {
		swipe.add(ID);
	}
	
	public String allResult() {
		
		return toResult() + "\n" + skipResult();
	}
	
	public String toResult() {
		StringBuilder toClass = new StringBuilder(); 
		toClass.append("Students who come to class: \n");
		for(String x : map.keySet()) {
			if(swipe.contains(x)) {
				toClass.append(map.get(x) + " \n");
			}
		}
		
		String result = toClass.toString();
		return result;
	}
	
	public String skipResult() { 
		StringBuilder notToClass = new StringBuilder();
		notToClass.append("Students who doesnt come to class: \n");
		for(String x : map.keySet()) {
			if(!swipe.contains(x)) {
				notToClass.append(map.get(x) + " \n");
			}
		}
		
		String result =notToClass.toString();
		return result;
	}
	
	public void updateDataBase(String ID, String student) throws Exception {
		if(map.containsKey(ID)) {
			throw DuplicateKeyException;
		} else {
			map.put(ID, student);
		}
	}
	public void reset() {
		for(String ID: swipe) {
			swipe.remove(ID);
		}
	}
}
