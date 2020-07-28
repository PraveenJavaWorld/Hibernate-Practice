package com.nt.test;

import com.nt.entity.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class SaveObjectTest {

    public static void main(String[] args) {

        @SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);

        Configuration cfg= null;
        SessionFactory factory= null;
        Session session = null;
        Transaction tx = null;
        Student stud = null;
        boolean flag = false;

        cfg = new Configuration();
        cfg.configure("com/nt/cfgs/hibernate.cfg.xml");
        factory = cfg.buildSessionFactory();
        session = factory.openSession();

        System.out.println("Enter Student ID");
        int id = s.nextInt();
        System.out.println("Enter Student FirstName");
        String fname = s.next();
        System.out.println("Enter Student LastName");
        String lname = s.next();
        System.out.println("Enter Student Email");
        String email = s.next();
        System.out.println("Enter Student Address");
        String address = s.next();
        System.out.println("Enter Student Branch");
        String branch = s.next();

        stud = new Student();
        stud.setId(id);
        stud.setFirstname(fname);
        stud.setLastname(lname);
        stud.setEmail(email);
        stud.setAddress(address);
        stud.setBranch(branch);

        try {
            tx = session.beginTransaction();
            session.save(stud);
            flag = true;
        }catch (HibernateException he){
            he.printStackTrace();
            flag = false;
        }
        finally {
            if (flag == true){
                tx.commit();
                System.out.println("Object is Saved");
            }
            else {
                tx.rollback();
                System.out.println("Object is not Saved");
            }
            session.close();
            factory.close();
        }

    }

}
