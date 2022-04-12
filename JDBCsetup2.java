import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCsetup2 {
    public static void main(String[] args) {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("postgres://rxvvcooplvilys:b73aab992569456aa893626c8f1e0229e156329b090bdac1a2b634c5fed71dd6@ec2-3-92-119-83.compute-1.amazonaws.com:5432/delfadi1gj68c0","rxvvcooplvilys","b73aab992569456aa893626c8f1e0229e156329b090bdac1a2b634c5fed71dd6");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Database opened successfully.");
    }
    
}


// how to run
/*
in terminal:

javac className.java
java -cp "executuableJarPath; executableClassFilePath" className
*/


// javac JDBCsetup2.java
// java -cp C:\Users\ctm31\Documents\GitHub\csi2132\postgresql-42.3.3.jar;C:\Users\ctm31\Documents\GitHub\csi2132\JDBCsetup2.java JDBCSetup2