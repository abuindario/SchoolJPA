package ejEscuelaJPA.BO;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import ejEscuelaJPA.DAO.Student_SubjectDAO;
import ejEscuelaJPA.connection.EntityManagerSingleton;
import ejEscuelaJPA.entities.Student;
import ejEscuelaJPA.entities.Student_Subject;
import ejEscuelaJPA.entities.Subject;

public class Student_SubjectBO {
	public void setStudent(int subjectId, int studentId) {
		EntityManager em = EntityManagerSingleton.getEntityManager();
		em.getTransaction().begin();
		Subject subject = em.find(Subject.class, subjectId);
		Student student = em.find(Student.class, studentId);
		Student_Subject student_subject = new Student_Subject();
		if (student != null) {
			student_subject.setStudent(student);
		} else {
			System.out.println("Enter a valid Student ID");
			throw new NullPointerException();
		}
		if (subject != null) {
			student_subject.setSubject(subject);
		} else {
			System.out.println("Enter a valid Subject ID");
			throw new NullPointerException();
		}

		// Prevent that one student can be added twice to the same subject
		Student_SubjectDAO student_subjectDAO = new Student_SubjectDAO();
		List<Student_Subject> ss = student_subjectDAO.getStudentSubject(em);
		boolean isIn = false;
		Iterator<Student_Subject> it = ss.iterator();
		while (it.hasNext()) {
			Student_Subject stsu = it.next();
			if (stsu.getStudent().equals(student_subject.getStudent())
					&& stsu.getSubject().equals(student_subject.getSubject())) {
				isIn = true;
				System.out
						.println(stsu.getStudent().getName() + " is already in subject " + stsu.getSubject().getName());
			}
		}
		if (!isIn) {
			student_subjectDAO.insert(em, student_subject);
		}

		em.getTransaction().commit();
		em.close();
	}

}
