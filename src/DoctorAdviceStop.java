package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class DoctorAdviceStop extends JPanel {

	private JComboBox comboBox_1;
	private JComboBox comboBox;
	private SpringLayout springLayout;
	private JTextField textField_1;
	private JTextField textField;
	/**
	 * Create the panel
	 */
	public DoctorAdviceStop() {
		super();
		springLayout = new SpringLayout();
		setLayout(springLayout);
		
		Calendar rightNow = Calendar.getInstance();
		int year=rightNow.get(Calendar.YEAR);
		int month=rightNow.get(Calendar.MONTH)+1;
		int day=rightNow.get(Calendar.DAY_OF_MONTH);
		
		final JLabel label = new JLabel();
		label.setText("停医嘱代码");
		add(label);
		springLayout.putConstraint(SpringLayout.SOUTH, label, 90, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, label, 60, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, label, 233, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, label, 118, SpringLayout.WEST, this);

		final JLabel label_1 = new JLabel();
		label_1.setText("停医嘱时间");
		add(label_1);
		springLayout.putConstraint(SpringLayout.SOUTH, label_1, 140, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, label_1, 110, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, label_1, 233, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, label_1, 118, SpringLayout.WEST, this);

		final JLabel label_2 = new JLabel();
		label_2.setText("停医嘱医生");
		add(label_2);

		textField = new JTextField();
		add(textField);
		springLayout.putConstraint(SpringLayout.SOUTH, textField, 95, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, textField, 65, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, textField, 353, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, textField, 218, SpringLayout.WEST, this);

		textField_1 = new JTextField();
		textField_1.setText(String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(day));
		textField_1.setEditable(false);
		add(textField_1);
		springLayout.putConstraint(SpringLayout.SOUTH, textField_1, 140, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, textField_1, 110, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, textField_1, 353, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, textField_1, 218, SpringLayout.WEST, this);

		final JButton button = new JButton();
		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(final MouseEvent e) {
				String 停医嘱代码=textField.getText();
				if(停医嘱代码.equals(""))
				{
					 JOptionPane.showMessageDialog(DoctorLogin.frame,"停医嘱代码不能为空？","信息校验",JOptionPane.ERROR_MESSAGE);
					 return;
				}
				if(comboBox_1.getItemCount()<1)
				{
					JOptionPane.showMessageDialog(DoctorLogin.frame,"请选择科室？\n确定停医嘱医生","信息校验",JOptionPane.INFORMATION_MESSAGE);
					 return;
				}
				String 停医嘱医生=comboBox_1.getSelectedItem().toString();
				String 停医嘱时间=textField_1.getText();
				if(!hasDocAdviceCode(停医嘱代码))
				{
					JOptionPane.showMessageDialog(DoctorLogin.frame,"经查询数据库中无这样的医嘱代码！\n请输入已经存在的医嘱代码!","信息校验",JOptionPane.INFORMATION_MESSAGE);
					 return;
				}
				String DocNo=getDocNo(停医嘱医生);
				System.out.println(停医嘱代码);
				System.out.println(停医嘱医生);
				System.out.println("停医嘱医生NO="+DocNo);
				System.out.println(停医嘱时间);
				int i=JOptionPane.showConfirmDialog(null,	"您确认要停止此医嘱吗?", "信息校验", JOptionPane.YES_NO_OPTION);
				if(i==0)
				{
					updateDocAdvice(DocNo,停医嘱代码,停医嘱时间);
					JOptionPane.showMessageDialog(null, "停医嘱成功！", "提示", JOptionPane.ERROR_MESSAGE);
					textField.setText("");
				}
				
			}
		});
		button.setText("执行停止医嘱");
		add(button);
		springLayout.putConstraint(SpringLayout.SOUTH, button, 301, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, button, 271, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, button, 329, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, button, 189, SpringLayout.WEST, this);

		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				String se1 = (String) cb.getSelectedItem();
				if (se1.equals("科室一")) {
					comboBox_1.removeAllItems();
					String SQL="select docName from doctor where officeNo='office51'";
					ComboxBoxAddItem(SQL);
				}
				if (se1.equals("科室二")) {
					comboBox_1.removeAllItems();
					String SQL="select docName from doctor where officeNo='office52'";
					ComboxBoxAddItem(SQL);
				}
				if (se1.equals("科室三")) {
					comboBox_1.removeAllItems();
					String SQL="select docName from doctor where officeNo='office53'";
					ComboxBoxAddItem(SQL);
				}
				// -----------------------------------------------------------------------
				if (se1.equals("科室四")) {
					comboBox_1.removeAllItems();
					String SQL="select docName from doctor where officeNo='office54'";
					ComboxBoxAddItem(SQL);
				}
				// ------------------------------------------------------------
				if (se1.equals("科室五")) {
					comboBox_1.removeAllItems();
					String SQL="select docName from doctor where officeNo='office55'";
					ComboxBoxAddItem(SQL);
				}

			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"科室一", "科室二", "科室三", "科室四", "科室五"}));
		add(comboBox);
		springLayout.putConstraint(SpringLayout.SOUTH, comboBox, -5, SpringLayout.NORTH, label_2);
		springLayout.putConstraint(SpringLayout.NORTH, comboBox, 160, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, comboBox, 0, SpringLayout.EAST, textField_1);
		springLayout.putConstraint(SpringLayout.WEST, comboBox, 0, SpringLayout.WEST, textField_1);

		final JLabel label_3 = new JLabel();
		label_3.setText("停医嘱科室");
		add(label_3);
		springLayout.putConstraint(SpringLayout.EAST, label_2, 115, SpringLayout.WEST, label_3);
		springLayout.putConstraint(SpringLayout.WEST, label_2, 0, SpringLayout.WEST, label_3);
		springLayout.putConstraint(SpringLayout.SOUTH, label_3, -5, SpringLayout.NORTH, label_2);
		springLayout.putConstraint(SpringLayout.NORTH, label_3, -36, SpringLayout.NORTH, label_2);
		springLayout.putConstraint(SpringLayout.EAST, label_3, 77, SpringLayout.WEST, label_1);
		springLayout.putConstraint(SpringLayout.WEST, label_3, 0, SpringLayout.WEST, label_1);

		comboBox_1 = new JComboBox();
		add(comboBox_1);
		springLayout.putConstraint(SpringLayout.SOUTH, label_2, 0, SpringLayout.SOUTH, comboBox_1);
		springLayout.putConstraint(SpringLayout.NORTH, label_2, -35, SpringLayout.SOUTH, comboBox_1);
		springLayout.putConstraint(SpringLayout.SOUTH, comboBox_1, 236, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, comboBox_1, 205, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, comboBox_1, 135, SpringLayout.WEST, comboBox);
		springLayout.putConstraint(SpringLayout.WEST, comboBox_1, 0, SpringLayout.WEST, comboBox);
		//
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
	public void ComboxBoxAddItem(String ItemSQL)
	{
		Connection conn=null;
	    ResultSet rs=null;
	    try{
	    conn=DBUtil.getConnection();
	    Statement stmt=conn.createStatement();
	    rs=stmt.executeQuery(ItemSQL);
	    while(rs.next())
	    {
	    	comboBox_1.addItem(rs.getString(1));
	    }
	    //conn.close();
	    }catch(SQLException e){
	       System.out.println("DoctorAdvideStop2!!!");
	    }
	}
	public String getDocNo(String 停医嘱医生)
	{
		String docNo=null;
		Connection conn=null;
	    ResultSet rs=null;
	    try{
	    conn=DBUtil.getConnection();
		PreparedStatement ptmt = conn.prepareStatement("select DocNo from doctor where docName=?");
		ptmt.setString(1, 停医嘱医生);
		rs = ptmt.executeQuery();
	    while(rs.next())
	    {
	    	docNo=rs.getString(1);
	    }
	    //conn.close();
	    }catch(SQLException e){
	       System.out.println("DoctorAdvideStop3!!!");
	       System.out.println("获取医生NO时错误");
	       System.out.println(e.getMessage());
	    }
		return docNo;
	}
	public void updateDocAdvice(String 停医嘱医No,String 停医嘱代码,String 停医嘱时间)
	{
		Connection conn=null;
	    ResultSet rs=null;
	    try{
	    conn=DBUtil.getConnection();
	    Statement stmt=conn.createStatement();
	    stmt.execute("update docAdvice set Advi_outtime='"+停医嘱时间+"',Advi_outpers='"+停医嘱医No+"' where Advi_No='"+停医嘱代码+"'");
	    //conn.close();
	    }catch(SQLException e){
	       System.out.println("DoctorAdvideStop4!!!");
	       System.out.println("更新时出现错误");
	       System.out.println(e.getMessage());
	    }
	}
}
