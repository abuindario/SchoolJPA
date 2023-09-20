package ejEscuelaJPA.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ejEscuelaJPA.entities.Student_Subject;

public class Student_SubjectDAO {
	public void insert(EntityManager em, Student_Subject ss) {
		em.persist(ss);
	}
	
	public List<Student_Subject> getStudentSubject(EntityManager em) {
		TypedQuery<Student_Subject> query = em.createQuery("FROM Student_Subject ORDER BY id", Student_Subject.class);
		return query.getResultList();
	}
}
