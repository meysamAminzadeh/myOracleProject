package oracleTransaction;

import java.sql.*;

/**
 * Created by asus on 5/13/2020.
 */
public class SaveToDB {

    PreparedStatement statement;
    Connection connection ;

    public  SaveToDB()
    {
        try {

            getConnection();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }


    }

    public Connection getConnection() throws Exception {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            if (connection == null || connection.isClosed() ) {
                connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.142.128:1521:orcl", "system", "12345");
            }


        } catch (Exception e) {
        }
        return connection;
    }


    public int generateID(String sequenceName){
        int id = 0;
        try {
            String query="SELECT "+ sequenceName +".NEXTVAL FROM dual";

/*      Statement stmt= connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        if ( rs!=null && rs.next() ) {
            id = rs.getInt(1);
            System.out.println(id);
            rs.close();
        }
            stmt.close();
            connection.close();*/

            if (connection == null || connection.isClosed()) {
                getConnection();
            }

         statement = connection.prepareStatement(query);
         ResultSet rs = statement.executeQuery();

            if ( rs!=null && rs.next() ) {
                id = rs.getInt(1);
                rs.close();
            }
            statement.close();
            connection.close();


        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return id;
    }

    public void insert(String name , String email , String password )
    {
        try {

            int pk_id= generateID("personsequence");

            if (connection == null || connection.isClosed()) {
                getConnection();
            }
            statement = connection.prepareStatement("insert into person values (?,?,?,?,?)");
            statement.setInt(1, pk_id);
            statement.setString(2, name);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.setTimestamp(5, new java.sql.Timestamp(new java.util.Date().getTime()));
            statement.executeQuery();
            statement.close();
            connection.close();
        }catch (Exception e)
        {
            System.out.println("********** "+e.getMessage());
            e.printStackTrace();
        }

    }
}
