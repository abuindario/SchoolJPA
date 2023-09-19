package ejEscuelaJPA.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ejEscuelaJPA.entities.Teacher;

public class TeacherDAO {
	public void insert(EntityManager em, Teacher teacher) {
		em.persist(teacher);
	}
	
	public List<Teacher> getTeachers(EntityManager em) {
		TypedQuery<Teacher> query = em.createQuery("FROM Teacher ORDER BY id", Teacher.class);
		return query.getResultList();
	}
	
	public void update(EntityManager em, Teacher teacher) {
		em.merge(teacher);
	}
	
	public void delete(EntityManager em, Teacher teacher) {
		em.remove(teacher);
	}
}
