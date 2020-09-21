package com.nt.test;

import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.nt.entity.Product;

public class SaveObjectTest {

	public static void main(String[] args) {
		
		Configuration cfg = null,cfg1,cfg2;
		SessionFactory factory = null,factory1,factory2;
		Session session = null,session1,session2;
		Product prod = null;
		Transaction tx = null,tx1 = null,tx2 = null;
		boolean flag = false;
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		
		cfg = new Configuration();
		cfg.configure("com/nt/cfgs/oracle-hibernate.cfg.xml");
		factory = cfg.buildSessionFactory();
		session = factory.openSession();
		
		cfg1 = new Configuration();
		cfg1.configure("com/nt/cfgs/mysql-hibernate.cfg.xml");
		factory1 = cfg1.buildSessionFactory();
		session1 = factory1.openSession();
		
		cfg2 = new Configuration();
		cfg2.configure("com/nt/cfgs/postgresql-hibernate.cfg.xml");
		factory2 = cfg2.buildSessionFactory();
		session2 = factory2.openSession();
		
		System.out.println("Enter Product ID");
        int id = s.nextInt();
        System.out.println("Enter Product Name");
        String name = s.next();
        System.out.println("Enter Product Price");
        float price = s.nextFloat();
        System.out.println("Enter Product Quantity");
        float qty = s.nextFloat();
		
		prod = new Product();
		prod.setPid(id);
		prod.setPname(name);
		prod.setPrice(price);
		prod.setQty(qty);
		
		try {
			tx = session.beginTransaction();
			session.save(prod);
			tx1 = session1.beginTransaction();
			session1.save(prod);
			tx2 = session2.beginTransaction();
			session2.save(prod);
			flag = true;
		} catch (HibernateException he) {
			he.printStackTrace();
			flag = false;
		}
		finally {
			if(flag) {
				tx.commit();
				tx1.commit();
				tx2.commit();
				System.out.println("Object is Saved");
			}
			else {
				tx.rollback();
				tx1.rollback();
				tx2.rollback();
				System.out.println("Object is Not Saved");
			}
			
			session.close();
			session1.close();
			session2.close();
			factory.close();
			factory1.close();
			factory2.close();
		}
		

	}

}
