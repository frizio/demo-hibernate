package cloud.frizio.web.demo;

import java.sql.Connection;
import java.sql.DriverManager;

public class App {
    public static void main( String[] args ) {

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
