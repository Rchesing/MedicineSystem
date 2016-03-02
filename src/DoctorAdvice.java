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
		setTitle("ҽ������");
		setSize(500, 375);
		setLocationRelativeTo(null);
		final JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("�½�ҽ��", new DoctorAdviceNew());
		tabbedPane.addTab("��ѯҽ��", new DoctorAdviceSearch());
		tabbedPane.addTab("ֹͣҽ��", new DoctorAdviceStop());
		tabbedPane.addTab("ɾ��ҽ��", new DoctorAdviceDelete());
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		//
	}

}
