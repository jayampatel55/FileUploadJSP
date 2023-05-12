import java.sql.*;

import com.mysql.cj.xdevapi.Schema.CreateCollectionOptions;

public class main_test {
    public static void main(String[] args) {
        // Database credentials
        String url = "jdbc:mysql://localhost:3306/schema_1";
        String username = "root";
        String password = "412003";

        try {
            // Create a connection to the database
            Connection conn = DriverManager.getConnection(url, username, password);

            // Prepare a statement for inserting data
           
            String sql = "INSERT INTO mytable (name, age) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // Set the parameters for the statement
            pstmt.setString(1, "sam");
            pstmt.setInt(2, 25);

            // Execute the statement
            pstmt.executeUpdate();

            // Close the statement and the connection
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            // Handle any errors that may have occurred
            e.printStackTrace();
        }
    }
}

