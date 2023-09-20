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
import ejEscuelaJPA.entities.Student_Subject;
import ejEscuelaJPA.entities.Subject;
import ejEscuelaJPA.entities.Teacher;

public class Main {
	public static TeacherBO teacherBO = new TeacherBO();
	public static StudentBO studentBO = new StudentBO();
	public static SubjectBO subjectBO = new SubjectBO();
	public static Student_SubjectBO student_subjectBO = new Student_SubjectBO();

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
		String teacherName = "";
		do {
			System.out.println("Enter teacher name: ");
			teacherName = br.readLine();
			if (teacherName.trim().equals("")) {
				System.out.println("Please, enter a valid name");
			}
		} while (teacherName.trim().equals(""));
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
			System.out.println("\t" + teacher.getId() + ". " + teacher.getName() + "\n\t\tSubjects:");
			List<Subject> subjects = teacher.getSubjects();
			Iterator<Subject> it = subjects.iterator();
			while (it.hasNext()) {
				Subject subject = it.next();
				System.out.println("\t\t" + subject.getId() + ". " + subject.getName());
			}
		}
	}

	public static void updateTeacher() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter 'ID' from teacher to update: ");
		getTeacherList();
		int teacherId = Integer.parseInt(br.readLine());
		String teacherName = "";
		do {
			System.out.println("Enter teacher name: ");
			teacherName = br.readLine();
			if (teacherName.trim().equals("")) {
				System.out.println("Please, enter a valid name");
			}
		} while (teacherName.trim().equals(""));
		try {
			teacherBO.update(teacherId, teacherName);
		} catch (NullPointerException e) {
			System.out.println("Invalid option, enter a valid Teacher ID");
		}
	}

	public static void deleteTeacher() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter 'ID' from teacher to remove: ");
		getTeacherList();
		int teacherId = Integer.parseInt(br.readLine());
		try {
			teacherBO.delete(teacherId);
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid option, enter a valid Teacher ID");
		}
	}

	public static void createStudent() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String studentName = "";
		do {
			System.out.println("Enter student name: ");
			studentName = br.readLine();
			if (studentName.trim().equals("")) {
				System.out.println("Please, enter a valid name");
			}
		} while (studentName.trim().equals(""));
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
			System.out.println("\t" + student.getId() + ". " + student.getName() + ", absences: " + student.getAbsence()
					+ "\n\t\tSubjects:");
			List<Student_Subject> studentSubjects = student.getSubjects();
			Iterator<Student_Subject> it = studentSubjects.iterator();
			while (it.hasNext()) {
				Subject subject = it.next().getSubject();
				System.out.println("\t\t" + subject.getId() + ". " + subject.getName());
			}
		}
	}

	public static void updateStudent() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter 'ID' from student to update: ");
		getStudentList();
		int studentId = Integer.parseInt(br.readLine());
		String studentName = "";
		do {
			System.out.println("Enter student name: ");
			studentName = br.readLine();
			if (studentName.trim().equals("")) {
				System.out.println("Please, enter a valid name");
			}
		} while (studentName.trim().equals(""));
		try {
			studentBO.update(studentId, studentName);
		} catch (NullPointerException e) {
			System.out.println("Invalid option, enter a valid Student ID");
		}
	}

	public static void deleteStudent() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter 'ID' from student to remove: ");
		getStudentList();
		int studentId = Integer.parseInt(br.readLine());
		try {
			studentBO.delete(studentId);
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid option, enter a valid Teacher ID");
		}
	}

	public static void createSubject() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String subjectName = "";
		do {
			System.out.println("Enter subject name: ");
			subjectName = br.readLine();
			if (subjectName.trim().equals("")) {
				System.out.println("Please, enter a valid name");
			}
		} while (subjectName.trim().equals(""));
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
			System.out.println("\t" + subject.getId() + ". " + subject.getName() + ", Teacher: " + teacherName
					+ "\n\t\t Students:");
			List<Student_Subject> studentSubject = subject.getStudents();
			Iterator<Student_Subject> it = studentSubject.iterator();
			while (it.hasNext()) {
				Student student = it.next().getStudent();
				System.out.println("\t\t" + student.getId() + ". " + student.getName());
			}
		}
	}

	public static void updateSubject() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter 'ID' from subject to update: ");
		getSubjectList();
		int subjectId = Integer.parseInt(br.readLine());
		String subjectName = "";
		do {
			System.out.println("Enter subject name: ");
			subjectName = br.readLine();
			if (subjectName.trim().equals("")) {
				System.out.println("Please, enter a valid name");
			}
		} while (subjectName.trim().equals(""));
		try {
			subjectBO.update(subjectId, subjectName);
		} catch (NullPointerException e) {
			System.out.println("Invalid option, enter a valid Subject ID");
		}
	}

	public static void deleteSubject() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter 'ID' from subject to remove: ");
		getSubjectList();
		int subjectId = Integer.parseInt(br.readLine());
		try {
			subjectBO.delete(subjectId);
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid option, enter a valid Subject ID");
		}
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
		try {
			student_subjectBO.setStudent(subjectId, studentId);
		} catch (NullPointerException e) {
		}
	}

}
