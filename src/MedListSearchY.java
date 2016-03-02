package src;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

public class MedListSearchY extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textField;
	private SpringLayout springLayout;
	private DefaultTableModel dtm;
	/**
	 * Create the panel
	 */
	public MedListSearchY() {
		super();
		 final String[] colHeads = { "ҩƷ��", "�ʻ�", "�ʻ����", "��������", "��ҩҽ��", "�����","��λ��"};
		 dtm = new DefaultTableModel(colHeads, 0);
		springLayout = new SpringLayout();
		setLayout(springLayout);

		final JLabel label = new JLabel();
		label.setText("ҩƷ��");
		add(label);
		springLayout.putConstraint(SpringLayout.SOUTH, label, 80, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, label, 45, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, label, 95, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, label, 30, SpringLayout.WEST, this);

		textField = new JTextField();
		add(textField);
		springLayout.putConstraint(SpringLayout.SOUTH, textField, 0, SpringLayout.SOUTH, label);
		springLayout.putConstraint(SpringLayout.NORTH, textField, 50, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, textField, 210, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, textField, 0, SpringLayout.EAST, label);

		final JButton button = new JButton();
		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(final MouseEvent e) {
				int rowcount = dtm.getRowCount() - 1;
				if (rowcount != -1) {
					for (int i = rowcount; i >= 0; i--) {
						dtm.removeRow(i); // ɾ��Jtable�е�������
					}
					dtm.setRowCount(0); // ��Jtable�е�������Ϊ��
				}		
				ResultSet rs=null;  
				 Connection conn=DBUtil.getConnection();  
				 int i=1;
				 String medicalNo=textField.getText();
				 if ((medicalNo == null)||(medicalNo.equals(""))) {
						JOptionPane.showMessageDialog(DoctorLogin.frame, "  ������ҩƷ�ţ�",
								"ע��", JOptionPane.ERROR_MESSAGE);
						return;
					}
				 System.out.println("textField"+medicalNo);
				    try{
				    	String sql = "select foregift.medicalNo,foregift.accountNO,foregift.need,sick.sickName,doctor.docName,sick.roomNo,sick.bedNo from foregift,sick,doctor where foregift.medicalNo=sick.medicalNo and doctor.docNo=sick.docNo and foregift.medicalNo=?";
				//    Statement stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
						PreparedStatement ptmt = conn.prepareStatement(sql);
						ptmt.setString(1, medicalNo);
						rs= ptmt.executeQuery();
				    String[] data = new String[7];
				    while(rs.next())
				    {
				    	i++;
				    	for (int j = 1; j <= 7; j++) {
							data[j - 1] = rs.getString(j); // ȡ�����ݿ��е�����װ�ص�������
						}
				    	dtm.addRow(data); // ��Jtable����ʾȡ��������
				    }
				    if(i==1)
				    {
				    	JOptionPane.showMessageDialog(DoctorLogin.frame, " ���ҵ�ҩƷ�Ų����ڣ�",
								"ע��", JOptionPane.ERROR_MESSAGE);
				    }
				    }catch(SQLException eX){
				       System.out.println("MedListSearchY!!!");
				    }
			}
		});
		button.setText("����");
		add(button);
		springLayout.putConstraint(SpringLayout.SOUTH, button, 0, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.NORTH, button, 0, SpringLayout.NORTH, textField);
		springLayout.putConstraint(SpringLayout.EAST, button, 315, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, button, 235, SpringLayout.WEST, this);

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
			}
		});
		button_1.setText("����б�");
		add(button_1);
		springLayout.putConstraint(SpringLayout.SOUTH, button_1, 0, SpringLayout.SOUTH, button);
		springLayout.putConstraint(SpringLayout.NORTH, button_1, 0, SpringLayout.NORTH, button);
		springLayout.putConstraint(SpringLayout.EAST, button_1, 440, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, button_1, 345, SpringLayout.WEST, this);

		final JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -45, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 50, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 280, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 100, SpringLayout.NORTH, this);

		table = new JTable(dtm);
		scrollPane.setViewportView(table);
		//
	}

}
