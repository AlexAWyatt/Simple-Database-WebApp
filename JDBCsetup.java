
// required package
import java.sql.*;

class JDBCsetup {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "postgres://rxvvcooplvilys:b73aab992569456aa893626c8f1e0229e156329b090bdac1a2b634c5fed71dd6@ec2-3-92-119-83.compute-1.amazonaws.com:5432/delfadi1gj68c0";

    // Database credentials
    static final String USER = "rxvvcooplvilys";
    static final String PASSWORD = "b73aab992569456aa893626c8f1e0229e156329b090bdac1a2b634c5fed71dd6";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try {
            // Open a connection
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            // Execute a Query
            System.out.println("Executing SQL Query...");
            statement = connection.createStatement();

            // Inside this string we can write our query.
            String sql = "";

            // Update the Query
            statement.executeUpdate(sql);
            System.out.println("Query executed successfully.");

            // Close the statement and connection
            statement.close();
            connection.close();
        } catch(Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    } // end of main
}

// how to run
/*
in terminal:

javac className.java
java -cp "executuableJarPath; executableClassFilePath" className
*/