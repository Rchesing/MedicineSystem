package src;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ValidatePass {
	private String name;
	private String passwd;
	public ValidatePass(String name,String passwd)
	{
		this.name=name;
		this.passwd=passwd;
	}
	public void ValidatePassword() throws SQLException
	{
		Connection conn=DBUtil.getConnection();
		String sql="select * from doctor where docName=? and docPassword=?";
	    ResultSet rs=null;
	    PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, name);
		ptmt.setString(2, passwd);
	    try{
	    rs=ptmt.executeQuery();
	    //如果用户名和密码有效，则显示
	    if(rs.next()){
	     //conn.close();
	     //进入下一级页面，显示用户信息
	     new Master();
	     DoctorLogin.frame.dispose();
	     //没有此用户，给出提示，让用户重新输入
	     }
	    else {
	        JOptionPane.showMessageDialog(DoctorLogin.frame,"  你是无权用户,退出或重新输入！","错误操作",JOptionPane.ERROR_MESSAGE);
	        DoctorLogin.name.setText("");
	        DoctorLogin.password.setText("");
	        //conn.close();
	        return;
	     }
	    }catch(SQLException e){
	    System.out.println("ValidatePass!!!");
	    }
	}
}
