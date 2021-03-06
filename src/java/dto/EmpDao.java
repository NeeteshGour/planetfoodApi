/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import dbutil.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Emp;


/**
 *
 * @author Monika
 */
public class EmpDao {
      public static String getNewId()throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        int id=101;
        ResultSet rs=st.executeQuery("select count(*) from employees");
        if(rs.next())
        {
            id=id+rs.getInt(1);
        }
        return "E"+id;
        
    }
      public static ArrayList<String>getEmployeeId()throws SQLException
    {
        ArrayList<String> empJob= new ArrayList<>();
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs =st.executeQuery("Select empid from employees");
        while(rs.next())
        { 
            empJob.add(rs.getString(1));
        }
        return empJob;
    }
         public static ArrayList<String>getEmployeeId(String job)throws SQLException
    {
        ArrayList<String> empJob= new ArrayList<>();
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Select empid from employees where job=?");
        ps.setString(1, job);
        ResultSet rs =ps.executeQuery();
        while(rs.next())
        { 
            empJob.add(rs.getString(1));
        }
        return empJob;
    }
    public static boolean addEmployee(Emp e)throws SQLException
    {
    
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into employees values(?,?,?,?)");
        ps.setString(1,e.getEmpId());
        ps.setString(2,e.getEmpName());
        ps.setString(3, e.getEmpJob());
        ps.setDouble(4,e.getEmpSal());
        int count=ps.executeUpdate();
        if(count==0)
            return false;
        else
            return true;
    }
    public static boolean editEmployee(Emp e)throws SQLException
    {
    
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("update employees set ename=?,job=?,sal=? where empid=?");
        ps.setString(4,e.getEmpId());
        ps.setString(1,e.getEmpName());
        ps.setString(2,e.getEmpJob());
        ps.setDouble(3,e.getEmpSal());
        int count=ps.executeUpdate();
        if(count==0)
            return false;
        else
            return true;
    }
       public static boolean removeEmp(String empId)throws SQLException
    {
    
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("delete from employees where empid=?");
        ps.setString(1,empId);
        int count=ps.executeUpdate();
        if(count==0)
            return false;
        else
            return true;
    }
    public static Emp viewEmp(String id)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Select * from employees where empid=?");
        ps.setString(1,id);
        ResultSet rs =ps.executeQuery();
        Emp e=new Emp();
        if(rs.next())
        { 
            e.setEmpName(rs.getString(2));
            e.setEmpSal(rs.getDouble(4));
        }
        return e;
    }
           
    public static ArrayList<Emp>getAllData()throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        String qry="Select * from employees";
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(qry);
        ArrayList<Emp> empList=new ArrayList<Emp>();
        while(rs.next())
        {
            Emp e=new Emp();
            e.setEmpId(rs.getString(1));
            e.setEmpName(rs.getString(2));
            e.setEmpJob(rs.getString(3));
            e.setEmpSal(rs.getDouble(4));
            empList.add(e);
          }
       return empList;
    }
}
        

