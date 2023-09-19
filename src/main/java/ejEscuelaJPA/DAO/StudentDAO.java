package ejEscuelaJPA.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ejEscuelaJPA.entities.Student;

public class StudentDAO {
	public void insert(EntityManager em, Student student) {
		em.persist(student);
	}
	
	public List<Student> getStudents(EntityManager em) {
		TypedQuery<Student> query = em.createQuery("FROM Student ORDER BY id", Student.class);
		return query.getResultList();
	}
	
	public void update(EntityManager em, Student student) {
		em.merge(student);
	}
	
	public void delete(EntityManager em, Student student) {
		em.remove(student);
	}
}
