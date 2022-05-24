package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Main implements ActionListener {
  Main() {
    JFrame f = new JFrame("Rescue Simulation");
    JLabel t=new JLabel();
    t.setText("Disaster City");
    t.setBounds(550,100,500,250);    
    t.setFont(t.getFont().deriveFont(82.0f));
    t.setForeground(Color.WHITE);
    f.add(t);
    f.setSize(new Dimension(1600,900));
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setResizable(false);
    JMenuBar jmb = new JMenuBar();
    JMenu jmFile = new JMenu("File");
    JMenuItem jmiOpen = new JMenuItem("Open");
    JMenuItem jmiClose = new JMenuItem("Close");
    JMenuItem jmiSave = new JMenuItem("Save");
    JMenuItem jmiExit = new JMenuItem("Exit");
    jmFile.add(jmiOpen);
    jmFile.add(jmiClose);
    jmFile.add(jmiSave);
    jmFile.addSeparator();
    jmFile.add(jmiExit);
    jmb.add(jmFile);

    JMenu jmOptions = new JMenu("Options");
    JMenu a = new JMenu("Link To A Player");

    jmOptions.add(a);
    jmb.add(jmOptions);

    JMenu jmHelp = new JMenu("Help");
    JMenuItem jmiAbout = new JMenuItem("About");
    jmHelp.add(jmiAbout);
    jmb.add(jmHelp);

    jmiOpen.addActionListener(this);
    jmiClose.addActionListener(this);
    jmiSave.addActionListener(this);
    jmiExit.addActionListener(this);

    jmiAbout.addActionListener(this);

    f.setJMenuBar(jmb);
    JPanel panel=new JPanel();
	JLabel back = new JLabel();
	back.setBounds(0,0,1600,900);
	back.setIcon(new ImageIcon("C:\\game.jpg"));
	JLabel T=new JLabel();
	T.setForeground(Color.WHITE);
	T.setSize(200,200);
	//////START BUTTON
	ImageIcon myImage = new ImageIcon("D:\\play.png");
	JButton start = new JButton(myImage);
	start.setOpaque(false);
	start.setContentAreaFilled(false);
	start.setBorderPainted(false);
	start.setBounds(600,200,400,400);
	f.add(start);
	//////EXIT BUTTON
	ImageIcon myImage2 = new ImageIcon("D:\\exit2.gif");
	JButton exit = new JButton(myImage2);
	exit.setOpaque(false);
	exit.setContentAreaFilled(false);
	exit.setBorderPainted(false);
	exit.setBounds(600,600,400,205);
	f.add(exit);
	f.getContentPane().add(panel);
	Random rand = new Random();
	float r = rand.nextFloat();
	float g = rand.nextFloat();
	float b1= rand.nextFloat();
	Color rcolor = new Color(r, g, b1);
	panel.setBackground(Color.WHITE);
	f.add(back);
	f.setVisible(true);
    f.setVisible(true);
  }
  public void actionPerformed(ActionEvent ae) {
    String comStr = ae.getActionCommand();
    System.out.println(comStr + " Selected");
  }
  public static void main(String args[]) {
    new Main();
  }
}