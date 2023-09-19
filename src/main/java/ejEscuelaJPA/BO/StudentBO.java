package ejEscuelaJPA.BO;

import java.util.List;

import javax.persistence.EntityManager;

import ejEscuelaJPA.DAO.StudentDAO;
import ejEscuelaJPA.connection.EntityManagerSingleton;
import ejEscuelaJPA.entities.Student;

public class StudentBO {
	public void insert(Student student) {
		EntityManager em = EntityManagerSingleton.getEntityManager();
		em.getTransaction().begin();
		StudentDAO studentDAO = new StudentDAO();
		studentDAO.insert(em, student);
		em.getTransaction().commit();
		em.close();
	}

	public List<Student> getStudents() {
		EntityManager em = EntityManagerSingleton.getEntityManager();
		StudentDAO studentDAO = new StudentDAO();
		List<Student> studentList = studentDAO.getStudents(em);
		em.close();
		return studentList;
	}
	
	public void update(int studentId, String studentName) {
		EntityManager em = EntityManagerSingleton.getEntityManager();
		em.getTransaction().begin();
		StudentDAO studentDAO = new StudentDAO();
		Student student = em.find(Student.class, studentId);
		student.setName(studentName);
		studentDAO.update(em, student);
		em.getTransaction().commit();
		em.close();
	}
	
	public void delete(int studentId) {
		EntityManager em = EntityManagerSingleton.getEntityManager();
		em.getTransaction().begin();
		StudentDAO studentDAO = new StudentDAO();
		Student student = em.find(Student.class, studentId);
		studentDAO.delete(em, student);
		em.getTransaction().commit();
		em.close();
	}
}
