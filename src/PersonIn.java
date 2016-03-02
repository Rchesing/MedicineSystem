//�ٴ�����վ ������Ϣ��¼���
package src;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.*;
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.*;

public class PersonIn extends JPanel {
	JComboBox comboBox = null;	
	JComboBox sex = null;
	String docNo;
	String SSex;
	String [] strArray = new String [20];
	JTextField number0 = new JTextField(15);
	JTextField number1 = new JTextField(15);
	JTextField number2 = new JTextField(15);
	JTextField number3 = new JTextField(15);
	JTextField number4 = new JTextField(15);
	JTextField number5 = new JTextField(15);
	JTextField number7 = new JTextField(15);
	int medNumber = Number();
	int seakNumber = Number();	
	Calendar rightNow = Calendar.getInstance();
	int year=rightNow.get(Calendar.YEAR);
	String medTime=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
	public PersonIn() throws SQLException {

		setLayout(new BorderLayout());
		// ������Ϣ��¼Demo
		JPanel jp = new JPanel(new GridLayout(1, 4));
		jp.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		jp.setBorder(BorderFactory.createBevelBorder(1, new Color(45, 92, 162), new Color(43, 66, 97),
				new Color(45, 92, 162), new Color(84, 123, 200)));

		final JLabel jp13 = new JLabel();
		jp.add(jp13);
		JPanel jp11 = new JPanel(new GridLayout(6, 1));
		jp.add(jp11);
		JPanel jp12 = new JPanel(new GridLayout(6, 1));
		jp.add(jp12);
		JPanel jp0 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel jp1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel jp2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel jp3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel jp4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel jp5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel jp6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel jp7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel jp8 = new JPanel(new FlowLayout(FlowLayout.LEFT));
				
		JPanel jp101 = new JPanel(new GridLayout(2, 1));
		jp101.add(new JLabel("���˴���         "));
		System.out.println(seakNumber);
		number0.setText(year + "0000" + String.valueOf(seakNumber));
		number0.setEditable(false);
		jp0.add(number0);
		jp101.add(jp0);
		jp11.add(jp101);
		
		JPanel jp111 = new JPanel(new GridLayout(2, 1));
		jp111.add(new JLabel("������         "));

		number1.setText( "Mdoc" + year + "0000" + String.valueOf(medNumber));
		number1.setEditable(false);
		jp1.add(number1);
		jp111.add(jp1);
		jp11.add(jp111);

		
		JPanel jp121 = new JPanel(new GridLayout(2, 1));
		jp121.add(new JLabel("��   ��  "));

		jp2.add(number2);
		jp121.add(jp2);
		jp12.add(jp121);

		JPanel jp112 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		String[] sexs = { "��", "Ů" };
		sex = new JComboBox(sexs);
		jp112.add(new JLabel("��    ��             "));
		sex.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if(sex.getSelectedItem().equals("��")){
					SSex="��";
				}
				else SSex="Ů";
			}
		});
		SSex= sex.getSelectedItem().toString();
		jp112.add(sex);
		jp11.add(jp112);
		JPanel jp122 = new JPanel(new GridLayout(2, 1));
		
		jp122.add(new JLabel("��������    "));
		jp4.add(number3);
		jp122.add(jp4);
		jp12.add(jp122);

		JPanel jp113 = new JPanel(new GridLayout(2, 1));
		jp113.add(new JLabel("��   ��  "));
		jp5.add(number4);
		jp113.add(jp5);
		jp11.add(jp113);
		JPanel jp123 = new JPanel(new GridLayout(2, 1));
		jp123.add(new JLabel("��ϵ�绰   "));
		jp6.add(number5);
		jp123.add(jp6);
		jp12.add(jp123);

		JPanel jp114 = new JPanel(new GridLayout(2, 1));
		jp114.add(new JLabel("���ҽ��"));
		Connection conn = DBUtil.getConnection();
	    Statement stmt=conn.createStatement();
	    String SQL = "select docName from doctor";
	    ResultSet rs=stmt.executeQuery(SQL);
		for(int i = 0;rs.next()==true;i++){
			String docName = rs.getString(1);
			strArray[i]=docName;
			//System.out.println(strArray[i]);
		}
		comboBox = new JComboBox(strArray);
		jp7.add(comboBox);
		jp114.add(jp7);
		jp11.add(jp114);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				Connection conn = DBUtil.getConnection();
			    try {
					Statement stmt=conn.createStatement();
					//System.out.println(comboBox.getSelectedItem());
					int i=0;
					for(i=0;i<strArray.length;i++){
						if(comboBox.getSelectedItem().equals(strArray[i]))
							break;
					}
					String SQL = "select docNo from doctor where docName="+"'"+strArray[i]+"'";
					System.out.println(SQL);
					ResultSet rs=stmt.executeQuery(SQL);
					while(rs.next()){
						docNo=rs.getString(1);
						System.out.println(docNo);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println();
			}
		});
		
		
		JPanel jp124 = new JPanel(new GridLayout(2, 1));
		jp124.add(new JLabel("��ͥסַ"));
		jp8.add(number7);
		jp124.add(jp8);
		jp12.add(jp124);

		JPanel jp125 = new JPanel(new GridLayout(1, 1));
		JButton loading = new JButton("¼��");
		jp3.add(loading);
		jp125.add(jp3);
		jp12.add(jp125);
		add(jp);

		loading.addMouseListener(new MouseAdapter() {
			public void mouseClicked(final MouseEvent e) {
				String ���˴��� = number0.getText();
				String ������ = number1.getText();
				String ���� = number2.getText();
				String �������� = number3.getText();
				String ���� = number4.getText();
				if(����.equals(""))
				{
					JOptionPane.showMessageDialog(DoctorLogin.frame,"����������!!!","��ϢУ��",JOptionPane.INFORMATION_MESSAGE);
					System.out.println("����������");
					return;
				}
				if(��������.equals(""))
				{
					JOptionPane.showMessageDialog(DoctorLogin.frame,"�������²���Ϊ��","��ϢУ��",JOptionPane.INFORMATION_MESSAGE);
					System.out.println("�����������");
					return;
				}
				if(����.equals(""))
				{
					JOptionPane.showMessageDialog(DoctorLogin.frame,"���䲻��Ϊ��","��ϢУ��",JOptionPane.INFORMATION_MESSAGE);
					System.out.println("��������");
					return;
				}
				//------------------------------------------
				//------------------------------------------
				System.out.println(���˴���);
				System.out.println(������);
				String SQL="insert into sick(Sick_No,medicalNo,sickName,"
						+ "sex,age,medTime,birthday,docNo) values('"+���˴���+"','"+������+"','"+����+"','"+SSex+"','"+����+"','"+medTime+"','"+��������+"','"+docNo+"')";
				System.out.println(SQL);
				Connection conn=null;
				    try{
				    conn=DBUtil.getConnection();
				    Statement stmt=conn.createStatement();
				    stmt.executeUpdate(SQL);
				    JOptionPane.showMessageDialog(DoctorLogin.frame,"����������Ӳ��˵�������������������?","�ɹ�!",JOptionPane.INFORMATION_MESSAGE);
				    //conn.close();
				    }catch(SQLException ButtonEX){
				       System.out.println("Link Error������Person��");
						System.out.println(ButtonEX.getMessage());
				    }
				    medNumber = Number();
					number1.setText( "Mdoc" + year + "0000" + String.valueOf(medNumber));
					seakNumber = Number();
					number0.setText(year + "0000" + String.valueOf(seakNumber));
				    number2.setText("");
				    number3.setText("");
				    number4.setText("");
				    number5.setText("");
				    number7.setText("");
				    
			}
		});
		
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
	
	public int Number() {
		int Number = 0;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from sick"); // ��ѯҽ�����еļ�¼��
			while (rs.next()) {
				Number++;
			}
			// conn.close();
		} catch (SQLException e) {
			System.out.println("Link Error");
			System.out.println(e.getMessage());
		}
		return Number;
	}
}
