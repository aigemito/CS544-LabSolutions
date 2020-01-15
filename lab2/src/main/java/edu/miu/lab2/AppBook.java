package edu.miu.lab2;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


/**
 * Hello world!
 *
 */
public class AppBook {
	private static SessionFactory sessionFactory;

	static {
		// This step will read hibernate.cfg.xml and prepare hibernate for use
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(sr);
	}

	public static void main(String[] args) {
		// Hibernate placeholders
		Session session = null;
		Transaction tx = null;
		//Question1
		Question1(session,tx);
		//Question2
		Queston2(session,tx);
		//Question3
		Question3(session,tx);
		//Question4
		Queston2(session,tx);
		
		// Close the SessionFactory (not mandatory)
		sessionFactory.close();
	}

	private static void Question3(Session session, Transaction tx) {
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			// Retrieve a book from the database and change its title and price 
			Book book = (Book) session.get(Book.class, 1);
			book.setTitle("Spring MVC _ designing real-world web applications");
			book.setPrice(99.9);
			session.update(book);
			//Delete a book (not the one that was just updated) 
			Book deleteBook=(Book) session.get(Book.class, 3);
			session.delete(deleteBook);
			
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		
	}

	private static void Queston2(Session session, Transaction tx) {
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			// retrieve all books
			List<Book> bookList = session.createQuery("from Book").list();
			for (Book book : bookList) {
				System.out.println(book);
			}
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		
	}

	private static void Question1(Session session, Transaction tx) {
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			// Create three new instance of book and set values in it
			Book book1 = new Book();
			book1.setTitle("Spring MVC 5.0.1");
			book1.setISBN("201908-12");
			book1.setAuthor("Kunjumohamed, Shameer");
			book1.setPrice(79);
			book1.setPublic_date(new Date(2016,02,01));
			Book book2 = new Book();
			book2.setTitle("Spring MVC Cookbook_ Over 40 recipes for creating cloud-ready Java web applications");
			book2.setISBN("201608-11");
			book2.setAuthor("Alex Bretet");
			book2.setPrice(189);
			book2.setPublic_date(new Date(2016,9,11));
			Book book3 = new Book();
			book3.setTitle("Spring MVC_ A Tutorial-Brainy Software");
			book3.setISBN("201705-11");
			book3.setAuthor("Paul Deck");
			book3.setPrice(109);
			book3.setPublic_date(new Date(2017,9,11));
			// save the books
			session.persist(book1);
			session.persist(book2);
			session.persist(book3);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		
	}
}