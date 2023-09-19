package ejEscuelaJPA.DAO;

import javax.persistence.EntityManager;

import ejEscuelaJPA.entities.Student_Subject;

public class Student_SubjectDAO {
	public void insert(EntityManager em, Student_Subject ss) {
		em.persist(ss);
	}
}
