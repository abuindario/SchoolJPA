package ejEscuelaJPA.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import ejEscuelaJPA.BO.StudentBO;
import ejEscuelaJPA.BO.TeacherBO;
import ejEscuelaJPA.entities.Student;
import ejEscuelaJPA.entities.Teacher;

public class Main {
	public static TeacherBO teacherBO = new TeacherBO();
	public static StudentBO studentBO = new StudentBO();

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int opt = 99;
		do {
		try {
			System.out.println("\n======= MENU =======");
			System.out.println("0. Exit");
			System.out.println("1. Create Teacher");
			System.out.println("2. Get Teacher list");
			System.out.println("3. Update Teacher");
			System.out.println("4. Delete Teacher");
			System.out.println("5. Create Student");
			System.out.println("6. Get Student list");
			System.out.println("7. Update Student");
			System.out.println("8. Delete Student");
			System.out.println("9. Create Subject");
			System.out.println("10. Get Subject list");
			System.out.println("11. Update Subject");
			System.out.println("12. Delete Subject");
			opt = Integer.parseInt(br.readLine());
			switch (opt) {
			case 0: 
				break;
			case 1:
				createTeacher();
				break;
			case 2:
				getTeacherList();
				break;
			case 3:
				updateTeacher();
				break;
			case 4:
				deleteTeacher();
				break;
			case 5:
				createStudent();
				break;
			case 6: 
				getStudentList();
				break;
			case 7:
				updateStudent();
				break;
			case 8: 
				deleteStudent();
				break;
			default:
				System.out.println("Invalid option");
				break;
			}
		} catch (NumberFormatException | IOException e) {
			System.out.println("You must enter a number");
		}
		} while(opt!=0);
	}
	
	public static void createTeacher() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter teacher name:");
		String teacherName = br.readLine();
		Teacher teacher = new Teacher();
		teacher.setName(teacherName);
		teacherBO.insert(teacher);
	}
	
	public static void getTeacherList() {
		List<Teacher> teachers = teacherBO.getTeachers();
		Iterator<Teacher> teacherIterator = teachers.iterator();
		System.out.println("Teacher list:");
		while(teacherIterator.hasNext()) {
			Teacher teacher = teacherIterator.next();
			System.out.println("\t" + teacher.getId() + ". " + teacher.getName());
		}
	}
	
	public static void updateTeacher() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter 'ID' from teacher to update: ");
		getTeacherList();
		int teacherId = Integer.parseInt(br.readLine());
		System.out.println("Enter teacher name: ");
		String teacherName = br.readLine();
		teacherBO.update(teacherId, teacherName);
	}
	
	public static void deleteTeacher() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter 'ID' from teacher to remove: ");
		getTeacherList();
		int teacherId = Integer.parseInt(br.readLine());
		teacherBO.delete(teacherId);
	}
	
	public static void createStudent() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter student name:");
		String studentName = br.readLine();
		Student student = new Student();
		student.setName(studentName);
		studentBO.insert(student);
	}
	
	public static void getStudentList() {
		List<Student> students = studentBO.getStudents();
		Iterator<Student> studentIterator = students.iterator();
		System.out.println("Student list:");
		while(studentIterator.hasNext()) {
			Student student = studentIterator.next();
			System.out.println("\t" + student.getId() + ". " + student.getName());
		}
	}
	
	public static void updateStudent() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter 'ID' from student to update: ");
		getStudentList();
		int studentId = Integer.parseInt(br.readLine());
		System.out.println("Enter student name: ");
		String studentName = br.readLine();
		studentBO.update(studentId, studentName);
	}
	
	public static void deleteStudent() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter 'ID' from student to remove: ");
		getStudentList();
		int studentId = Integer.parseInt(br.readLine());
		studentBO.delete(studentId);
	}
}
