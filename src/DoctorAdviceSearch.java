package src;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

public class DoctorAdviceSearch extends JPanel {

	private JTextArea textArea_1;
	private JTextArea textArea;
	private JTable table;
	private JTextField textField;
	private JComboBox comboBox;
	private SpringLayout springLayout;
	
	private DefaultTableModel dtm;
	private String[] columnNames={"ҽ������","���˴���","ҽ��ҽ������","��ҽ��ʱ��","ͣҽ��ʱ��","ͣҽ����"};
	/**
	 * Create the panel
	 */
	public DoctorAdviceSearch() {
		super();
		springLayout = new SpringLayout();
		setLayout(springLayout);

		comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(final ItemEvent e) {
				textField.setText("");
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"��������", "���˴���"}));
		add(comboBox);
		springLayout.putConstraint(SpringLayout.SOUTH, comboBox, 55, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, comboBox, 30, SpringLayout.NORTH, this);

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
				String SelectContent=comboBox.getSelectedItem().toString();
				System.out.println(SelectContent);
				if(SelectContent.equals("��������"))
				{
					String ��������=textField.getText();
					if(��������.equals(""))
					{
						JOptionPane.showMessageDialog(DoctorLogin.frame, "  �����벡��������",
								"ע��", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					System.out.println("��������"+��������+"00000000000");
					String sickNo=GetdocNo(��������);
					System.out.println("docNo="+sickNo);
					String SQL="select Advi_No,Sick_No,Doc_No,Advi_ontime,Advi_outtime,Advi_outpers,Advi_content,Advi_fushu from docAdvice where Sick_No=?";
					Search(SQL,sickNo);
					textField.setText("");
				}
				if(SelectContent.equals("���˴���"))
				{
					String ���˴���=textField.getText();
					if(textField.getText().equals(""))
					{
						JOptionPane.showMessageDialog(DoctorLogin.frame, "  �����벡�˴��룡",
								"ע��", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					String SQL="select Advi_No,Sick_No,Doc_No,Advi_ontime,Advi_outtime,Advi_outpers,Advi_content,Advi_fushu from docAdvice where Sick_No=?";
					System.out.println("���˴���"+���˴���);
					Search(SQL,���˴���);
					textField.setText("");
				}
			}
		});
		button.setText("��ѯ");
		add(button);
		springLayout.putConstraint(SpringLayout.SOUTH, button, 55, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, button, 0, SpringLayout.NORTH, comboBox);
		springLayout.putConstraint(SpringLayout.EAST, button, 405, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, button, 325, SpringLayout.WEST, this);

		textField = new JTextField();
		add(textField);
		springLayout.putConstraint(SpringLayout.SOUTH, textField, 55, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, textField, 0, SpringLayout.NORTH, comboBox);
		springLayout.putConstraint(SpringLayout.EAST, textField, 295, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, textField, 160, SpringLayout.WEST, this);

		final JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, comboBox, 105, SpringLayout.WEST, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, comboBox, 0, SpringLayout.WEST, scrollPane);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 135, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 75, SpringLayout.NORTH, this);

		dtm = new DefaultTableModel(columnNames, 0);
		table = new JTable(dtm);
		scrollPane.setViewportView(table);

		final JLabel label = new JLabel();
		label.setText("ҽ������");
		add(label);
		springLayout.putConstraint(SpringLayout.SOUTH, label, 30, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.NORTH, label, 5, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, label, 110, SpringLayout.WEST, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, label, 0, SpringLayout.WEST, scrollPane);

		final JLabel label_1 = new JLabel();
		label_1.setText("����ҽ��");
		add(label_1);
		springLayout.putConstraint(SpringLayout.SOUTH, label_1, 0, SpringLayout.SOUTH, label);
		springLayout.putConstraint(SpringLayout.NORTH, label_1, -25, SpringLayout.SOUTH, label);

		final JScrollPane scrollPane_1 = new JScrollPane();
		add(scrollPane_1);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 475, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, scrollPane_1);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane_1, 205, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane_1, 20, SpringLayout.WEST, this);

		textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		textArea.setEditable(false);

		final JScrollPane scrollPane_2 = new JScrollPane();
		add(scrollPane_2);
		springLayout.putConstraint(SpringLayout.EAST, label_1, 115, SpringLayout.WEST, scrollPane_2);
		springLayout.putConstraint(SpringLayout.WEST, label_1, 0, SpringLayout.WEST, scrollPane_2);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane_2, 0, SpringLayout.EAST, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane_2, 285, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane_1, 0, SpringLayout.SOUTH, scrollPane_2);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane_1, 0, SpringLayout.SOUTH, label);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane_2, 275, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane_2, 0, SpringLayout.SOUTH, label_1);

		textArea_1 = new JTextArea();
		scrollPane_2.setViewportView(textArea_1);
		textArea_1.setEditable(false);
		//
	}
	public void Search(String SQL,String str)
	{
		//+++++++++++++++++++++++
		int rowcount = dtm.getRowCount() - 1;
		if (rowcount != 0) {
			for (int i = rowcount; i >= 0; i--) {
				dtm.removeRow(i); // ɾ��Jtable�е�������
			}
			dtm.setRowCount(0); // ��Jtable�е�������Ϊ��
		}
		//+++++++++++++++++++++++	
		Connection conn=null;
	    ResultSet rs=null;
	    int i=1;
	    try{
	    conn=DBUtil.getConnection();
		PreparedStatement ptmt = conn.prepareStatement(SQL);
		ptmt.setString(1, str);	
		rs= ptmt.executeQuery();
	    String[] data = new String[6];
	    while(rs.next())
	    {
	    	i++;
	    	for (int j = 1; j <= 6; j++) {
				data[j - 1] = rs.getString(j); // ȡ�����ݿ��е�����װ�ص�������
			}
	    	dtm.addRow(data); // ��Jtable����ʾȡ��������
	    	textArea.setText(rs.getString(7));
	    	textArea_1.setText(rs.getString(8));
	    }
	    //conn.close();
	    }catch(SQLException e){
	       System.out.println("DoctorAdviceSearch1!!!!");
	    }
		//++++++++++++++++++++++++
	    if(i==1)
	    {
	    	JOptionPane.showMessageDialog(DoctorLogin.frame, " ����ѯ�޴��˵�ҽ����",
					"ע��", JOptionPane.INFORMATION_MESSAGE);
	    }
	    //============================
	}
	public String GetdocNo(String sickName)
	{
		String sickNo=null;
		Connection conn=null;
	    ResultSet rs=null;
	    try{
	    conn=DBUtil.getConnection();
		PreparedStatement ptmt = conn.prepareStatement("select Sick_No from sick where sickName=?");
		ptmt.setString(1, sickName);
	    rs=ptmt.executeQuery();
	    while(rs.next())
	    {
	    	sickNo=rs.getString(1);  //1��������ݿ���ȡ����һ���ֶ�ֵ
	    }
	    //conn.close();
	    }catch(SQLException e){
	       System.out.println("DoctorAdviceSearch2!!!!");
	    }
		return sickNo;
	}
}
