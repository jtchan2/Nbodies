import java.util.*;

import javax.swing.JPanel;

import javax.swing.JFrame;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.awt.Color;
import java.io.IOException;
import java.io.*;

public class NBodies extends JPanel{
	private String name;
	private int mass;
	private int xCoordinate;
	private int yCoordinate;
	private int xDirectVelocity;
	private int yDirectVelocity;
	private int bodySize;

	private static class CelestBody{
		private String name;
		private int mass;
		private int xCoordinate;
		private int yCoordinate;
		private int xDirectVelocity;
		private int yDirectVelocity;
		private int bodySize;

		public CelestBody(String name, int mass, int xCoordinate, int yCoordinate, int xDirectVelocity, int yDirectVelocity, int size){
			this.name=name;
			this.mass=mass;
			this.xCoordinate=xCoordinate;
			this.yCoordinate=yCoordinate;
			this.xDirectVelocity=xDirectVelocity;
			this.yDirectVelocity=yDirectVelocity;
			this.bodySize=bodySize;
		}
		public String giveName(){
			return this.name;
		}
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.drawOval(100,100,50,50);
		g.setColor(Color.YELLOW);
		g.fillOval(100,200,50,50);
	}
	public static void main(String[] args){
		CelestBody p1= new CelestBody("Earth", 20000, 20, 5,16, 3000, 20000);
		System.out.println(p1.giveName());

		NBodies t= new NBodies();
		JFrame jf= new JFrame();

		jf.setTitle("Canvas");
		jf.setSize(600,400);
		jf.add(t);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



	}

}