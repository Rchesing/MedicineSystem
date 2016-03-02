package src;

import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class connectDB {
  public static  DefaultTableModel dtm;
  private Vector<String> data;
  private ResultSet rs=null;
  public connectDB(String TName){
	Connection conn=DBUtil.getConnection();
    try{
    Statement stmt=conn.createStatement();
     if(TName=="医嘱管理"){
       rs=stmt.executeQuery(
       "select sickName,startDay,adviceNo,content,type,state,doctor.docName,adTime,stopDay,officeNo from docAdvice,sick,doctor,bed where bed.bedNo=sick.bedNo and doctor.docNo=docAdvice.docNo");
        insertTab(10);
      }
     if(TName=="费用管理"){
       rs=stmt.executeQuery(
       "select officeNo,bedNo,sickName,sick.medicalNo,accountNo,hand,cost,leave,need from foregift,sick,room where sick.medicalNo=foregift.medicalNo and sick.roomNo=room.roomNo");
       insertTab(9);
       }
    }catch(SQLException e){
       System.out.println("Link Error");
    }

 }
 public void insertTab(int num){
    int j=0;
    try{
    while(rs.next()){
      data=new Vector<String>();
      for(int i=1;i<=num;i++) {
       data.add(rs.getString(i));
      }
       dtm.insertRow(j++,data);
     }
    }catch(SQLException e){
    	e.printStackTrace();
    	System.out.println("conectTab!!!!");
    }
 }
}