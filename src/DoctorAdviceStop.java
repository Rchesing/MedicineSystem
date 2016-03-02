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
		label.setText("ͣҽ������");
		add(label);
		springLayout.putConstraint(SpringLayout.SOUTH, label, 90, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, label, 60, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, label, 233, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, label, 118, SpringLayout.WEST, this);

		final JLabel label_1 = new JLabel();
		label_1.setText("ͣҽ��ʱ��");
		add(label_1);
		springLayout.putConstraint(SpringLayout.SOUTH, label_1, 140, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, label_1, 110, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, label_1, 233, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, label_1, 118, SpringLayout.WEST, this);

		final JLabel label_2 = new JLabel();
		label_2.setText("ͣҽ��ҽ��");
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
				String ͣҽ������=textField.getText();
				if(ͣҽ������.equals(""))
				{
					 JOptionPane.showMessageDialog(DoctorLogin.frame,"ͣҽ�����벻��Ϊ�գ�","��ϢУ��",JOptionPane.ERROR_MESSAGE);
					 return;
				}
				if(comboBox_1.getItemCount()<1)
				{
					JOptionPane.showMessageDialog(DoctorLogin.frame,"��ѡ����ң�\nȷ��ͣҽ��ҽ��","��ϢУ��",JOptionPane.INFORMATION_MESSAGE);
					 return;
				}
				String ͣҽ��ҽ��=comboBox_1.getSelectedItem().toString();
				String ͣҽ��ʱ��=textField_1.getText();
				if(!hasDocAdviceCode(ͣҽ������))
				{
					JOptionPane.showMessageDialog(DoctorLogin.frame,"����ѯ���ݿ�����������ҽ�����룡\n�������Ѿ����ڵ�ҽ������!","��ϢУ��",JOptionPane.INFORMATION_MESSAGE);
					 return;
				}
				String DocNo=getDocNo(ͣҽ��ҽ��);
				System.out.println(ͣҽ������);
				System.out.println(ͣҽ��ҽ��);
				System.out.println("ͣҽ��ҽ��NO="+DocNo);
				System.out.println(ͣҽ��ʱ��);
				int i=JOptionPane.showConfirmDialog(null,	"��ȷ��Ҫֹͣ��ҽ����?", "��ϢУ��", JOptionPane.YES_NO_OPTION);
				if(i==0)
				{
					updateDocAdvice(DocNo,ͣҽ������,ͣҽ��ʱ��);
					JOptionPane.showMessageDialog(null, "ͣҽ���ɹ���", "��ʾ", JOptionPane.ERROR_MESSAGE);
					textField.setText("");
				}
				
			}
		});
		button.setText("ִ��ֹͣҽ��");
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
				if (se1.equals("����һ")) {
					comboBox_1.removeAllItems();
					String SQL="select docName from doctor where officeNo='office51'";
					ComboxBoxAddItem(SQL);
				}
				if (se1.equals("���Ҷ�")) {
					comboBox_1.removeAllItems();
					String SQL="select docName from doctor where officeNo='office52'";
					ComboxBoxAddItem(SQL);
				}
				if (se1.equals("������")) {
					comboBox_1.removeAllItems();
					String SQL="select docName from doctor where officeNo='office53'";
					ComboxBoxAddItem(SQL);
				}
				// -----------------------------------------------------------------------
				if (se1.equals("������")) {
					comboBox_1.removeAllItems();
					String SQL="select docName from doctor where officeNo='office54'";
					ComboxBoxAddItem(SQL);
				}
				// ------------------------------------------------------------
				if (se1.equals("������")) {
					comboBox_1.removeAllItems();
					String SQL="select docName from doctor where officeNo='office55'";
					ComboxBoxAddItem(SQL);
				}

			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"����һ", "���Ҷ�", "������", "������", "������"}));
		add(comboBox);
		springLayout.putConstraint(SpringLayout.SOUTH, comboBox, -5, SpringLayout.NORTH, label_2);
		springLayout.putConstraint(SpringLayout.NORTH, comboBox, 160, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, comboBox, 0, SpringLayout.EAST, textField_1);
		springLayout.putConstraint(SpringLayout.WEST, comboBox, 0, SpringLayout.WEST, textField_1);

		final JLabel label_3 = new JLabel();
		label_3.setText("ͣҽ������");
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
	public String getDocNo(String ͣҽ��ҽ��)
	{
		String docNo=null;
		Connection conn=null;
	    ResultSet rs=null;
	    try{
	    conn=DBUtil.getConnection();
		PreparedStatement ptmt = conn.prepareStatement("select DocNo from doctor where docName=?");
		ptmt.setString(1, ͣҽ��ҽ��);
		rs = ptmt.executeQuery();
	    while(rs.next())
	    {
	    	docNo=rs.getString(1);
	    }
	    //conn.close();
	    }catch(SQLException e){
	       System.out.println("DoctorAdvideStop3!!!");
	       System.out.println("��ȡҽ��NOʱ����");
	       System.out.println(e.getMessage());
	    }
		return docNo;
	}
	public void updateDocAdvice(String ͣҽ��ҽNo,String ͣҽ������,String ͣҽ��ʱ��)
	{
		Connection conn=null;
	    ResultSet rs=null;
	    try{
	    conn=DBUtil.getConnection();
	    Statement stmt=conn.createStatement();
	    stmt.execute("update docAdvice set Advi_outtime='"+ͣҽ��ʱ��+"',Advi_outpers='"+ͣҽ��ҽNo+"' where Advi_No='"+ͣҽ������+"'");
	    //conn.close();
	    }catch(SQLException e){
	       System.out.println("DoctorAdvideStop4!!!");
	       System.out.println("����ʱ���ִ���");
	       System.out.println(e.getMessage());
	    }
	}
}
