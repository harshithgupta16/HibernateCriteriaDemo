package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        // Insert records
        Transaction transaction = session.beginTransaction();
        Customer customer1 = new Customer();
        customer1.setName("John Doe");
        customer1.setEmail("john@example.com");
        customer1.setAge(25);
        customer1.setLocation("New York");

        Customer customer2 = new Customer();
        customer2.setName("Jane Smith");
        customer2.setEmail("jane@example.com");
        customer2.setAge(30);
        customer2.setLocation("Los Angeles");

        session.save(customer1);
        session.save(customer2);
        transaction.commit();

        // Perform HCQL operations
        System.out.println("Customers whose age is greater than 25:");
        List<Customer> customers = session.createCriteria(Customer.class)
                .add(Restrictions.gt("age", 25))
                .list();
        for (Customer c : customers) {
            System.out.println(c.getName());
        }

        System.out.println("Customers with name containing 'John':");
        customers = session.createCriteria(Customer.class)
                .add(Restrictions.like("name", "%John%"))
                .list();
        for (Customer c : customers) {
            System.out.println(c.getName());
        }

        session.close();
        sessionFactory.close();
    }
}