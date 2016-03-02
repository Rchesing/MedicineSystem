package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import src.SwingResourceManager;
public class DoctorLogin implements ActionListener{
	public static JFrame frame;
	public static JTextField name;
    public static JPasswordField password;
	private JPanel jContentPane = null;
    
	public DoctorLogin() throws HeadlessException {
		super();
		// TODO Auto-generated constructor stub
		initialize();
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 String cmd=e.getActionCommand();
		    String passwd=String.valueOf(password.getPassword());
		    if(cmd=="µÇÂ¼"){
		    	ValidatePass vapass=new ValidatePass(name.getText(),passwd);
		    	try {
					vapass.ValidatePassword();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		      }
		    if(cmd=="ÍË³ö"){
		      System.exit(0);
		      }
		
	}

	public DoctorLogin(GraphicsConfiguration arg0) {
		
		// TODO Auto-generated constructor stub
		initialize();
	}

	public DoctorLogin(String arg0) throws HeadlessException {
	
		// TODO Auto-generated constructor stub
		initialize();
	}

	public DoctorLogin(String arg0, GraphicsConfiguration arg1) {
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * This method initializes jContentPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
		}
		return jContentPane;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new DoctorLogin();
	}
	private void initialize() {
		     
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame=new JFrame("µÇÂ¼ÏµÍ³");
    	//modify title image
    	frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/src/images/pill.gif")));
        //add top image
    	frame.setContentPane(getJContentPane());
        JPanel jp=new JPanel(new BorderLayout());
        JLabel jp1=new JLabel(SwingResourceManager.getIcon(DoctorLogin.class, "images/head2.gif"));
        jp1.setHorizontalAlignment(SwingConstants.CENTER);
    	//add name and password
        name=new JTextField("",11);
        //name
        password=new JPasswordField("",11);
        password.setEchoChar('*');

        JPanel jp2=new JPanel(new GridLayout(1,2));
        JPanel jp20=new JPanel(new BorderLayout());
        jp20.setBackground(new Color(218,242,16));
        JPanel jp21=new JPanel(new GridLayout(2,1));
        JPanel jp211=new JPanel(new FlowLayout());
        jp211.setBackground(new Color(218,242,16));
        jp211.add(new JLabel("ÐÕÃû "));jp211.add(name);
        JPanel jp212=new JPanel(new FlowLayout());
        jp212.setBackground(new Color(218,242,16));
        jp212.add(new JLabel("ÃÜÂë "));jp212.add(password);
        jp21.add(jp211);jp21.add(jp212);
        jp20.add("North",jp21);
    	//add anther image
        JPanel jp22=new JPanel(new BorderLayout());
        jp22.add("North",new JLabel(new ImageIcon("images/logo.gif")));
        jp22.setBackground(new Color(218,242,16));
        jp2.add(jp22);jp2.add(jp20);

        final JLabel label = new JLabel();
        label.setIcon(SwingResourceManager.getIcon(DoctorLogin.class, "images/logo.gif"));
        jp22.add(label, BorderLayout.CENTER);
        JPanel jp3=new JPanel(new GridLayout(1,2));
        JPanel jp31=new JPanel(new FlowLayout());
        jp31.setBackground(new Color(84,85,78));
        JPanel jp32=new JPanel(new FlowLayout());
        jp32.setBackground(new Color(84,85,78));
    	//add bottom button
        JButton loading=new JButton("µÇÂ¼");
        loading.addActionListener(this);
        JButton exit=new JButton("ÍË³ö");
        exit.addActionListener(this);
        jp31.add(loading);jp32.add(exit);
        jp3.add(jp31);jp3.add(jp32);
        jp.add("North",jp1);jp.add("Center",jp2);jp.add("South",jp3);
        frame.add(jp);
        frame.setResizable(false);		
		Dimension initFrame=new Dimension(440,255);
		frame.setSize(initFrame);
		
        frame.setLocation(200,200);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
