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

	private JTextField 病例号textField;
	private JTextField 病人姓名textField;
	private JComboBox jcbb1;
	private JComboBox comboBox;
	private JTable table;
	public static  DefaultTableModel dtm;
	/**
	 * Launch the application
	 * @param args
	 */
	private String[] columnNames={"科室","床号","病人姓名","病例号","记账号","押金","花费金额","所剩金额","需交押金"};
	 

	/**
	 * Create the frame
	 */
	public FeeManager() {
		super();
		final JLabel label_1 = new JLabel();
		final JLabel 病历号label_2 = new JLabel();
		getContentPane().setLayout(null);
		setTitle("费用管理");

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
		panel.setBorder(new TitledBorder(null, "科室", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel.setBounds(15, 175, 297, 168);
		getContentPane().add(panel);

		final JLabel label = new JLabel();
		label.setText("请选择科室");
		label.setBounds(92, 30, 90, 25);
		panel.add(label);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"科室一", "科室二", "科室三", "科室四", "科室五", "全部科室"}));
		comboBox.setBounds(75, 65, 130, 25);
		panel.add(comboBox);

		final JButton button_1 = new JButton();
		button_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(final MouseEvent e) {
				//-----------------------------------------------
				int rowcount = dtm.getRowCount() - 1;
				if (rowcount != -1) {
					for (int i = rowcount; i >= 0; i--) {
						dtm.removeRow(i); // 删除Jtable中的所有行
					}
					dtm.setRowCount(0); // 将Jtable中的行数设为零
				}		
				String se1 = (String)comboBox.getSelectedItem();
				if (se1.equals("科室一")) {
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
				if (se1.equals("科室二")) {
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
				if (se1.equals("科室三")) {
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
				if (se1.equals("科室四")) {
					String SQL="select room.officeNo,bedNo,sickcase.sickName,"
							+ "sickcase.caseID,foregift.accountNo,"
							+ "foregift.hand,foregift.cost,foregift.leave,foregift.need"
							+ " from foregift,sick,room,sickcase,doctor "
							+ "where sickcase.docName=doctor.docName "
							+ "and sick.docNo=doctor.docNo and sick.roomNo=room.roomNo "
							+ "and foregift.medicalNo=sick.medicalNo and "
							+ "doctor.officeNo=room.officeNo and room.officeNo='office54'";
					System.out.println("科室四");
					printInfo(SQL);
				}
				// ------------------------------------------------------------
				if (se1.equals("科室五")) {
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
				if(se1.equals("全部科室"))
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
		button_1.setText("显示");
		button_1.setBounds(88, 109, 100, 30);
		panel.add(button_1);

		final JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "查询", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
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
							dtm.removeRow(i); // 删除Jtable中的所有行
						}
						dtm.setRowCount(0); // 将Jtable中的行数设为零
					}		
				   //=================================================
				   if(selectNamecontent.equals("请选择查询种类"))
				   {
					   
					   label_1.setVisible(false);
					   病人姓名textField.setVisible(false);
					   病历号label_2.setVisible(false);
					   病例号textField.setVisible(false);
					   System.out.println("什么也不做");
					   return;
				   }
				   else if(selectNamecontent.equals("病人姓名"))
				   {
					   label_1.setVisible(true);
					   病人姓名textField.setVisible(true);
					   病历号label_2.setVisible(false);
					   病例号textField.setVisible(false);
					   return;
				   }else if(selectNamecontent.equals("病例号"))
				   {
					   label_1.setVisible(false);
					   病人姓名textField.setVisible(false);
					   病历号label_2.setVisible(true);
					   病例号textField.setVisible(true);
					   return;
				   }
				   //++++++++++++++++++++++++++++++++++++++++++++++++++
			}
		});
		jcbb1.setModel(new DefaultComboBoxModel(new String[] {"请选择查询种类", "病人姓名", "病例号"}));
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
							dtm.removeRow(i); // 删除Jtable中的所有行
						}
						dtm.setRowCount(0); // 将Jtable中的行数设为零
					}		
				   //-----------------------------------------------------
				   if(selectNamecontent.equals("请选择查询种类"))
				   {
					   System.out.println("什么也不做");
					   return;
				   }
				   else if(selectNamecontent.equals("病人姓名"))
				   {
					   if(病人姓名textField.getText().equals(""))
					   {
						   JOptionPane.showMessageDialog(DoctorLogin.frame,"  请输入病人姓名！","注意",JOptionPane.ERROR_MESSAGE);
						   return;
					   }
					   String sickName=病人姓名textField.getText().trim();
						String SQL="select room.officeNo,bedNo,sickcase.sickName,"
								+ "sickcase.caseID,foregift.accountNo,foregift.hand,foregift.cost,"
								+ "foregift.leave,foregift.need from foregift,sick,room,"
								+ "sickcase,doctor where sickcase.docName=doctor.docName "
								+ "and sick.docNo=doctor.docNo and sick.roomNo=room.roomNo "
								+ "and foregift.medicalNo=sick.medicalNo and doctor.officeNo=room.officeNo "
								+ "and sickcase.sickName=?;";
						databaseSearch(SQL,9,sickName);
					   System.out.println(病人姓名textField.getText());
					   return;
				   }else if(selectNamecontent.equals("病例号"))
				   {
					   if(病例号textField.getText().equals(""))
					   {
						   JOptionPane.showMessageDialog(DoctorLogin.frame,"  请输入病历号！","注意",JOptionPane.ERROR_MESSAGE);
						   return;
					   }
					   String sickCaseID=病例号textField.getText().trim();
					   System.out.println("sickCaseID"+sickCaseID);
						String SQL="select room.officeNo,bedNo,sickcase.sickName,"
								+ "sickcase.caseID,foregift.accountNo,foregift.hand,foregift.cost,"
								+ "foregift.leave,foregift.need from foregift,sick,room,"
								+ "sickcase,doctor where sickcase.docName=doctor.docName "
								+ "and sick.docNo=doctor.docNo and sick.roomNo=room.roomNo "
								+ "and foregift.medicalNo=sick.medicalNo and doctor.officeNo=room.officeNo "
								+ "and sickcase.caseID=?;";
						databaseSearch(SQL,9,sickCaseID);
					   System.out.println(病例号textField.getText());
					   return;
				   }
			}
		});
		button.setText("查询");
		button.setBounds(118, 126, 83, 30);
		panel_1.add(button);

		//final JLabel label_1 = new JLabel();
		label_1.setVisible(false);
		label_1.setText("病人姓名");
		label_1.setBounds(125, 57, 95, 25);
		panel_1.add(label_1);

		病人姓名textField = new JTextField();
		病人姓名textField.setVisible(false);
		病人姓名textField.setBounds(94, 83, 138, 25);
		panel_1.add(病人姓名textField);

		//final JLabel 病历号label_2 = new JLabel();
		病历号label_2.setText("病例号");
		病历号label_2.setVisible(false);
		病历号label_2.setBounds(132, 58, 64, 25);
		panel_1.add(病历号label_2);

		病例号textField = new JTextField();
		病例号textField.setVisible(false);
		病例号textField.setBounds(95, 85, 135, 25);
		panel_1.add(病例号textField);
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
						data[j - 1] = rs.getString(j); // 取出数据库中的数组装载到数组中
					}
			    	dtm.addRow(data); // 在Jtable中显示取出的数据
		       }
		       if(i==0)
			    {
			    	JOptionPane.showMessageDialog(DoctorLogin.frame, " 您查找的内容不存在！",
							"注意", JOptionPane.INFORMATION_MESSAGE);
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
				data[j - 1] = rs.getString(j); // 取出数据库中的数组装载到数组中
			    
			}
			dtm.addRow(data); // 在Jtabl
		}
	    //conn.close();
	    }catch(SQLException e){
	       System.out.println("FeeManager2!!!!!");
	    }
	}
}
