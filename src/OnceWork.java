package src;

import java.sql.SQLException;

import javax.swing.*;
import java.awt.*;

public class OnceWork extends JFrame{

  public OnceWork() throws SQLException{
  super("临时工作站");
  setSize(650,400);
  setBackground(Color.WHITE);
  setResizable(false);
  setBackground(Color.white);
  setLayout(new BorderLayout());
  setIconImage(SwingResourceManager.getImage(OnceWork.class, "images/pill.gif"));
  //add two panel
  JTabbedPane tabbedPane1=new JTabbedPane();
  tabbedPane1.addTab("登记个人信息",new PersonIn());
  tabbedPane1.addTab("记录病例信息",new InputCase());
  tabbedPane1.addTab("查询病例信息",new SearchSickInfo());
  add("Center",tabbedPane1);
  setLocationRelativeTo(null);
  setVisible(true);
  }
}