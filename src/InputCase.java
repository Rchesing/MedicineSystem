package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

public class InputCase extends JPanel {

	private JTextArea textField_6;
	private JTextArea textField_4;
	private JTextArea textField_2;
	private JComboBox comboBox;
	private JTextField textField_7;
	private JTextField textField_5;
	private JTextField textField_3;
	private JTextField textField_1;
	private SpringLayout springLayout;

	/**
	 * Create the panel
	 */
	public InputCase() {
		super();
		springLayout = new SpringLayout();
		setLayout(springLayout);

		final JLabel label = new JLabel();
		label.setText("诊断医生");
		add(label);
		springLayout.putConstraint(SpringLayout.SOUTH, label, 50, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, label, 30, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, label, 395, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, label, 220, SpringLayout.WEST, this);

		final JLabel label_1 = new JLabel();
		label_1.setText("诊断日期");
		add(label_1);

		textField_1 = new JTextField();
		add(textField_1);
		springLayout.putConstraint(SpringLayout.SOUTH, textField_1, 85, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, textField_1, 60, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, label_1, -5, SpringLayout.NORTH, textField_1);
		springLayout.putConstraint(SpringLayout.NORTH, label_1, -25, SpringLayout.NORTH, textField_1);
		springLayout.putConstraint(SpringLayout.EAST, label_1, 155, SpringLayout.WEST, textField_1);
		springLayout.putConstraint(SpringLayout.WEST, label_1, 0, SpringLayout.WEST, textField_1);
		springLayout.putConstraint(SpringLayout.EAST, textField_1, -15, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, textField_1, 425, SpringLayout.WEST, this);

		final JLabel label_2 = new JLabel();
		label_2.setText("门诊诊断");
		add(label_2);
		springLayout.putConstraint(SpringLayout.EAST, label_2, 385, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, label_2, 220, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, label_2, 120, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, label_2, 100, SpringLayout.NORTH, this);

		final JLabel label_3 = new JLabel();
		label_3.setText("出院情况");
		add(label_3);
		springLayout.putConstraint(SpringLayout.SOUTH, label_3, 0, SpringLayout.SOUTH, label_2);
		springLayout.putConstraint(SpringLayout.NORTH, label_3, 0, SpringLayout.NORTH, label_2);
		springLayout.putConstraint(SpringLayout.EAST, label_3, 0, SpringLayout.EAST, textField_1);
		springLayout.putConstraint(SpringLayout.WEST, label_3, 0, SpringLayout.WEST, textField_1);

		textField_3 = new JTextField();
		add(textField_3);
		springLayout.putConstraint(SpringLayout.SOUTH, textField_3, 145, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, textField_3, 0, SpringLayout.SOUTH, label_3);
		springLayout.putConstraint(SpringLayout.EAST, textField_3, 0, SpringLayout.EAST, label_3);
		springLayout.putConstraint(SpringLayout.WEST, textField_3, 0, SpringLayout.WEST, label_3);

		final JLabel label_4 = new JLabel();
		label_4.setText("是否入院？入院诊断");
		add(label_4);
		springLayout.putConstraint(SpringLayout.EAST, label_4, 385, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, label_4, 220, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, label_4, 190, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, label_4, 165, SpringLayout.NORTH, this);

		final JLabel label_5 = new JLabel();
		label_5.setText("病人姓名");
		add(label_5);
		springLayout.putConstraint(SpringLayout.SOUTH, label_5, 0, SpringLayout.SOUTH, label_4);
		springLayout.putConstraint(SpringLayout.NORTH, label_5, 170, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, label_5, 0, SpringLayout.EAST, textField_3);
		springLayout.putConstraint(SpringLayout.WEST, label_5, 0, SpringLayout.WEST, textField_3);

		textField_5 = new JTextField();
		add(textField_5);
		springLayout.putConstraint(SpringLayout.SOUTH, textField_5, 215, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, textField_5, 0, SpringLayout.SOUTH, label_5);
		springLayout.putConstraint(SpringLayout.EAST, textField_5, 0, SpringLayout.EAST, label_5);
		springLayout.putConstraint(SpringLayout.WEST, textField_5, 0, SpringLayout.WEST, label_5);

		final JLabel label_6 = new JLabel();
		label_6.setText("病理诊断");
		add(label_6);
		springLayout.putConstraint(SpringLayout.EAST, label_6, 165, SpringLayout.WEST, label_4);
		springLayout.putConstraint(SpringLayout.WEST, label_6, 0, SpringLayout.WEST, label_4);
		springLayout.putConstraint(SpringLayout.SOUTH, label_6, 260, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, label_6, 235, SpringLayout.NORTH, this);

		final JLabel label_7 = new JLabel();
		label_7.setText("病例代码");
		add(label_7);
		springLayout.putConstraint(SpringLayout.SOUTH, label_7, 0, SpringLayout.SOUTH, label_6);
		springLayout.putConstraint(SpringLayout.NORTH, label_7, 0, SpringLayout.NORTH, label_6);
		springLayout.putConstraint(SpringLayout.EAST, label_7, 0, SpringLayout.EAST, textField_5);
		springLayout.putConstraint(SpringLayout.WEST, label_7, 0, SpringLayout.WEST, textField_5);

		textField_7 = new JTextField();
		add(textField_7);
		springLayout.putConstraint(SpringLayout.EAST, textField_7, 0, SpringLayout.EAST, label_7);
		springLayout.putConstraint(SpringLayout.WEST, textField_7, 0, SpringLayout.WEST, label_7);

		final JButton button = new JButton();
		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(final MouseEvent e) {
				// 判断输入项都不能为空
				if ((textField_2.getText()).equals("") || (textField_4.getText()).equals("")
						|| (textField_6.getText()).equals("")) {
					JOptionPane.showMessageDialog(DoctorLogin.frame, "请输入完整信息", "信息校验", JOptionPane.INFORMATION_MESSAGE);
					System.out.println("表格没有填完整");
					return;
				}
				String 诊断医生 = (String) comboBox.getSelectedItem();
				String 诊断日期 = textField_1.getText();
				String 门诊诊断 = textField_2.getText();
				String 出院情况 = textField_3.getText();
				String 入院诊断 = textField_4.getText();
				String 病人姓名 = textField_5.getText();
				String 病理诊断 = textField_6.getText();
				String 病例代码 = textField_7.getText();
				if (!isHaveSickName(病人姓名)) {
					JOptionPane.showMessageDialog(DoctorLogin.frame, "数据库中无‘" + 病人姓名 + "’的病人信息", "信息校验",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				// ++++++++++++++++++++++++++++++++测试输出到控制台+++++++++++++++++++++++++++++++++++++++++
				System.out.print(诊断医生);
				System.out.print(诊断日期);
				System.out.print(门诊诊断);
				System.out.print(出院情况);
				System.out.print(入院诊断);
				System.out.print(病人姓名);
				System.out.print(病理诊断);
				System.out.println(病例代码);
				Connection conn = null;
				try {
					conn = DBUtil.getConnection();

					PreparedStatement ptmt = conn.prepareStatement(
							"insert into sickcase(docName,DateT,clinic,outhosp,inhosp,sickName,pathology,caseID)"
							+ " values(?,?,?,?,?,?,?,?)");
					ptmt.setString(1, 诊断医生);
					ptmt.setString(2, 诊断日期);
					ptmt.setString(3, 门诊诊断);
					ptmt.setString(4, 出院情况);
					ptmt.setString(5, 入院诊断);
					ptmt.setString(6, 病人姓名);
					ptmt.setString(7, 病理诊断);
					ptmt.setString(8, 病例代码);
					ptmt.execute();
					JOptionPane.showMessageDialog(DoctorLogin.frame, "如果想继续插入，请重新选择医生?", "插入成功!",
							JOptionPane.INFORMATION_MESSAGE);
					// rs=stmt.executeQuery("select docName from doctor");
					//conn.close();
				} catch (SQLException ButtonEX) {
					System.out.println("InputCase Error");
				}
				// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textField_6.setText("");
				textField_7.setText("");
			}
		});
		button.setText("病例生成");
		add(button);
		springLayout.putConstraint(SpringLayout.SOUTH, textField_7, -5, SpringLayout.NORTH, button);
		springLayout.putConstraint(SpringLayout.NORTH, textField_7, 5, SpringLayout.SOUTH, label_7);
		springLayout.putConstraint(SpringLayout.EAST, button, 530, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, button, 435, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, button, 325, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, button, 295, SpringLayout.NORTH, this);


		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				String docNoTrim = null;
				String docNo = null;
				ResultSet res = null;
				ResultSet resNum = null; // 记录当天医生开的第N个药单查询语句
				int num = 0; // 初始化记录当天医生开的第N个药单
				String dateinsertdocNo = null;
				Connection conndb = DBUtil.getConnection();
				Calendar rightNow = Calendar.getInstance();
				int year = rightNow.get(Calendar.YEAR);
				int month = rightNow.get(Calendar.MONTH) + 1;
				int day = rightNow.get(Calendar.DAY_OF_MONTH);
				String dateinsert = String.valueOf(year) + String.valueOf(month) + String.valueOf(day);
				JComboBox cb = (JComboBox) e.getSource();
				String docName = (String) cb.getSelectedItem();
				System.out.println("你选择了" + docName);

				// ----------------------------------------------------------
				try {
					PreparedStatement ptmt = conndb.prepareStatement("select docNo from doctor where docName=?");
					ptmt.setString(1,docName);
					res = ptmt.executeQuery();
					while (res.next()) {
						docNo = res.getString(1); // 通过医生名取出医生的NO
					}
					docNoTrim = docNo.trim(); // 去掉取出的空格
					dateinsertdocNo = dateinsert + docNoTrim;
					ptmt = conndb.prepareStatement("select * from sickcase where caseID like ?");
					ptmt.setString(1,"%"+dateinsertdocNo+"%");
					resNum = ptmt.executeQuery();
					while (resNum.next()) {
						System.out.println("Num++");
						num++;
					}
					num++; // 在原有数字上加一为新的记录ID
					//conndb.close();
				} catch (SQLException eX) {
					System.out.println("查找医生NO时出错");
					eX.printStackTrace();
				}
				// --------------------------------------------------------------
				dateinsert = String.valueOf(year) + String.valueOf(month) + String.valueOf(day) + docNoTrim
						+ String.valueOf(num);
				textField_7.setText(dateinsert);
				textField_1.setText(String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day));
				System.out.println("数据库中已有" + num);

			}
		});
		add(comboBox);
		springLayout.putConstraint(SpringLayout.EAST, comboBox, 375, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, comboBox, 0, SpringLayout.WEST, label);
		springLayout.putConstraint(SpringLayout.SOUTH, comboBox, 0, SpringLayout.SOUTH, textField_1);
		springLayout.putConstraint(SpringLayout.NORTH, comboBox, -25, SpringLayout.SOUTH, textField_1);

		ResultSet rs = null;
		Connection conn = DBUtil.getConnection();
		try {
			String sql = "select docName from doctor";
			PreparedStatement ptmt = conn.prepareStatement(sql);
			rs = ptmt.executeQuery();
			while (rs.next()) {
				comboBox.addItem(rs.getString(1));
				// System.out.println(rs.getString(1));
			}
			//conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("组合框添加项错误");
		}

		final JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 0, SpringLayout.NORTH, label_5);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 5, SpringLayout.SOUTH, label_2);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, label_2);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, label_2);

		textField_2 = new JTextArea();
		scrollPane.setViewportView(textField_2);

		final JScrollPane scrollPane_1 = new JScrollPane();
		add(scrollPane_1);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane_1, 0, SpringLayout.EAST, label_6);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane_1, 0, SpringLayout.WEST, label_4);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane_1, 240, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane_1, 5, SpringLayout.SOUTH, label_4);

		textField_4 = new JTextArea();
		scrollPane_1.setViewportView(textField_4);

		final JScrollPane scrollPane_2 = new JScrollPane();
		add(scrollPane_2);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane_2, 305, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane_2, 5, SpringLayout.SOUTH, label_6);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane_2, 0, SpringLayout.EAST, label_6);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane_2, 0, SpringLayout.WEST, label_6);

		textField_6 = new JTextArea();
		scrollPane_2.setViewportView(textField_6);
	}

	public boolean isHaveSickName(String 病人姓名) {
		ResultSet rs = null;
		Connection conn = DBUtil.getConnection();
		int num = 0;
		try {
			String sql = "select sickName from sick where sickName=?";
			rs = null;
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, 病人姓名);
			rs = ptmt.executeQuery();
			
			while (rs.next()) {
				num++;
			}
			//conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("查询病人姓名时出错");
		}
		if (num == 0) {
			return false;
		} else {
			return true;
		}
	}
}
