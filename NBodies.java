import java.util.*;

import javax.swing.JPanel;

import javax.swing.JFrame;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.awt.Color;
import java.io.IOException;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class NBodies extends JPanel implements ActionListener{
	private String name;
	private int mass;
	private int xCoordinate;
	private int yCoordinate;
	private int xDirectVelocity;
	private int yDirectVelocity;
	private int bodySize;
	private List<String[]> content;
	private List<CelestBody> arrList;


	private static class Node<E>{
		E data;
		Node<E> next;
		public Node(E value){
			data=value;
			next=null;
		}
	}

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
	//
	Timer tm= new Timer(5, this);
	int x=0, velx=2;

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillOval(x,100,50,50);
		tm.start();
	}

	public void actionPerformed(ActionEvent e){
		if(x<0||x>718){
			velx= -velx;
		}
		x=x+velx;
		repaint();
	}
	//stopped here @10:49 10/23 making file reader
	public NbodyCreator(String fileName) throws IOException{
		String fileInput= fileName;
		content= new ArrayList<>();
		try(BufferedReader read= new BufferedReader(new FileReader(fileInput))){

			String line="";
			while(line=read.readLine()){
				content.add(line.split(","));
			}
		} catch(Exception e){
			System.out.println("Error no file found");
		}

	}



	public static void main(String[] args){
		String fileName= "nbody_input.text";
		CelestBody p1= new CelestBody("Earth", 20000, 20, 5,16, 3000, 20000);
		System.out.println(p1.giveName());

		NBodies t= new NBodies();
		JFrame jf= new JFrame();

		jf.setTitle("Canvas");
		jf.setSize(768,768);
		jf.add(t);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}