package src;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainMedList extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MainMedList() {
		 super("��ҩ����ѯ");
		
		  setSize(650,400); 
		  setLocationRelativeTo(null);
		  setResizable(false);
		  setBackground(Color.white);
		  setLayout(new BorderLayout());
		  setIconImage(SwingResourceManager.getImage(MainMedList.class, "images/pill.gif"));
		  //add two panel
		  JTabbedPane tabbedPane1=new JTabbedPane();
		  tabbedPane1.addTab("ҩƷ��ѯ",new MedList());
		  tabbedPane1.addTab("ҩƷ����",new MedListSearchY());
		  add("Center",tabbedPane1);

		  setVisible(true);
	}

}
