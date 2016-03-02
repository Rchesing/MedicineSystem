package src;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class MedList extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ResultSet rs=null;  
	private Connection conn=DBUtil.getConnection(); 
	private JTable table;
	private DefaultTableModel dtm;
	private String[] columnNames={"ҩƷ��","��������","��ҩҽ��","�ʻ�","�ʻ����","�����","��λ��"};
	
	/**
	 * Create the panel
	 */
	public MedList() {
		super();
		setLayout(null);

		final JLabel label = new JLabel();
		label.setIcon(SwingResourceManager.getIcon(MedList.class, "images/pen.gif"));
		label.setBounds(548, -10, 68, 71);
		add(label);

		final JLabel label_1 = new JLabel();
		label_1.setText("ҩƷ�б�");
		label_1.setBounds(15, 20, 100, 23);
		add(label_1);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 55, 589, 198);
		add(scrollPane);

		dtm = new DefaultTableModel(columnNames, 0);
		table = new JTable(dtm);
		scrollPane.setViewportView(table);
		//
		connToDatabase();
	}
	public void connToDatabase()
	{

		    try{
		    Statement stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		    rs=stmt.executeQuery("select distinct foregift.medicalNo,sick.sickName,doctor.docName,foregift.accountNO,foregift.need,sick.roomNo,sick.bedNo from foregift,sick,doctor where foregift.medicalNo=sick.medicalNo and doctor.docNo=sick.docNo");
		    String[] data = new String[7];
		    while(rs.next())
		    {
		    //	i++;
		    	for (int j = 1; j <= 7; j++) {
					data[j - 1] = rs.getString(j); // ȡ�����ݿ��е�����װ�ص�������
				}
		    	dtm.addRow(data); // ��Jtable����ʾȡ��������
		    }
		    }catch(SQLException e){
		       System.out.println("MedList!!!");
		    }
	}

}
