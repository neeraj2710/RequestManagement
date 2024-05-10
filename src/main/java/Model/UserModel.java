package Model;

import pojo.UserPojo;

import java.sql.*;

public class UserModel {
    private static Connection conn;

    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advjava","root","neeraj");

        }catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String isUserValid(UserPojo user)throws SQLException{
        PreparedStatement ps = conn.prepareStatement("select * from users where username=? and PASSWORD =? and department=?");
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getDepartment());
        ResultSet rs = ps.executeQuery();
        String username = null;
        if(rs.next()){
            username = rs.getString("username");
        }
        return username;
    }
}
