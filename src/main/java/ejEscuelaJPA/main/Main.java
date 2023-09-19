package ejEscuelaJPA.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import ejEscuelaJPA.BO.StudentBO;
import ejEscuelaJPA.BO.Student_SubjectBO;
import ejEscuelaJPA.BO.SubjectBO;
import ejEscuelaJPA.BO.TeacherBO;
import ejEscuelaJPA.entities.Student;
import ejEscuelaJPA.entities.Subject;
import ejEscuelaJPA.entities.Teacher;

public class Main {
	public static TeacherBO teacherBO = new TeacherBO();
	public static StudentBO studentBO = new StudentBO();
	public static SubjectBO subjectBO = new SubjectBO();
	public static Student_SubjectBO student_subjectBO =  new Student_SubjectBO();

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
				System.out.println("13. Set teacher to a subject");
				System.out.println("14. Add student to a subject");
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
				case 9:
					createSubject();
					break;
				case 10:
					getSubjectList();
					break;
				case 11:
					updateSubject();
					break;
				case 12:
					deleteSubject();
					break;
				case 13:
					setTeacher();
					break;
				case 14:
					setStudent();
					break;
				default:
					System.out.println("Invalid option");
					break;
				}
			} catch (NumberFormatException | IOException e) {
				System.out.println("You must enter a number");
			}
		} while (opt != 0);
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
		while (teacherIterator.hasNext()) {
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
		while (studentIterator.hasNext()) {
			Student student = studentIterator.next();
			System.out
					.println("\t" + student.getId() + ". " + student.getName() + ", absences: " + student.getAbsence());
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

	public static void createSubject() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter subject name:");
		String subjectName = br.readLine();
		Subject subject = new Subject();
		subject.setName(subjectName);
		subjectBO.insert(subject);
	}

	public static void getSubjectList() {
		List<Subject> subjects = subjectBO.getSubjects();
		Iterator<Subject> subjectIterator = subjects.iterator();
		System.out.println("Subject list:");
		while (subjectIterator.hasNext()) {
			Subject subject = subjectIterator.next();
			Teacher teacher = subject.getTeacher();
			String teacherName = "";
			try {
				teacherName = teacher.getName();
			} catch (NullPointerException e) {
				teacherName = "'none'";
			}
			System.out.println("\t" + subject.getId() + ". " + subject.getName() + ", Teacher: " + teacherName);
		}
	}

	public static void updateSubject() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter 'ID' from subject to update: ");
		getSubjectList();
		int subjectId = Integer.parseInt(br.readLine());
		System.out.println("Enter subject name: ");
		String subjectName = br.readLine();
		subjectBO.update(subjectId, subjectName);
	}

	public static void deleteSubject() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter 'ID' from subject to remove: ");
		getSubjectList();
		int subjectId = Integer.parseInt(br.readLine());
		subjectBO.delete(subjectId);
	}

	public static void setTeacher() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter 'ID' from subject to set teacher: ");
		getSubjectList();
		int subjectId = Integer.parseInt(br.readLine());
		System.out.println("Enter 'ID' from the teacher of the subject: ");
		getTeacherList();
		int teacherId = Integer.parseInt(br.readLine());
		subjectBO.setTeacher(subjectId, teacherId);
	}
	
	public static void setStudent() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter 'ID' from subject to set student: ");
		getSubjectList();
		int subjectId = Integer.parseInt(br.readLine());
		System.out.println("Enter 'ID' from student of the subject: ");
		getStudentList();
		int studentId = Integer.parseInt(br.readLine());
		student_subjectBO.setStudent(subjectId, studentId);
	}
}
