import java.sql.*;

public class JDBCExample {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            String url = "jdbc:mysql://localhost:3306/jd1709";
            String username = "root";
            String password = "********";

            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database.");

            // Insert user
            String insertSql = "INSERT INTO users (name, email) VALUES (?, ?)";
            pstmt = conn.prepareStatement(insertSql);
            pstmt.setString(1, "JD");
            pstmt.setString(2, "jd17092002@gmail.com");
            pstmt.executeUpdate();

            // Read users
            String selectSql = "SELECT * FROM users";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(selectSql);

            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " + rs.getString("name") + " | " + rs.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
            try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}
