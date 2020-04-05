package Attendance;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class AttendanceTest {
	private Attendance student;
	private HashMap<String, String> database = new HashMap<>();
	@Before
	public void setUp() {
		database.put("1", "Long Duy Le");
		database.put("2", "Mohit Pradhan");
		database.put("3", "Li Hao");
		database.put("4", "Kazuki");
		database.put("5", "Sakura");
		student = new Attendance(database);
	}
	@Test	
	public void testToResult() {
		student.toAdd("1");
		student.toAdd("2");
		assertEquals(student.toResult().contains("Long Duy Le"), true);
		assertEquals(student.toResult().contains("Li Hao"), false);
		student.toAdd("3");
		assertEquals(student.toResult().contains("Li Hao"), true);
	}
	
	@Test
	public void testSkipResult() {
		student.toAdd("1");
		student.toAdd("2");
		assertEquals(student.skipResult().contains("Long Duy Le"), false);
		assertEquals(student.skipResult().contains("Kazuki"), true);
		student.toAdd("4");
		assertEquals(student.skipResult().contains("kazuki"), false);
	}
	
	@Test
	public void testReset() {
		student.toAdd("1");
		assertEquals(student.skipResult().contains("Long Duy Le"), false);
		student.reset();
		assertEquals(student.skipResult().contains("Long Duy Le"), true);
	}
	
}
