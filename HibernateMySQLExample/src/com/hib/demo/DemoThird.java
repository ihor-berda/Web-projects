package com.hib.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hib.entities.Student;
import com.hib.init.HibernateUtil;

public class DemoThird {
	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionfactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Student student = (Student) session.get(Student.class, 2);
		student.setFirstName("Zet");
		student.setAge(45);
		
		session.update(student);
		session.getTransaction().commit();
		
		session.close();
	}
}
