package ejEscuelaJPA.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ejEscuelaJPA.entities.Subject;

public class SubjectDAO {
	public void insert(EntityManager em, Subject subject) {
		em.persist(subject);
	}
	
	public List<Subject> getSubjects(EntityManager em) {
		TypedQuery<Subject> query = em.createQuery("FROM Subject ORDER BY id", Subject.class);
		return query.getResultList();
	}
	
	public void update(EntityManager em, Subject subject) {
		em.merge(subject);
	}
	
	public void delete(EntityManager em, Subject subject) {
		em.remove(subject);
	}
	
	public void setTeacher(EntityManager em, Subject subject) {
		em.merge(subject);
	}
}
