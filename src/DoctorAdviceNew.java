package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class DoctorAdviceNew extends JPanel {

	private JTextField textField_3;
	private JTextArea textArea_1;
	private JTextArea 医嘱内容textArea;
	private JTextField textField_1;
	private JTextField 病人代码textField;
	private JComboBox comboBox;
	/**
	 * Create the panel
	 */
	public DoctorAdviceNew() {
		super();
		setLayout(null);

		final JLabel label = new JLabel();
		label.setBounds(15, 15, 145, 30);
		label.setText("立嘱医生");
		add(label);

		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				Calendar rightNow = Calendar.getInstance();
				int year=rightNow.get(Calendar.YEAR);
				int month=rightNow.get(Calendar.MONTH)+1;
				int day=rightNow.get(Calendar.DAY_OF_MONTH);
				String dateinsert=String.valueOf(year)+String.valueOf(month)+String.valueOf(day);
				JComboBox cb = (JComboBox) e.getSource();
				String docName = (String) cb.getSelectedItem();
				String Startdocadvice=String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(day);
				textField_1.setText(Startdocadvice);
				//textField_3.setText(dateinsert);
				System.out.println("你选择了1"+docName);
				String docNoTemp=GetdocNo(docName);  //通过医生姓名取得医生docNo
				String docNo=docNoTemp.trim();
				int docAdviceNumber=docAdviceNumber();  //获取医嘱表中的记录数做为尾数
				textField_3.setText(dateinsert+docNo + String.valueOf(docAdviceNumber));
			}
		});
		comboBox.setBounds(15, 45, 145, 25);
		add(comboBox);

		final JLabel label_1 = new JLabel();
		label_1.setBounds(190, 25, 115, 20);
		label_1.setText("病人代码");
		add(label_1);

		病人代码textField = new JTextField();
		病人代码textField.setBounds(190, 45, 130, 25);
		add(病人代码textField);

		final JLabel label_2 = new JLabel();
		label_2.setBounds(189, 78, 130, 30);
		label_2.setText("开医嘱时间");
		add(label_2);

		textField_1 = new JTextField();
		textField_1.setBounds(193, 107, 132, 25);
		textField_1.setEditable(false);
		add(textField_1);

		final JLabel label_4 = new JLabel();
		label_4.setBounds(15, 150, 145, 25);
		label_4.setText("医嘱内容");
		add(label_4);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 180, 145, 80);
		add(scrollPane);

		医嘱内容textArea = new JTextArea();
		scrollPane.setViewportView(医嘱内容textArea);

		final JLabel label_5 = new JLabel();
		label_5.setBounds(190, 150, 130, 25);
		label_5.setText("附属医嘱");
		add(label_5);

		final JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(190, 180, 135, 80);
		add(scrollPane_1);

		textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);

		final JLabel label_6 = new JLabel();
		label_6.setBounds(16, 81, 125, 20);
		label_6.setText("医嘱代码");
		add(label_6);

		textField_3 = new JTextField();
		textField_3.setBounds(13, 109, 146, 25);
		textField_3.setEditable(false);
		add(textField_3);

		final JButton button = new JButton();
		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(final MouseEvent e) {
				String 立嘱医生=comboBox.getSelectedItem().toString();
				String 病人代码=病人代码textField.getText();
				String 医嘱内容=医嘱内容textArea.getText();
				String 医嘱代码=textField_3.getText();
				String 开医嘱时间=textField_1.getText();
				String 附属医嘱=textArea_1.getText();
				String docNoTemp=GetdocNo(立嘱医生);  //通过医生姓名取得医生docNo
				String 立嘱医生No=docNoTemp.trim();
				if(病人代码.equals(""))
				{
					JOptionPane.showMessageDialog(DoctorLogin.frame,"请输入病人代码!!!","信息校验",JOptionPane.INFORMATION_MESSAGE);
					System.out.println("请输入病人代码");
					return;
				}
				if(医嘱内容.equals(""))
				{
					JOptionPane.showMessageDialog(DoctorLogin.frame,"医嘱内容不能为空","信息校验",JOptionPane.INFORMATION_MESSAGE);
					System.out.println("请输医嘱内容");
					return;
				}
				if(!hasSickCode(病人代码))
				{
					JOptionPane.showMessageDialog(DoctorLogin.frame,"不存在这样的病人，无法创建医嘱！！！\n 请重新输入病人代码?","信息校验",JOptionPane.INFORMATION_MESSAGE);
					System.out.println("请输医嘱内容");
					return;
				}
				if(hasDocAdvice(病人代码))
				{
					JOptionPane.showMessageDialog(DoctorLogin.frame,"此病人在数据库中已有医嘱了，不需要再创建！\n 请重新输入病人代码?","信息校验",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if(textField_1.getText().equals(""))
				{
					JOptionPane.showMessageDialog(DoctorLogin.frame,"请输入开医嘱的时间!","信息校验",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				//------------------------------------------
				System.out.println(立嘱医生No);
				System.out.println(病人代码);
				System.out.println(医嘱内容);
				System.out.println(医嘱代码);
				System.out.println(开医嘱时间);
				System.out.println(附属医嘱);
				//------------------------------------------
				String SQL="insert into docAdvice(Advi_No,Sick_No,Doc_No,Advi_ontime,Advi_content,Advi_fushu) values('"+医嘱代码+"','"+病人代码+"','"+立嘱医生No+"','"+开医嘱时间+"','"+医嘱内容+"','"+附属医嘱+"')";
				buildDocAdivce(SQL);
			}
		});
		button.setText("医嘱生成");
		button.setBounds(130, 275, 90, 25);
		add(button);

		final JLabel label_3 = new JLabel();
		label_3.setIcon(SwingResourceManager.getIcon(DoctorAdviceNew.class, "images/doctor.gif"));
		label_3.setText("New JLabel");
		label_3.setBounds(335, 40, 115, 215);
		add(label_3);
		initDatabase();
		//
	}
	public void initDatabase()
 {
		Connection conn = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery("select docName from doctor");
			while (rs.next()) {
				comboBox.addItem(rs.getString(1));
			}
			//conn.close();
		} catch (SQLException e){
	       System.out.println("Link Error");
	       System.out.println(e.getMessage());
	    }
	}
	public String GetdocNo(String docName)
	{
		String docNo=null;
		Connection conn=null;
	    ResultSet rs=null;
	    try{
	    conn=DBUtil.getConnection();
	    Statement stmt=conn.createStatement();
	    //按指定的医生名查找docNO
	    rs=stmt.executeQuery("select docNo from doctor where docName='"+docName+"'");
	    while(rs.next())
	    {
	    	docNo=rs.getString(1);  //1代表从数据库中取出第一个字段值
	    }
	    //conn.close();
	    }catch(SQLException e){
	       System.out.println("Link Error");
	       System.out.println(e.getMessage());
	    }
		return docNo;
	}
	public int docAdviceNumber()
	{	int docAdviceNumber=0;
		Connection conn=null;
	    ResultSet rs=null;
	    try{
	    conn=DBUtil.getConnection();
	    Statement stmt=conn.createStatement();
	    rs=stmt.executeQuery("select * from docAdvice");  //查询医嘱表中的记录数
	    while(rs.next())
	    {
	    	docAdviceNumber++;
	    }
	    //conn.close();
	    }catch(SQLException e){
	       System.out.println("Link Error");
	       System.out.println(e.getMessage());
	    }
		return docAdviceNumber;
	}
	public boolean hasSickCode(String 病人代码)
	{
		int num=0;
		Connection conn=null;
	    ResultSet rs=null;
	    try{
	    conn=DBUtil.getConnection();
	    Statement stmt=conn.createStatement();
	    rs=stmt.executeQuery("select Sick_No from sick where Sick_No='"+病人代码+"'");  //查询医嘱表中的记录数
	    while(rs.next())
	    {
	    	num++;
	    }
	    //conn.close();
	    }catch(SQLException e){
	       System.out.println("Link Error");
	       System.out.println(e.getMessage());
	    }
	    if(num==0)
	    {
	    	return false;   //不存在返回false
	    }else
	    {
	    	return true;
	    }
	}
	public void buildDocAdivce(String SQL)
	{
		ResultSet rs=null;
		Connection conn=null;
		    try{
		    conn=DBUtil.getConnection();
		    Statement stmt=conn.createStatement();
		    stmt.executeUpdate(SQL);
		    JOptionPane.showMessageDialog(DoctorLogin.frame,"如果想继续生成医嘱，请重新选择医生?","成功!",JOptionPane.INFORMATION_MESSAGE);
		    //conn.close();
		    }catch(SQLException ButtonEX){
		       System.out.println("Link Error");
		    }
		    病人代码textField.setText("");
		    医嘱内容textArea.setText("");
		    textArea_1.setText("");
		    textField_3.setText("");
		    textField_1.setText("");
	}
	public boolean hasDocAdvice(String 病人代码)
	{
		int num=0;
		Connection conn=null;
	    ResultSet rs=null;
	    try{
	    	conn=DBUtil.getConnection();
	    Statement stmt=conn.createStatement();
	    rs=stmt.executeQuery("select Sick_No from docAdvice where Sick_No='"+病人代码+"'");  //查询医嘱表中的记录数
	    while(rs.next())
	    {
	    	num++;
	    }
	    //conn.close();
	    }catch(SQLException e){
	       System.out.println("Link Error");
	       System.out.println(e.getMessage());
	    }
		if(num!=0)
		{
			return true;
		}else
		{
			return false;
		}
	}
}
