package src;

import java.awt.BorderLayout;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

import src.DoctorAdviceNew;

public class DoctorAdvice extends JFrame {


	/**
	 * Create the frame
	 * @throws SQLException 
	 */
	public DoctorAdvice() throws SQLException {
		super();
		setIconImage(SwingResourceManager.getImage(DoctorAdvice.class, "images/pill.gif"));
		setTitle("医嘱处理");
		setSize(500, 375);
		setLocationRelativeTo(null);
		final JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("新建医嘱", new DoctorAdviceNew());
		tabbedPane.addTab("查询医嘱", new DoctorAdviceSearch());
		tabbedPane.addTab("停止医嘱", new DoctorAdviceStop());
		tabbedPane.addTab("删除医嘱", new DoctorAdviceDelete());
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		//
	}

}
