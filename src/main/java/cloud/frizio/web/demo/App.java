package cloud.frizio.web.demo;

import java.sql.Connection;
import java.sql.DriverManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import cloud.frizio.web.demo.entity.Customer;

public class App {
    
    public static void main( String[] args ) {
      createAnObjectHibernate();
      //dbConnectionWithJDBC();
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
        Customer tempStudent1 = new Customer("Max", "Tux", "max@luv2code.com");
        Customer tempStudent2 = new Customer("Al", "Alen", "max@luv2code.com");
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
