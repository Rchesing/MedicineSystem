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
	private JTextArea ҽ������textArea;
	private JTextField textField_1;
	private JTextField ���˴���textField;
	private JComboBox comboBox;
	/**
	 * Create the panel
	 */
	public DoctorAdviceNew() {
		super();
		setLayout(null);

		final JLabel label = new JLabel();
		label.setBounds(15, 15, 145, 30);
		label.setText("����ҽ��");
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
				System.out.println("��ѡ����1"+docName);
				String docNoTemp=GetdocNo(docName);  //ͨ��ҽ������ȡ��ҽ��docNo
				String docNo=docNoTemp.trim();
				int docAdviceNumber=docAdviceNumber();  //��ȡҽ�����еļ�¼����Ϊβ��
				textField_3.setText(dateinsert+docNo + String.valueOf(docAdviceNumber));
			}
		});
		comboBox.setBounds(15, 45, 145, 25);
		add(comboBox);

		final JLabel label_1 = new JLabel();
		label_1.setBounds(190, 25, 115, 20);
		label_1.setText("���˴���");
		add(label_1);

		���˴���textField = new JTextField();
		���˴���textField.setBounds(190, 45, 130, 25);
		add(���˴���textField);

		final JLabel label_2 = new JLabel();
		label_2.setBounds(189, 78, 130, 30);
		label_2.setText("��ҽ��ʱ��");
		add(label_2);

		textField_1 = new JTextField();
		textField_1.setBounds(193, 107, 132, 25);
		textField_1.setEditable(false);
		add(textField_1);

		final JLabel label_4 = new JLabel();
		label_4.setBounds(15, 150, 145, 25);
		label_4.setText("ҽ������");
		add(label_4);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 180, 145, 80);
		add(scrollPane);

		ҽ������textArea = new JTextArea();
		scrollPane.setViewportView(ҽ������textArea);

		final JLabel label_5 = new JLabel();
		label_5.setBounds(190, 150, 130, 25);
		label_5.setText("����ҽ��");
		add(label_5);

		final JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(190, 180, 135, 80);
		add(scrollPane_1);

		textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);

		final JLabel label_6 = new JLabel();
		label_6.setBounds(16, 81, 125, 20);
		label_6.setText("ҽ������");
		add(label_6);

		textField_3 = new JTextField();
		textField_3.setBounds(13, 109, 146, 25);
		textField_3.setEditable(false);
		add(textField_3);

		final JButton button = new JButton();
		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(final MouseEvent e) {
				String ����ҽ��=comboBox.getSelectedItem().toString();
				String ���˴���=���˴���textField.getText();
				String ҽ������=ҽ������textArea.getText();
				String ҽ������=textField_3.getText();
				String ��ҽ��ʱ��=textField_1.getText();
				String ����ҽ��=textArea_1.getText();
				String docNoTemp=GetdocNo(����ҽ��);  //ͨ��ҽ������ȡ��ҽ��docNo
				String ����ҽ��No=docNoTemp.trim();
				if(���˴���.equals(""))
				{
					JOptionPane.showMessageDialog(DoctorLogin.frame,"�����벡�˴���!!!","��ϢУ��",JOptionPane.INFORMATION_MESSAGE);
					System.out.println("�����벡�˴���");
					return;
				}
				if(ҽ������.equals(""))
				{
					JOptionPane.showMessageDialog(DoctorLogin.frame,"ҽ�����ݲ���Ϊ��","��ϢУ��",JOptionPane.INFORMATION_MESSAGE);
					System.out.println("����ҽ������");
					return;
				}
				if(!hasSickCode(���˴���))
				{
					JOptionPane.showMessageDialog(DoctorLogin.frame,"�����������Ĳ��ˣ��޷�����ҽ��������\n ���������벡�˴���?","��ϢУ��",JOptionPane.INFORMATION_MESSAGE);
					System.out.println("����ҽ������");
					return;
				}
				if(hasDocAdvice(���˴���))
				{
					JOptionPane.showMessageDialog(DoctorLogin.frame,"�˲��������ݿ�������ҽ���ˣ�����Ҫ�ٴ�����\n ���������벡�˴���?","��ϢУ��",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if(textField_1.getText().equals(""))
				{
					JOptionPane.showMessageDialog(DoctorLogin.frame,"�����뿪ҽ����ʱ��!","��ϢУ��",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				//------------------------------------------
				System.out.println(����ҽ��No);
				System.out.println(���˴���);
				System.out.println(ҽ������);
				System.out.println(ҽ������);
				System.out.println(��ҽ��ʱ��);
				System.out.println(����ҽ��);
				//------------------------------------------
				String SQL="insert into docAdvice(Advi_No,Sick_No,Doc_No,Advi_ontime,Advi_content,Advi_fushu) values('"+ҽ������+"','"+���˴���+"','"+����ҽ��No+"','"+��ҽ��ʱ��+"','"+ҽ������+"','"+����ҽ��+"')";
				buildDocAdivce(SQL);
			}
		});
		button.setText("ҽ������");
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
	    //��ָ����ҽ��������docNO
	    rs=stmt.executeQuery("select docNo from doctor where docName='"+docName+"'");
	    while(rs.next())
	    {
	    	docNo=rs.getString(1);  //1��������ݿ���ȡ����һ���ֶ�ֵ
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
	    rs=stmt.executeQuery("select * from docAdvice");  //��ѯҽ�����еļ�¼��
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
	public boolean hasSickCode(String ���˴���)
	{
		int num=0;
		Connection conn=null;
	    ResultSet rs=null;
	    try{
	    conn=DBUtil.getConnection();
	    Statement stmt=conn.createStatement();
	    rs=stmt.executeQuery("select Sick_No from sick where Sick_No='"+���˴���+"'");  //��ѯҽ�����еļ�¼��
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
	    	return false;   //�����ڷ���false
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
		    JOptionPane.showMessageDialog(DoctorLogin.frame,"������������ҽ����������ѡ��ҽ��?","�ɹ�!",JOptionPane.INFORMATION_MESSAGE);
		    //conn.close();
		    }catch(SQLException ButtonEX){
		       System.out.println("Link Error");
		    }
		    ���˴���textField.setText("");
		    ҽ������textArea.setText("");
		    textArea_1.setText("");
		    textField_3.setText("");
		    textField_1.setText("");
	}
	public boolean hasDocAdvice(String ���˴���)
	{
		int num=0;
		Connection conn=null;
	    ResultSet rs=null;
	    try{
	    	conn=DBUtil.getConnection();
	    Statement stmt=conn.createStatement();
	    rs=stmt.executeQuery("select Sick_No from docAdvice where Sick_No='"+���˴���+"'");  //��ѯҽ�����еļ�¼��
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
