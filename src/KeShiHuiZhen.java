package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;


public class KeShiHuiZhen extends JFrame {

	private JTable table;
	private JComboBox comboBox;
	private SpringLayout springLayout;
	private DefaultTableModel dtm;

	/**
	 * Create the frame
	 */
	public KeShiHuiZhen() {
		super();
		setSize(650, 316);
		setIconImage(SwingResourceManager.getImage(KeShiHuiZhen.class, "images/pill.gif"));
		springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		setTitle("科室会诊");	
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


		final JLabel label_1 = new JLabel();
		label_1.setText("科室");
		getContentPane().add(label_1);

		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
			//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				JComboBox cb = (JComboBox) e.getSource();
				String se1 = (String) cb.getSelectedItem();
				if (se1.equals("科室一")) {
					String SQL="select officeNo,docName,Doc_sex,Doc_age,Doc_zhicheng,Remark,Doc_time,Doc_xueli from doctor where officeNo='office51'";
					printInfo(SQL);
				}
				if (se1.equals("科室二")) {
					String SQL="select officeNo,docName,Doc_sex,Doc_age,Doc_zhicheng,Remark,Doc_time,Doc_xueli from doctor where officeNo='office52'";
					printInfo(SQL);
				}
				if (se1.equals("科室三")) {
					String SQL="select officeNo,docName,Doc_sex,Doc_age,Doc_zhicheng,Remark,Doc_time,Doc_xueli from doctor where officeNo='office53'";
					printInfo(SQL);
				}
				// -----------------------------------------------------------------------
				if (se1.equals("科室四")) {
					String SQL="select officeNo,docName,Doc_sex,Doc_age,Doc_zhicheng,Remark,Doc_time,Doc_xueli from doctor where officeNo='office54'";
					printInfo(SQL);
				}
				// ------------------------------------------------------------
				if (se1.equals("科室五")) {
					String SQL="select officeNo,docName,Doc_sex,Doc_age,Doc_zhicheng,Remark,Doc_time,Doc_xueli from doctor where officeNo='office55'";
					printInfo(SQL);
				}
				if(se1.equals("所有科室"))
				{
					String SQL="select officeNo,docName,Doc_sex,Doc_age,Doc_zhicheng,Remark,Doc_time,Doc_xueli from doctor";
					printInfo(SQL);
				}
			//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"科室一", "科室二", "科室三", "科室四", "科室五", "所有科室"}));
		getContentPane().add(comboBox);
		springLayout.putConstraint(SpringLayout.SOUTH, comboBox, 0, SpringLayout.SOUTH, label_1);
		springLayout.putConstraint(SpringLayout.NORTH, comboBox, -25, SpringLayout.SOUTH, label_1);
		springLayout.putConstraint(SpringLayout.EAST, comboBox, 200, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, comboBox, 75, SpringLayout.WEST, getContentPane());

		final JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, label_1, 90, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, label_1, 0, SpringLayout.WEST, scrollPane);
		springLayout.putConstraint(SpringLayout.SOUTH, label_1, 30, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.NORTH, label_1, 5, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 605, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 20, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 230, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 70, SpringLayout.NORTH, getContentPane());

		final String[] colHeads = { "科室", "医生姓名", "性别", "年龄", "职称","主治","在职时间","最高学历"};
		dtm = new DefaultTableModel(colHeads, 0);

		table = new JTable(dtm);
		scrollPane.setViewportView(table);

		final JLabel label_3 = new JLabel();
		label_3.setText("科室会诊明细表");
		getContentPane().add(label_3);
		springLayout.putConstraint(SpringLayout.EAST, label_3, 135, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, label_3, 0, SpringLayout.WEST, scrollPane);
		springLayout.putConstraint(SpringLayout.SOUTH, label_3, 70, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, label_3, 45, SpringLayout.NORTH, getContentPane());
		//
	}
	public void printInfo(String SQL)
	{
		Connection conn=DBUtil.getConnection();
	    ResultSet rs=null;
	    //===================================================
	    int rowcount = dtm.getRowCount() - 1;
		if (rowcount != -1) {
			for (int i = rowcount; i >= 0; i--) {
				dtm.removeRow(i); // 删除Jtable中的所有行
			}
			dtm.setRowCount(0); // 将Jtable中的行数设为零
		}		
	    //===================================================
	    try{
	    Statement stmt=conn.createStatement();
	    rs=stmt.executeQuery(SQL);
	    String[] data = new String[8];
		while (rs.next()) {
			for (int j = 1; j <= 8; j++) {
				data[j - 1] = rs.getString(j); // 取出数据库中的数组装载到数组中
			}
			dtm.addRow(data); // 在Jtabl
		}
	    //conn.close();
	    }catch(SQLException e){
	       System.out.println("KeShiHuiZhen");
	    }
	}
}
