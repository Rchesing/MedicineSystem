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
		final String[] colHeads = { "��������", "��������", "���ҽ��", "�������", "��Ժ���", "��Ժ���","�������"};
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
		checkBox.setText("�������");
		add(checkBox);
		springLayout.putConstraint(SpringLayout.EAST, checkBox, 295, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, checkBox, 160, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, checkBox, 270, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, checkBox, 245, SpringLayout.NORTH, this);

		final JLabel label = new JLabel();
		label.setText("��������");
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
					JOptionPane.showMessageDialog(DoctorLogin.frame, " ��ѯ����Ϊ�գ�",
							"ע��", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				int rowcount = dtm.getRowCount() - 1;
				if (rowcount != -1) {
					for (int i = rowcount; i >= 0; i--) {
						dtm.removeRow(i); // ɾ��Jtable�е�������
					}
					dtm.setRowCount(0); // ��Jtable�е�������Ϊ��
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
							data[j - 1] = rs.getString(j); // ȡ�����ݿ��е�����װ�ص�������
						}
				    	dtm.addRow(data); // ��Jtable����ʾȡ��������
				    }
				    //conn.close();
				    }catch(SQLException eX){
				       System.out.println("Link Error");
				    }
				    if(i==1)
				    {
				    	JOptionPane.showMessageDialog(DoctorLogin.frame, " ���Ҳ����Ų����ڣ�",
								"ע��", JOptionPane.INFORMATION_MESSAGE);
				    }
				//++++++++++++++++++++++++++++++++++++++++++++++++++++
			}
		});
		button.setText("��ѯ");
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
		radioButton.setText("���ҽ��");
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
		radioButton_1.setText("��������");
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
						dtm.removeRow(i); // ɾ��Jtable�е�������
					}
					dtm.setRowCount(0); // ��Jtable�е�������Ϊ��
				}		
				String excuteSQL=null;
				String ���ҽ��=textField_1.getText();
				String �������=textField_2.getText();
				String ��������=textField_3.getText();
			    Connection conn=DBUtil.getConnection(); 
			    PreparedStatement ptmt =null;
				if(radioButton.isSelected())  //���ҽ����ѡ��
				{
					if(textField_1.getText().equals(""))
					{
						JOptionPane.showMessageDialog(DoctorLogin.frame, " ���������ҽ��������",
								"ע��", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					if(checkBox.isSelected())  //���ҽ�����������
					{
						if((textField_2.getText()).equals(""))
						{
							JOptionPane.showMessageDialog(DoctorLogin.frame, " ������������ڣ�",
									"ע��", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
					    //excuteSQL=���ҽ��+�������;
						excuteSQL="select caseID,sickName,docName,clinic,inhosp,outhosp,dateT from sickcase where docName=? and dateT=?";
							try {
								ptmt = conn.prepareStatement(excuteSQL);
								ptmt.setString(1, ���ҽ��);
								ptmt.setString(2, �������);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					}
					else
					{    //���ҽ����Ϊ��ѯ����
						excuteSQL="select caseID,sickName,docName,clinic,inhosp,outhosp,dateT from sickcase where docName=?";
							try {
								ptmt = conn.prepareStatement(excuteSQL);
								ptmt.setString(1, ���ҽ��);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					}
				}
				if(radioButton_1.isSelected())
				{
					if(textField_3.getText().equals("")) {JOptionPane.showMessageDialog(DoctorLogin.frame, " �����벡��������",	"ע��", JOptionPane.INFORMATION_MESSAGE); return;}
					//excuteSQL=��������;
					excuteSQL="select caseID,sickName,docName,clinic,inhosp,outhosp,dateT from sickcase where sickName=?";
					try {
						ptmt = conn.prepareStatement(excuteSQL);
						ptmt.setString(1, ��������);
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
							data[j - 1] = rs.getString(j); // ȡ�����ݿ��е�����װ�ص�������
						}
				    	dtm.addRow(data); // ��Jtable����ʾȡ��������
				    }
				    //conn.close();
				    }catch(SQLException eX){
				       System.out.println("SearchSickInfo!");
				    }
				    if(i==1)
				    {
				    	JOptionPane.showMessageDialog(DoctorLogin.frame, " ���Ҳ����Ų����ڣ�",
								"ע��", JOptionPane.INFORMATION_MESSAGE);
				    }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				
			}			
		});
		button_1.setText("��ѯ");
		add(button_1);
		springLayout.putConstraint(SpringLayout.SOUTH, button_1, 0, SpringLayout.SOUTH, textField_3);
		springLayout.putConstraint(SpringLayout.NORTH, button_1, -25, SpringLayout.SOUTH, textField_3);
		springLayout.putConstraint(SpringLayout.EAST, button_1, 0, SpringLayout.EAST, textField_1);
		springLayout.putConstraint(SpringLayout.WEST, button_1, -80, SpringLayout.EAST, textField_1);
	}

}
