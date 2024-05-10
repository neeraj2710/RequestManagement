package Model;

import pojo.RequestPojo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestModel {
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

    public static String getNextReqId()throws SQLException{

        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("Select max(SNO) from requests");
        rs.next();
        String str=rs.getString(1);
        int empId=101;
        if(str!=null){
            String id=str.substring(1);
            empId=Integer.parseInt(id);
            empId++;
        }
        String newId="R"+empId;
        return newId;
    }

    public static boolean addRequest(RequestPojo r)throws SQLException{
        PreparedStatement ps = conn.prepareStatement("insert into requests values(?,?,?,?,?,?,?,?,?)");
        ps.setString(1,RequestModel.getNextReqId());
        ps.setString(2,r.getTitle());
        ps.setString(3,r.getDescription());
        ps.setString(4,r.getRaisedBy());
        ps.setString(5,r.getRequestDate());
        ps.setString(6,r.getDepartment());
        ps.setString(7,r.getApprovalDate());
        ps.setString(8,r.getApprovedBy());
        ps.setString(9,r.getStatus());
        return ps.executeUpdate()==1;
    }

    public static List<RequestPojo> getAllRequestById(String raisedBy)throws SQLException{
        PreparedStatement ps = conn.prepareStatement("Select * from requests where raisedby=?");
        ps.setString(1,raisedBy);
        ResultSet rs=ps.executeQuery();
        List<RequestPojo> rlist=new ArrayList<>();
        while(rs.next()){
            RequestPojo r=new RequestPojo();
            r.setSno(rs.getString(1));
            r.setTitle(rs.getString(2));
            r.setDescription(rs.getString(3));
            r.setRaisedBy(rs.getString(4));
            r.setRequestDate(rs.getString(5));
            r.setDepartment(rs.getString(6));
            r.setApprovalDate(rs.getString(7));
            r.setApprovedBy(rs.getString(8));
            r.setStatus(rs.getString(9));
            rlist.add(r);
        }
        return rlist;
    }

    public static List<RequestPojo> getAllRequestByDepartment(String department)throws SQLException{
        PreparedStatement ps = conn.prepareStatement("Select * from requests where department=?");
        ps.setString(1,department);
        ResultSet rs=ps.executeQuery();
        List<RequestPojo> rlist=new ArrayList<>();
        while(rs.next()){
            RequestPojo r=new RequestPojo();
            r.setSno(rs.getString(1));
            r.setTitle(rs.getString(2));
            r.setDescription(rs.getString(3));
            r.setRaisedBy(rs.getString(4));
            r.setRequestDate(rs.getString(5));
            r.setDepartment(rs.getString(6));
            r.setApprovalDate(rs.getString(7));
            r.setApprovedBy(rs.getString(8));
            r.setStatus(rs.getString(9));
            rlist.add(r);
        }
        return rlist;
    }

    public static List<RequestPojo> getPendingRequestByDepartment(String department)throws SQLException{
        PreparedStatement ps = conn.prepareStatement("Select * from requests where department=? and status='pending'");
        ps.setString(1,department);
        ResultSet rs=ps.executeQuery();
        List<RequestPojo> rlist=new ArrayList<>();
        while(rs.next()){
            RequestPojo r=new RequestPojo();
            r.setSno(rs.getString(1));
            r.setTitle(rs.getString(2));
            r.setDescription(rs.getString(3));
            r.setRaisedBy(rs.getString(4));
            r.setRequestDate(rs.getString(5));
            r.setDepartment(rs.getString(6));
            r.setApprovalDate(rs.getString(7));
            r.setApprovedBy(rs.getString(8));
            r.setStatus(rs.getString(9));
            rlist.add(r);
        }
        return rlist;
    }

    public static boolean updateRequest(RequestPojo r)throws SQLException{
        PreparedStatement ps = conn.prepareStatement("update requests set approvaldate=?, approvedby=?, status=? where sno=?");
        ps.setString(1,r.getApprovalDate());
        ps.setString(2,r.getApprovedBy());
        ps.setString(3,r.getStatus());
        ps.setString(4,r.getSno());
        return ps.executeUpdate()==1;
    }
}
