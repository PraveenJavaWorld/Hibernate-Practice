package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.nt.entity.Student;



public class SaveObjectTest {

	public static void main(String[] args) {
		
		Configuration cfg = null;
		SessionFactory factory = null;
		Session ses = null;
		Transaction tx = null;
		Student stud = null;
		boolean flag = false;
		
		cfg = new Configuration();
		cfg.configure("com/nt/cfgs/hibernate.cfg.xml");
		factory = cfg.buildSessionFactory();
		ses = factory.openSession();
		
		stud = new Student();
		stud.setId(123);
		stud.setFirstname("Praveen");
		stud.setLastname("Chandana");
		stud.setEmail("chvnspraveen1997@gmail.com");
		stud.setAddress("Hyderabad");
		stud.setBranch("ComputerScience");
		
		try {
			tx = ses.beginTransaction();
			ses.save(stud);
			flag = true;
			
		} catch (HibernateException he) {
			he.printStackTrace();
			flag = false;
		}
		finally {
			if(flag == true) {
				tx.commit();
				System.out.println("Object is Saved");
			}
			else {
				tx.rollback();
				System.out.println("Object is not Saved");
			}
			ses.close();
			factory.close();
		}

	}

}
