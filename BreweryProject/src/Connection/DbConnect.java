package Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//Class.forName("com.mysql.jdbc.Driver").newInstance();
public class DbConnect {

    public DbConnect(){}

    public static DbConnect getInstance(){

        return new DbConnect();
    }
    public Connection getConnection(){
        //String connect_string = "jdbc:mysql://127.0.0.1/users";

      //con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/sql2", "pmauser", "rootroot");

        Connection connection= null;

        try {


            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/users", "pmauser", "rootroot");


        } catch (SQLException e) {
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        return connection;
    }





}
//z dzialajacej apki
/*
public Connection getConnection() throws SQLException {

        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/sql2", "pmauser", "rootroot");
            if (!con.isClosed()) System.out.println("Successfully connected to MySQL server...");

            String sql="INSERT INTO data VALUES ('jebac legie')";
            Statement statement = con.createStatement();
            statement.executeUpdate(sql);

        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
            }
        }
        return con;
    }
 */