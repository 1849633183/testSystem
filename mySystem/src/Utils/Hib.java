package Utils;

import java.util.jar.Attributes.Name;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Bean.User;



public class Hib {
	public void add(String name,String password ,String email) {

		SessionFactory sf = new Configuration().configure().buildSessionFactory();

		Session s = sf.openSession();
		s.beginTransaction();

		User p = new User();
		p.setName(name);
		p.setPassword(password);
		p.setEmail(email);
		s.save(p);

		s.getTransaction().commit();
		s.close();
		sf.close();
	}

}