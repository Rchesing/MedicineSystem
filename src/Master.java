package src;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.SwingResourceManager;

import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;

public class Master extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Master() throws HeadlessException {
		 super("ҽԺ����ϵͳ-�����");
		 
		 JFrame.setDefaultLookAndFeelDecorated(true);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public Master(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public Master(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public Master(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		String cmd = event.getActionCommand();
	    if(cmd=="��ʱ����վ"){
	    	System.out.println("��ʱ����վ");
	    	try {
				new OnceWork();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    //	new tempWorkstation();
	      }
	    if(cmd=="���һ���"){
	    	System.out.println("���һ���");
	    	KeShiHuiZhen consu = new KeShiHuiZhen();
			consu.setVisible(true);
	      }
	    if(cmd=="���ù���"){
	    	System.out.println("���ù���");	
	    	FeeManager fee=new FeeManager();
	    	fee.setVisible(true);
	      }
	    if(cmd=="ҽ������"){
	    	System.out.println("ҽ������");
	       // new docManage();
	    	DoctorAdvice frame;
			try {
				frame = new DoctorAdvice();
				frame.setVisible(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	      }
	    if(cmd=="��ҩ����ѯ"){
	    	System.out.println("��ҩ����ѯ");
	    	new MainMedList();
	      }
	}


	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		
		  setSize(700,500);
		  setResizable(false);
	      setLocationRelativeTo(null);
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  setIconImage(SwingResourceManager.getImage(Master.class, "images/pill.gif"));
		  //add top image

		  JPanel jp0=new JPanel();
		  jp0.setBorder(BorderFactory.createBevelBorder(1,new Color(45,92,162),
		                new Color(43,66,97),new Color(45,92,162),new Color(84,123,200)));
		  //add hospital image
		  jp0.setLayout(new BorderLayout());
		  JLabel background=new JLabel(SwingResourceManager.getIcon(Master.class, "images/��ҳ.jpg"),JLabel.CENTER);
		  jp0.add("Center",background);
		  //add 5 button
		  JPanel jp1=new JPanel(new GridLayout(5,1));
		  JButton tempWorkstation=new JButton("��ʱ����վ");
		  tempWorkstation.addActionListener(this);
		  tempWorkstation.setForeground(Color.RED);
		  JButton NurseFinding=new JButton("���һ���");
		  NurseFinding.addActionListener(this);
		  NurseFinding.setForeground(Color.RED);
		  JButton TempWork=new JButton("���ù���");
		  TempWork.addActionListener(this);
		  TempWork.setForeground(Color.RED);
		  JButton DocManage=new JButton("ҽ������");
		  DocManage.addActionListener(this);
		  DocManage.setForeground(Color.RED);
		  JButton FeeManage=new JButton("��ҩ����ѯ");
		  FeeManage.addActionListener(this);
		  FeeManage.setForeground(Color.RED);

		  jp1.add(tempWorkstation);
		  jp1.add(NurseFinding);
		  jp1.add(TempWork);
		  jp1.add(DocManage);
		  jp1.add(FeeManage);

		  jp0.add("East",jp1);
		  add(jp0);
		  setVisible(true);
	}

}  //  @jve:decl-index=0:visual-constraint="26,123"
