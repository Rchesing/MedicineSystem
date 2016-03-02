package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class FeeManager extends JFrame {

	private JTextField ������textField;
	private JTextField ��������textField;
	private JComboBox jcbb1;
	private JComboBox comboBox;
	private JTable table;
	public static  DefaultTableModel dtm;
	/**
	 * Launch the application
	 * @param args
	 */
	private String[] columnNames={"����","����","��������","������","���˺�","Ѻ��","���ѽ��","��ʣ���","�轻Ѻ��"};
	 

	/**
	 * Create the frame
	 */
	public FeeManager() {
		super();
		final JLabel label_1 = new JLabel();
		final JLabel ������label_2 = new JLabel();
		getContentPane().setLayout(null);
		setTitle("���ù���");

	    setSize(650,400);
		setResizable(false);
		setLocationRelativeTo(null);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 636, 151);
		getContentPane().add(scrollPane);
		
		dtm=new DefaultTableModel(columnNames,0);
		table = new JTable(dtm);
		scrollPane.setViewportView(table);

		final JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "����", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel.setBounds(15, 175, 297, 168);
		getContentPane().add(panel);

		final JLabel label = new JLabel();
		label.setText("��ѡ�����");
		label.setBounds(92, 30, 90, 25);
		panel.add(label);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"����һ", "���Ҷ�", "������", "������", "������", "ȫ������"}));
		comboBox.setBounds(75, 65, 130, 25);
		panel.add(comboBox);

		final JButton button_1 = new JButton();
		button_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(final MouseEvent e) {
				//-----------------------------------------------
				int rowcount = dtm.getRowCount() - 1;
				if (rowcount != -1) {
					for (int i = rowcount; i >= 0; i--) {
						dtm.removeRow(i); // ɾ��Jtable�е�������
					}
					dtm.setRowCount(0); // ��Jtable�е�������Ϊ��
				}		
				String se1 = (String)comboBox.getSelectedItem();
				if (se1.equals("����һ")) {
					String SQL="select room.officeNo,bedNo,sickcase.sickName,"
							+ "sickcase.caseID,foregift.accountNo,"
							+ "foregift.hand,foregift.cost,foregift.leave,foregift.need"
							+ " from foregift,sick,room,sickcase,doctor "
							+ "where sickcase.docName=doctor.docName "
							+ "and sick.docNo=doctor.docNo and sick.roomNo=room.roomNo "
							+ "and foregift.medicalNo=sick.medicalNo and "
							+ "doctor.officeNo=room.officeNo and room.officeNo='office51'";
					printInfo(SQL);
				}
				if (se1.equals("���Ҷ�")) {
					String SQL="select room.officeNo,bedNo,sickcase.sickName,"
							+ "sickcase.caseID,foregift.accountNo,"
							+ "foregift.hand,foregift.cost,foregift.leave,foregift.need"
							+ " from foregift,sick,room,sickcase,doctor "
							+ "where sickcase.docName=doctor.docName "
							+ "and sick.docNo=doctor.docNo and sick.roomNo=room.roomNo "
							+ "and foregift.medicalNo=sick.medicalNo and "
							+ "doctor.officeNo=room.officeNo and room.officeNo='office52'";
					printInfo(SQL);
				}
				if (se1.equals("������")) {
					String SQL="select room.officeNo,bedNo,sickcase.sickName,"
							+ "sickcase.caseID,foregift.accountNo,"
							+ "foregift.hand,foregift.cost,foregift.leave,foregift.need"
							+ " from foregift,sick,room,sickcase,doctor "
							+ "where sickcase.docName=doctor.docName "
							+ "and sick.docNo=doctor.docNo and sick.roomNo=room.roomNo "
							+ "and foregift.medicalNo=sick.medicalNo and "
							+ "doctor.officeNo=room.officeNo and room.officeNo='office53'";
					printInfo(SQL);
				}
				// -----------------------------------------------------------------------
				if (se1.equals("������")) {
					String SQL="select room.officeNo,bedNo,sickcase.sickName,"
							+ "sickcase.caseID,foregift.accountNo,"
							+ "foregift.hand,foregift.cost,foregift.leave,foregift.need"
							+ " from foregift,sick,room,sickcase,doctor "
							+ "where sickcase.docName=doctor.docName "
							+ "and sick.docNo=doctor.docNo and sick.roomNo=room.roomNo "
							+ "and foregift.medicalNo=sick.medicalNo and "
							+ "doctor.officeNo=room.officeNo and room.officeNo='office54'";
					System.out.println("������");
					printInfo(SQL);
				}
				// ------------------------------------------------------------
				if (se1.equals("������")) {
					String SQL="select room.officeNo,bedNo,sickcase.sickName,"
							+ "sickcase.caseID,foregift.accountNo,"
							+ "foregift.hand,foregift.cost,foregift.leave,foregift.need"
							+ " from foregift,sick,room,sickcase,doctor "
							+ "where sickcase.docName=doctor.docName "
							+ "and sick.docNo=doctor.docNo and sick.roomNo=room.roomNo "
							+ "and foregift.medicalNo=sick.medicalNo and "
							+ "doctor.officeNo=room.officeNo and room.officeNo='office55'";
					printInfo(SQL);
				}
				if(se1.equals("ȫ������"))
				{
					String SQL="select room.officeNo,bedNo,sickcase.sickName,"
							+ "sickcase.caseID,foregift.accountNo,"
							+ "foregift.hand,foregift.cost,foregift.leave,foregift.need"
							+ " from foregift,sick,room,sickcase,doctor "
							+ "where sickcase.docName=doctor.docName "
							+ "and sick.docNo=doctor.docNo and sick.roomNo=room.roomNo "
							+ "and foregift.medicalNo=sick.medicalNo and "
							+ "doctor.officeNo=room.officeNo";
					printInfo(SQL);
				}
				//-----------------------------------------------
			}
		});
		button_1.setText("��ʾ");
		button_1.setBounds(88, 109, 100, 30);
		panel.add(button_1);

		final JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "��ѯ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_1.setBounds(314, 175, 316, 170);
		getContentPane().add(panel_1);

		jcbb1 = new JComboBox();
		jcbb1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				Object SelectName=jcbb1.getSelectedItem();
				   String selectNamecontent=SelectName.toString();
				   int rowcount = dtm.getRowCount() - 1;
					if (rowcount != -1) {
						for (int i = rowcount; i >= 0; i--) {
							dtm.removeRow(i); // ɾ��Jtable�е�������
						}
						dtm.setRowCount(0); // ��Jtable�е�������Ϊ��
					}		
				   //=================================================
				   if(selectNamecontent.equals("��ѡ���ѯ����"))
				   {
					   
					   label_1.setVisible(false);
					   ��������textField.setVisible(false);
					   ������label_2.setVisible(false);
					   ������textField.setVisible(false);
					   System.out.println("ʲôҲ����");
					   return;
				   }
				   else if(selectNamecontent.equals("��������"))
				   {
					   label_1.setVisible(true);
					   ��������textField.setVisible(true);
					   ������label_2.setVisible(false);
					   ������textField.setVisible(false);
					   return;
				   }else if(selectNamecontent.equals("������"))
				   {
					   label_1.setVisible(false);
					   ��������textField.setVisible(false);
					   ������label_2.setVisible(true);
					   ������textField.setVisible(true);
					   return;
				   }
				   //++++++++++++++++++++++++++++++++++++++++++++++++++
			}
		});
		jcbb1.setModel(new DefaultComboBoxModel(new String[] {"��ѡ���ѯ����", "��������", "������"}));
		jcbb1.setBounds(94, 28, 135, 25);
		panel_1.add(jcbb1);

		final JButton button = new JButton();
		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(final MouseEvent e) {
				System.out.println("Good idea!!!");
				   Object SelectName=jcbb1.getSelectedItem();
				   String selectNamecontent=SelectName.toString();
				   //----------------------------------------------------
				   int rowcount = dtm.getRowCount() - 1;
					if (rowcount != -1) {
						for (int i = rowcount; i >= 0; i--) {
							dtm.removeRow(i); // ɾ��Jtable�е�������
						}
						dtm.setRowCount(0); // ��Jtable�е�������Ϊ��
					}		
				   //-----------------------------------------------------
				   if(selectNamecontent.equals("��ѡ���ѯ����"))
				   {
					   System.out.println("ʲôҲ����");
					   return;
				   }
				   else if(selectNamecontent.equals("��������"))
				   {
					   if(��������textField.getText().equals(""))
					   {
						   JOptionPane.showMessageDialog(DoctorLogin.frame,"  �����벡��������","ע��",JOptionPane.ERROR_MESSAGE);
						   return;
					   }
					   String sickName=��������textField.getText().trim();
						String SQL="select room.officeNo,bedNo,sickcase.sickName,"
								+ "sickcase.caseID,foregift.accountNo,foregift.hand,foregift.cost,"
								+ "foregift.leave,foregift.need from foregift,sick,room,"
								+ "sickcase,doctor where sickcase.docName=doctor.docName "
								+ "and sick.docNo=doctor.docNo and sick.roomNo=room.roomNo "
								+ "and foregift.medicalNo=sick.medicalNo and doctor.officeNo=room.officeNo "
								+ "and sickcase.sickName=?;";
						databaseSearch(SQL,9,sickName);
					   System.out.println(��������textField.getText());
					   return;
				   }else if(selectNamecontent.equals("������"))
				   {
					   if(������textField.getText().equals(""))
					   {
						   JOptionPane.showMessageDialog(DoctorLogin.frame,"  �����벡���ţ�","ע��",JOptionPane.ERROR_MESSAGE);
						   return;
					   }
					   String sickCaseID=������textField.getText().trim();
					   System.out.println("sickCaseID"+sickCaseID);
						String SQL="select room.officeNo,bedNo,sickcase.sickName,"
								+ "sickcase.caseID,foregift.accountNo,foregift.hand,foregift.cost,"
								+ "foregift.leave,foregift.need from foregift,sick,room,"
								+ "sickcase,doctor where sickcase.docName=doctor.docName "
								+ "and sick.docNo=doctor.docNo and sick.roomNo=room.roomNo "
								+ "and foregift.medicalNo=sick.medicalNo and doctor.officeNo=room.officeNo "
								+ "and sickcase.caseID=?;";
						databaseSearch(SQL,9,sickCaseID);
					   System.out.println(������textField.getText());
					   return;
				   }
			}
		});
		button.setText("��ѯ");
		button.setBounds(118, 126, 83, 30);
		panel_1.add(button);

		//final JLabel label_1 = new JLabel();
		label_1.setVisible(false);
		label_1.setText("��������");
		label_1.setBounds(125, 57, 95, 25);
		panel_1.add(label_1);

		��������textField = new JTextField();
		��������textField.setVisible(false);
		��������textField.setBounds(94, 83, 138, 25);
		panel_1.add(��������textField);

		//final JLabel ������label_2 = new JLabel();
		������label_2.setText("������");
		������label_2.setVisible(false);
		������label_2.setBounds(132, 58, 64, 25);
		panel_1.add(������label_2);

		������textField = new JTextField();
		������textField.setVisible(false);
		������textField.setBounds(95, 85, 135, 25);
		panel_1.add(������textField);
		//
	}
	private void databaseSearch(String SQL,int num,String str)
	  {
		  Connection conn=null;
		  ResultSet rs=null;
		    try{	
		    	System.out.println("aaaaaaaaaaaaaFeeManager1!!!!!");
				conn = DBUtil.getConnection();
				PreparedStatement ptmt = conn.prepareStatement(SQL);
				ptmt.setString(1, str);
				rs = ptmt.executeQuery();

		       int i=0;
		       String[] data = new String[num];
		       while(rs.next())
		       {
		    	   i++;
			    	for (int j = 1; j <= num; j++) {
						data[j - 1] = rs.getString(j); // ȡ�����ݿ��е�����װ�ص�������
					}
			    	dtm.addRow(data); // ��Jtable����ʾȡ��������
		       }
		       if(i==0)
			    {
			    	JOptionPane.showMessageDialog(DoctorLogin.frame, " �����ҵ����ݲ����ڣ�",
							"ע��", JOptionPane.INFORMATION_MESSAGE);
			    }
		    ////conn.close();
		    }catch(SQLException e){
		       System.out.println("FeeManager1!!!!!");
		    }
	  }
	public void printInfo(String SQL)
	{
		Connection conn=null;
	    ResultSet rs=null;
	    try{
	    conn=DBUtil.getConnection();
	    Statement stmt=conn.createStatement();
	    rs=stmt.executeQuery(SQL);
	    String[] data = new String[9];
		while (rs.next()) {
			for (int j = 1; j <= 9; j++) {
				data[j - 1] = rs.getString(j); // ȡ�����ݿ��е�����װ�ص�������
			    
			}
			dtm.addRow(data); // ��Jtabl
		}
	    //conn.close();
	    }catch(SQLException e){
	       System.out.println("FeeManager2!!!!!");
	    }
	}
}
