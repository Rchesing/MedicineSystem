package src;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ButtonGroup;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

public class SearchSickInfo extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField_3;
	private JTextField textField_2;
	private JTextField textField_1;
	private JTextField textField;
	private JTable table;
	private SpringLayout springLayout;
	private DefaultTableModel dtm;
	private JRadioButton radioButton = new JRadioButton();
	private JRadioButton radioButton_1 = new JRadioButton();
	/**
	 * Create the panel
	 */
	public SearchSickInfo()throws SQLException {
		super();
		final String[] colHeads = { "病例代码", "病人姓名", "诊断医生", "门诊诊断", "入院诊断", "出院情况","诊断日期"};
		 dtm = new DefaultTableModel(colHeads, 0);
		springLayout = new SpringLayout();
		setLayout(springLayout);

		scrollPane = new JScrollPane();
		add(scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -30, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 55, SpringLayout.WEST, this);

		table = new JTable(dtm);
		scrollPane.setViewportView(table);

		final JCheckBox checkBox = new JCheckBox();
		checkBox.addMouseListener(new MouseAdapter() {
			public void mouseClicked(final MouseEvent e) {
				textField_2.setText("");
				if(checkBox.isSelected())
				{
					textField_2.setEnabled(true);
				}
				else
				{
					textField_2.setEnabled(false);
				}
			}
		});
		checkBox.setText("诊断日期");
		add(checkBox);
		springLayout.putConstraint(SpringLayout.EAST, checkBox, 295, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, checkBox, 160, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, checkBox, 270, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, checkBox, 245, SpringLayout.NORTH, this);

		final JLabel label = new JLabel();
		label.setText("病例代码");
		add(label);
		springLayout.putConstraint(SpringLayout.EAST, label, 60, SpringLayout.WEST, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, label, 0, SpringLayout.WEST, scrollPane);
		springLayout.putConstraint(SpringLayout.SOUTH, label, 55, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, label, 30, SpringLayout.NORTH, this);

		textField = new JTextField();
		add(textField);
		springLayout.putConstraint(SpringLayout.SOUTH, textField, 55, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, textField, 0, SpringLayout.NORTH, label);
		springLayout.putConstraint(SpringLayout.EAST, textField, 270, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, textField, 140, SpringLayout.WEST, this);

		final JButton button = new JButton();
		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(final MouseEvent e) {
				String caseID=textField.getText();
				if(caseID.equals(""))
				{
					JOptionPane.showMessageDialog(DoctorLogin.frame, " 查询条件为空！",
							"注意", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				int rowcount = dtm.getRowCount() - 1;
				if (rowcount != -1) {
					for (int i = rowcount; i >= 0; i--) {
						dtm.removeRow(i); // 删除Jtable中的所有行
					}
					dtm.setRowCount(0); // 将Jtable中的行数设为零
				}		
				//++++++++++++++++++++++++++++++++++++++++++++++++++++
				ResultSet rs=null;  
			    Connection conn=DBUtil.getConnection();  
			    int i=1;
				    try{
				    //Statement stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
						PreparedStatement ptmt = conn.prepareStatement("select caseID,sickName,docName,clinic,inhosp,outhosp,dateT from sickcase where caseID=?");
						ptmt.setString(1, caseID);
						rs= ptmt.executeQuery();
		//		    rs=stmt.executeQuery("select foregift.medicalNo,foregift.accountNO,foregift.need,sick.sickName,doctor.docName,sick.roomNo,sick.bedNo from foregift,sick,doctor where foregift.medicalNo=sick.medicalNo and doctor.docNo=sick.docNo and foregift.medicalNo='"+medicalNo+"'");
				    String[] data = new String[7];
				    while(rs.next())
				    {
				    	i++;
				    	for (int j = 1; j <= 7; j++) {
							data[j - 1] = rs.getString(j); // 取出数据库中的数组装载到数组中
						}
				    	dtm.addRow(data); // 在Jtable中显示取出的数据
				    }
				    //conn.close();
				    }catch(SQLException eX){
				       System.out.println("Link Error");
				    }
				    if(i==1)
				    {
				    	JOptionPane.showMessageDialog(DoctorLogin.frame, " 查找病例号不存在！",
								"注意", JOptionPane.INFORMATION_MESSAGE);
				    }
				//++++++++++++++++++++++++++++++++++++++++++++++++++++
			}
		});
		button.setText("查询");
		add(button);
		springLayout.putConstraint(SpringLayout.SOUTH, button, 0, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.NORTH, button, -25, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.EAST, button, 415, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, button, 330, SpringLayout.WEST, this);

		textField_1 = new JTextField();
		add(textField_1);
		springLayout.putConstraint(SpringLayout.SOUTH, textField_1, 0, SpringLayout.SOUTH, checkBox);
		springLayout.putConstraint(SpringLayout.NORTH, textField_1, -20, SpringLayout.SOUTH, checkBox);
		springLayout.putConstraint(SpringLayout.EAST, textField_1, 85, SpringLayout.EAST, checkBox);
		springLayout.putConstraint(SpringLayout.WEST, textField_1, 5, SpringLayout.EAST, checkBox);

		textField_2 = new JTextField();
		add(textField_2);
		springLayout.putConstraint(SpringLayout.SOUTH, textField_2, 0, SpringLayout.SOUTH, textField_1);
		springLayout.putConstraint(SpringLayout.NORTH, textField_2, -20, SpringLayout.SOUTH, textField_1);
		springLayout.putConstraint(SpringLayout.EAST, textField_2, 75, SpringLayout.EAST, button);
		springLayout.putConstraint(SpringLayout.WEST, textField_2, 0, SpringLayout.EAST, button);
		//
		textField_2.setEnabled(false);

		//final JRadioButton radioButton = new JRadioButton();
		radioButton.setText("诊断医生");
		radioButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(final MouseEvent e) {
				textField_1.setEnabled(true);
				textField_3.setEnabled(false);
				checkBox.setEnabled(true);
				textField_3.setText("");
				if(checkBox.isSelected())
				{
					textField_2.setEnabled(true);
				}else
				{
					textField_2.setEnabled(false);
				}
			}
		});
		buttonGroup.add(radioButton);
		radioButton.setSelected(true);
		add(radioButton);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -5, SpringLayout.NORTH, radioButton);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, -175, SpringLayout.NORTH, radioButton);
		springLayout.putConstraint(SpringLayout.EAST, radioButton, 0, SpringLayout.WEST, checkBox);
		springLayout.putConstraint(SpringLayout.WEST, radioButton, 0, SpringLayout.WEST, scrollPane);
		springLayout.putConstraint(SpringLayout.SOUTH, radioButton, 0, SpringLayout.SOUTH, checkBox);
		springLayout.putConstraint(SpringLayout.NORTH, radioButton, 0, SpringLayout.NORTH, checkBox);

		//final JRadioButton radioButton_1 = new JRadioButton();
		radioButton_1.setText("病人姓名");
		radioButton_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(final MouseEvent e) {
				if(radioButton_1.isSelected())
				{
					textField_3.setEnabled(true);
					checkBox.setEnabled(false);
					textField_1.setEnabled(false);
					textField_2.setEnabled(false);
					textField_1.setText("");
					textField_2.setText("");
				}
			}
		});
		buttonGroup.add(radioButton_1);
		add(radioButton_1);
		springLayout.putConstraint(SpringLayout.SOUTH, radioButton_1, 305, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, radioButton_1, 5, SpringLayout.SOUTH, radioButton);
		springLayout.putConstraint(SpringLayout.EAST, radioButton_1, 0, SpringLayout.WEST, checkBox);
		springLayout.putConstraint(SpringLayout.WEST, radioButton_1, 0, SpringLayout.WEST, radioButton);

		textField_3 = new JTextField();
		textField_3.setEnabled(false);
		add(textField_3);
		springLayout.putConstraint(SpringLayout.EAST, textField_3, 255, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, textField_3, 5, SpringLayout.EAST, radioButton_1);
		springLayout.putConstraint(SpringLayout.SOUTH, textField_3, 0, SpringLayout.SOUTH, radioButton_1);
		springLayout.putConstraint(SpringLayout.NORTH, textField_3, 285, SpringLayout.NORTH, this);

		final JButton button_1 = new JButton();
		button_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(final MouseEvent e) {
				int rowcount = dtm.getRowCount() - 1;
				if (rowcount != -1) {
					for (int i = rowcount; i >= 0; i--) {
						dtm.removeRow(i); // 删除Jtable中的所有行
					}
					dtm.setRowCount(0); // 将Jtable中的行数设为零
				}		
				String excuteSQL=null;
				String 诊断医生=textField_1.getText();
				String 诊断日期=textField_2.getText();
				String 病人姓名=textField_3.getText();
			    Connection conn=DBUtil.getConnection(); 
			    PreparedStatement ptmt =null;
				if(radioButton.isSelected())  //诊断医生被选中
				{
					if(textField_1.getText().equals(""))
					{
						JOptionPane.showMessageDialog(DoctorLogin.frame, " 请输入诊断医生的名子",
								"注意", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					if(checkBox.isSelected())  //诊断医生＋诊断日期
					{
						if((textField_2.getText()).equals(""))
						{
							JOptionPane.showMessageDialog(DoctorLogin.frame, " 请输入诊断日期！",
									"注意", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
					    //excuteSQL=诊断医生+诊断日期;
						excuteSQL="select caseID,sickName,docName,clinic,inhosp,outhosp,dateT from sickcase where docName=? and dateT=?";
							try {
								ptmt = conn.prepareStatement(excuteSQL);
								ptmt.setString(1, 诊断医生);
								ptmt.setString(2, 诊断日期);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					}
					else
					{    //诊断医生做为查询条件
						excuteSQL="select caseID,sickName,docName,clinic,inhosp,outhosp,dateT from sickcase where docName=?";
							try {
								ptmt = conn.prepareStatement(excuteSQL);
								ptmt.setString(1, 诊断医生);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					}
				}
				if(radioButton_1.isSelected())
				{
					if(textField_3.getText().equals("")) {JOptionPane.showMessageDialog(DoctorLogin.frame, " 请输入病人姓名！",	"注意", JOptionPane.INFORMATION_MESSAGE); return;}
					//excuteSQL=病人姓名;
					excuteSQL="select caseID,sickName,docName,clinic,inhosp,outhosp,dateT from sickcase where sickName=?";
					try {
						ptmt = conn.prepareStatement(excuteSQL);
						ptmt.setString(1, 病人姓名);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				System.out.println("excuteSQL"+excuteSQL);
				//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				ResultSet rs;
				try {
					rs = ptmt.executeQuery();	
					int i=1;
				    try{
				   // Statement stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				    //rs=stmt.executeQuery(excuteSQL);
				    String[] data = new String[7];
				    while(rs.next())
				    {
				    	i++;
				    	for (int j = 1; j <= 7; j++) {
							data[j - 1] = rs.getString(j); // 取出数据库中的数组装载到数组中
						}
				    	dtm.addRow(data); // 在Jtable中显示取出的数据
				    }
				    //conn.close();
				    }catch(SQLException eX){
				       System.out.println("SearchSickInfo!");
				    }
				    if(i==1)
				    {
				    	JOptionPane.showMessageDialog(DoctorLogin.frame, " 查找病例号不存在！",
								"注意", JOptionPane.INFORMATION_MESSAGE);
				    }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				
			}			
		});
		button_1.setText("查询");
		add(button_1);
		springLayout.putConstraint(SpringLayout.SOUTH, button_1, 0, SpringLayout.SOUTH, textField_3);
		springLayout.putConstraint(SpringLayout.NORTH, button_1, -25, SpringLayout.SOUTH, textField_3);
		springLayout.putConstraint(SpringLayout.EAST, button_1, 0, SpringLayout.EAST, textField_1);
		springLayout.putConstraint(SpringLayout.WEST, button_1, -80, SpringLayout.EAST, textField_1);
	}

}
