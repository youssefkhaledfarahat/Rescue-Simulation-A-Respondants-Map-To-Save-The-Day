package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.Random;
import sun.audio.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.io.*;

public class GUI {
	
	public static void main(String[]args) {
		JFrame frame=new JFrame();
		frame.setSize(new Dimension(1600,900));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Rescue Simulation");
		frame.setResizable(false);
		JPanel panel=new JPanel();
		JLabel back = new JLabel();
		back.setBounds(0,0,1600,900);
		back.setIcon(new ImageIcon("C:\\game.jpg"));
		JLabel T=new JLabel();
		T.setForeground(Color.WHITE);
		T.setSize(200,200);
		//////START BUTTON
		JButton start = new JButton("Start The Game");
		start.setBounds(600,350,300,100);
		frame.add(start);
		//////EXIT BUTTON
		JButton exit = new JButton("Exit Game");
		exit.setBounds(600,500,300,100);
		frame.add(exit);
		
		frame.getContentPane().add(panel);
		Random rand = new Random();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		Color rcolor = new Color(r ,g, b);
		panel.setBackground(Color.WHITE);
		frame.add(back);
		frame.setVisible(true);
		
		
	}
	
}
