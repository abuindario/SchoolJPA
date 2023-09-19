package ejEscuelaJPA.BO;

import java.util.List;

import javax.persistence.EntityManager;

import ejEscuelaJPA.DAO.TeacherDAO;
import ejEscuelaJPA.connection.EntityManagerSingleton;
import ejEscuelaJPA.entities.Teacher;

public class TeacherBO {
	public void insert(Teacher teacher) {
		EntityManager em = EntityManagerSingleton.getEntityManager();
		em.getTransaction().begin();
		TeacherDAO teacherDAO = new TeacherDAO();
		teacherDAO.insert(em, teacher);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Teacher> getTeachers() {
		EntityManager em = EntityManagerSingleton.getEntityManager();
		TeacherDAO teacherDAO = new TeacherDAO();
		List<Teacher> teachersList = teacherDAO.getTeachers(em);
		em.close();
		return teachersList;
	}
	
	public void update(int teacherId, String teacherName) {
		EntityManager em = EntityManagerSingleton.getEntityManager();
		em.getTransaction().begin();
		TeacherDAO teacherDAO = new TeacherDAO();
		Teacher teacher = em.find(Teacher.class, teacherId);
		teacher.setName(teacherName);
		teacherDAO.update(em, teacher);
		em.getTransaction().commit();
		em.close();
	}
	
	public void delete(int teacherId) {
		EntityManager em = EntityManagerSingleton.getEntityManager();
		em.getTransaction().begin();
		TeacherDAO teacherDAO = new TeacherDAO();
		Teacher teacher = em.find(Teacher.class, teacherId);
		teacherDAO.delete(em, teacher);
		em.getTransaction().commit();
		em.close();
	}
}
