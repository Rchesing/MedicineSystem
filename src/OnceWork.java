package src;

import java.sql.SQLException;

import javax.swing.*;
import java.awt.*;

public class OnceWork extends JFrame{

  public OnceWork() throws SQLException{
  super("��ʱ����վ");
  setSize(650,400);
  setBackground(Color.WHITE);
  setResizable(false);
  setBackground(Color.white);
  setLayout(new BorderLayout());
  setIconImage(SwingResourceManager.getImage(OnceWork.class, "images/pill.gif"));
  //add two panel
  JTabbedPane tabbedPane1=new JTabbedPane();
  tabbedPane1.addTab("�ǼǸ�����Ϣ",new PersonIn());
  tabbedPane1.addTab("��¼������Ϣ",new InputCase());
  tabbedPane1.addTab("��ѯ������Ϣ",new SearchSickInfo());
  add("Center",tabbedPane1);
  setLocationRelativeTo(null);
  setVisible(true);
  }
}