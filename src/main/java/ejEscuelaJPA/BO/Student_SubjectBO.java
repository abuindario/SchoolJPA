package ejEscuelaJPA.BO;

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
		student_subject.setStudent(student);
		student_subject.setSubject(subject);
		Student_SubjectDAO student_subjectDAO = new Student_SubjectDAO();
		student_subjectDAO.insert(em, student_subject);
		em.getTransaction().commit();
		em.close();
	}
}
