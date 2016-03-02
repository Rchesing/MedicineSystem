package src;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class DoctorAdviceDelete extends JPanel{
	String Advi_No;
	private SpringLayout springLayout;
	private JTextField textField;
	public DoctorAdviceDelete() throws SQLException{
		super();
		springLayout = new SpringLayout();
		setLayout(springLayout);
		
		
		final JLabel label = new JLabel();
		label.setText("停医嘱代码");
		add(label);
		springLayout.putConstraint(SpringLayout.SOUTH, label, 90, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, label, 60, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, label, 233, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, label, 118, SpringLayout.WEST, this);

		textField = new JTextField();
		add(textField);
		springLayout.putConstraint(SpringLayout.SOUTH, textField, 95, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, textField, 65, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, textField, 353, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, textField, 218, SpringLayout.WEST, this);
		final JButton button = new JButton("删除医嘱");
		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(final MouseEvent e) {
				String 停医嘱代码=textField.getText();
				if(停医嘱代码.equals(""))
				{
					 JOptionPane.showMessageDialog(DoctorLogin.frame,"停医嘱代码不能为空？","信息校验",JOptionPane.ERROR_MESSAGE);
					 return;
				}
				if(!hasDocAdviceCode(停医嘱代码))
				{
					JOptionPane.showMessageDialog(DoctorLogin.frame,"经查询数据库中无这样的医嘱代码！\n请输入已经存在的医嘱代码!","信息校验",JOptionPane.INFORMATION_MESSAGE);
					 return;
				}
				int i=JOptionPane.showConfirmDialog(null,	"您确认要删除此医嘱吗?", "信息校验", JOptionPane.YES_NO_OPTION);
				if(i==0)
				{
					Advi_No=textField.getText();
					updateDocAdvice1(Advi_No);
					JOptionPane.showMessageDialog(null, "停医嘱成功！", "提示", JOptionPane.ERROR_MESSAGE);
					textField.setText("");
				}
				
			}
		});
		add(button);
		springLayout.putConstraint(SpringLayout.SOUTH, button, 140, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, button, 110, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, button, 329, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, button, 189, SpringLayout.WEST, this);

	}
	
	public void updateDocAdvice1(String Advi_No)
	{
	    try{
			Connection conn = DBUtil.getConnection();
			Statement stmt=conn.createStatement();
			String SQL = "delete from docAdvice where Advi_No="+"'"+Advi_No+"'";
			System.out.println(SQL);
			stmt.execute(SQL);
			//conn.close();
	    }catch(SQLException e){
	       System.out.println("删除时出现错误");
	       System.out.println(e.getMessage());
	    }
	}
	public boolean hasDocAdviceCode(String 停医嘱代码)
	{
		int num=0;
		Connection conn=null;
	    ResultSet rs=null;
	    try{
	    conn=DBUtil.getConnection();
	    //查询医嘱表中的记录数
		PreparedStatement ptmt = conn.prepareStatement("select Advi_No from docAdvice where Advi_No=?");
		ptmt.setString(1, 停医嘱代码);
		rs = ptmt.executeQuery();
	    while(rs.next())
	    {
	    	num++;
	    }
	    //conn.close();
	    }catch(SQLException e){
	       System.out.println("DoctorAdvideStop1!!!");
	    }
	    if(num==0)
	    {
	    	return false;   //不存在返回false
	    }else
	    {
	    	return true;
	    }
	}	
}
