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
		label.setText("ͣҽ������");
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
		final JButton button = new JButton("ɾ��ҽ��");
		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(final MouseEvent e) {
				String ͣҽ������=textField.getText();
				if(ͣҽ������.equals(""))
				{
					 JOptionPane.showMessageDialog(DoctorLogin.frame,"ͣҽ�����벻��Ϊ�գ�","��ϢУ��",JOptionPane.ERROR_MESSAGE);
					 return;
				}
				if(!hasDocAdviceCode(ͣҽ������))
				{
					JOptionPane.showMessageDialog(DoctorLogin.frame,"����ѯ���ݿ�����������ҽ�����룡\n�������Ѿ����ڵ�ҽ������!","��ϢУ��",JOptionPane.INFORMATION_MESSAGE);
					 return;
				}
				int i=JOptionPane.showConfirmDialog(null,	"��ȷ��Ҫɾ����ҽ����?", "��ϢУ��", JOptionPane.YES_NO_OPTION);
				if(i==0)
				{
					Advi_No=textField.getText();
					updateDocAdvice1(Advi_No);
					JOptionPane.showMessageDialog(null, "ͣҽ���ɹ���", "��ʾ", JOptionPane.ERROR_MESSAGE);
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
	       System.out.println("ɾ��ʱ���ִ���");
	       System.out.println(e.getMessage());
	    }
	}
	public boolean hasDocAdviceCode(String ͣҽ������)
	{
		int num=0;
		Connection conn=null;
	    ResultSet rs=null;
	    try{
	    conn=DBUtil.getConnection();
	    //��ѯҽ�����еļ�¼��
		PreparedStatement ptmt = conn.prepareStatement("select Advi_No from docAdvice where Advi_No=?");
		ptmt.setString(1, ͣҽ������);
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
	    	return false;   //�����ڷ���false
	    }else
	    {
	    	return true;
	    }
	}	
}
