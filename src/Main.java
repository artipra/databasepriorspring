import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        String url ="jdbc:mysql://localhost:3306/batchtest";
        String user = "root";
        String password ="root";
        String query = "select * from player";
        Connection conn = null;
        Statement stmt = null;
        try {
            conn  = getConnection(url,user,password);
            stmt = getStatement(conn);
//            createTable(stmt);
//            insert(conn);
            delete(conn,3);
        }
        catch(Exception e){
           e.printStackTrace();
        }
    }

    static Connection getConnection(String url, String user, String password){
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    static Statement getStatement(Connection conn){
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return stmt;
    }
    static void createTable(Statement stmt) {
        String create = "create table if not exists player(\n" +
                "    id bigint not null,\n" +
                "    first_name varchar(50),\n" +
                "    last_name varchar(50),\n" +
                "    email varchar(50),\n" +
                "    gender varchar(20),\n" +
                "    primary key(id)\n" +
                ")";
        try {
           stmt.execute(create);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void insert(Connection conn) throws IOException, SQLException {
        List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Arti\\Desktop\\mock_data_1.csv"));
        PreparedStatement pstmt = conn.prepareStatement("insert into player(id,first_name,last_name,email,gender) values(?,?,?,?,?)");

        lines.remove(0);
        for(String line : lines){
           String[] data = line.split(",");
            pstmt.setInt(1,Integer.valueOf(data[0]));
            pstmt.setString(2,data[1]);
            pstmt.setString(3,data[2]);
            pstmt.setString(4,data[3]);
            pstmt.setString(5,data[4]);
            pstmt.execute();
        }
        pstmt.close();
    }

    static void delete(Connection conn,int id) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("delete from player where id = ?");
            pstmt.setInt(1,id);
            pstmt.execute();
            pstmt.close();
    }

}