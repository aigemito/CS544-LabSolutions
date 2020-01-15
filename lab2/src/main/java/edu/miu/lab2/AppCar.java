package edu.miu.lab2;

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
public class AppCar {
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
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			// Create new instance of car and set values in it
			Car car = new Car();
			car.setBrand("Toyota");
			car.setYear(2013);
			car.setPrice(10000.00f);
			// save the car
			session.persist(car);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			// retrieve all Cars
			List<Car> CarList = session.createQuery("from Car").list();
			for (Car car : CarList) {
				System.out.println("Brand = " + car.getBrand()+ ", Year= " + car.getYear() + " and Price= "+ car.getPrice());
			}
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		// Close the SessionFactory (not mandatory)
		sessionFactory.close();
	}
}