package ejEscuelaJPA.BO;

import java.util.List;

import javax.persistence.EntityManager;

import ejEscuelaJPA.DAO.SubjectDAO;
import ejEscuelaJPA.connection.EntityManagerSingleton;
import ejEscuelaJPA.entities.Subject;
import ejEscuelaJPA.entities.Teacher;

public class SubjectBO {
	public void insert(Subject subject) {
		EntityManager em = EntityManagerSingleton.getEntityManager();
		em.getTransaction().begin();
		SubjectDAO subjectDAO = new SubjectDAO();
		subjectDAO.insert(em, subject);
		em.getTransaction().commit();
		em.close();
	}

	public List<Subject> getSubjects() {
		EntityManager em = EntityManagerSingleton.getEntityManager();
		SubjectDAO subjectDAO = new SubjectDAO();
		List<Subject> subjectList = subjectDAO.getSubjects(em);
		em.close();
		return subjectList;
	}

	public void update(int subjectId, String subjectName) {
		EntityManager em = EntityManagerSingleton.getEntityManager();
		em.getTransaction().begin();
		SubjectDAO subjectDAO = new SubjectDAO();
		Subject subject = em.find(Subject.class, subjectId);
		subject.setName(subjectName);
		subjectDAO.update(em, subject);
		em.getTransaction().commit();
		em.close();
	}

	public void delete(int subjectId) {
		EntityManager em = EntityManagerSingleton.getEntityManager();
		em.getTransaction().begin();
		SubjectDAO subjectDAO = new SubjectDAO();
		Subject subject = em.find(Subject.class, subjectId);
		subjectDAO.delete(em, subject);
		em.getTransaction().commit();
		em.close();
	}

	public void setTeacher(int subjectId, int teacherId) {
		EntityManager em = EntityManagerSingleton.getEntityManager();
		em.getTransaction().begin();
		SubjectDAO subjectDAO = new SubjectDAO();
		Subject subject = null;
		subject = em.find(Subject.class, subjectId);
		Teacher teacher = em.find(Teacher.class, teacherId);
		try {
		if(teacher.equals(null)) {
			throw new NullPointerException();
		}
		} catch (NullPointerException e) {
			System.out.println("Enter a valid Teacher ID");
		}
		try {
			subject.setTeacher(teacher);
			subjectDAO.setTeacher(em, subject);
		} catch (NullPointerException e) {
			System.out.println("Enter a valid Subject ID");
		}
		em.getTransaction().commit();
		em.close();
	}

}
