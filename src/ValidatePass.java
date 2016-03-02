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
	    //����û�����������Ч������ʾ
	    if(rs.next()){
	     //conn.close();
	     //������һ��ҳ�棬��ʾ�û���Ϣ
	     new Master();
	     DoctorLogin.frame.dispose();
	     //û�д��û���������ʾ�����û���������
	     }
	    else {
	        JOptionPane.showMessageDialog(DoctorLogin.frame,"  ������Ȩ�û�,�˳����������룡","�������",JOptionPane.ERROR_MESSAGE);
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
