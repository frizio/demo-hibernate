package cloud.frizio.web.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import cloud.frizio.web.demo.entity.Customer;

public class App {

    public static final int THEID = 1;

    public static void main( String[] args ) {
      updateAnObjectWithHibernate();
      //readAnObjectHibernate();
      //createAnObjectHibernate();
      //dbConnectionWithJDBC();
    }

    public static void updateAnObjectWithHibernate() {
      // create session factory
      SessionFactory sessionFactory = new Configuration()
                                            .configure("hibernate.cfg.xml")
                                            .addAnnotatedClass(Customer.class)
                                            .buildSessionFactory();
      // create session
      Session session = sessionFactory.getCurrentSession();
      try {
        System.out.println("Reading a student object...");

        // start a transaction
        session.beginTransaction();
        // Read the data
        Customer theCustomer = (Customer) session.get(Customer.class, THEID);
        // Update the data
        theCustomer.setLastName("Simpsonn");
        // commit transaction
        session.getTransaction().commit();
        // Display result
        System.out.println("The customer retrieved is " + theCustomer);
        System.out.println("Done!");
      }
      finally {
        sessionFactory.close();
      }

    }
  
    public static void readAnObjectHibernate() {
      // create session factory
      SessionFactory sessionFactory = new Configuration()
                                            .configure("hibernate.cfg.xml")
                                            .addAnnotatedClass(Customer.class)
                                            .buildSessionFactory();
      // create session
      Session session = sessionFactory.getCurrentSession();
      try {
        System.out.println("Reading a student object...");

        // start a transaction
        session.beginTransaction();
        // Read the data
        Customer theCustomer = (Customer) session.get(Customer.class, THEID);
        // Reads the data
        List<Customer> customers = session.createQuery("from Customer c where c.email LIKE '%email.com'", Customer.class).getResultList();
        // commit transaction
        session.getTransaction().commit();
        // Display result
        System.out.println("The customer retrieved is " + theCustomer);
        System.out.println("Customer list ### ");
        for (Customer customer : customers) {
          System.out.println(customer);
        }
        System.out.println("End ###");
        System.out.println("Done!");
      }
      finally {
        sessionFactory.close();
      }

    }

    public static void createAnObjectHibernate() {
      // create session factory
      SessionFactory sessionFactory = new Configuration()
                                            .configure("hibernate.cfg.xml")
                                            .addAnnotatedClass(Customer.class)
                                            .buildSessionFactory();
      // create session
      Session session = sessionFactory.getCurrentSession();
      try {
        // create a customer object
        System.out.println("Creating new student object...");
        Customer tempStudent1 = new Customer("Bob", "Bobbed", "max@email.com");
        Customer tempStudent2 = new Customer("Carl", "Carlson", "max@email.com");
        // start a transaction
        session.beginTransaction();
        // save the customer object
        System.out.println("Saving the student...");
        session.save(tempStudent1);
        session.save(tempStudent2);
        // commit transaction
        session.getTransaction().commit();
        System.out.println("Done!");
      }
      finally {
        sessionFactory.close();
      }

    }


    public static void dbConnectionWithJDBC() {

        System.out.println( "Demo Hibernate" );

        String user = "wildfly_user";
        String password = "password1;";
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String driver = "com.mysql.cj.jdbc.Driver";

        try {
          System.out.println("Connecting to db: " + url);
          Class.forName(driver);
          Connection connection = DriverManager.getConnection(url, user, password);
          System.out.println("Successful Connection.");
          connection.close();
        } catch (Exception e) {
          e.printStackTrace();
        }

    }



}
