package com.hib.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hib.entities.Student;
import com.hib.init.HibernateUtil;

public class DemoSecond {
	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionfactory();
		Session session = sessionFactory.openSession();
		List<Student> students = session.createQuery("from Student").list();
		
		session.close();
		
		for (Student s : students) {
			System.out.println(s.getId()+". "+s.getFirstName()+" "+s.getAge());
		}
	}
}
