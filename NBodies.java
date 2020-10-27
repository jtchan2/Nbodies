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
	private List<String[]> storage;
	private List<CelestBody> eList;


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
		public int getXPos(){
			return this.xCoordinate;
		}
		public int getYPos(){
			return this.yCoordinate;
		}
		public int bodySize(){
			return this.bodySize;
		}
	}
	
	Timer tm= new Timer(5, this);
	int x=384, velx=2;
	int y=348, vely=12;

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillOval(x,y,20,20);
		tm.start();
	}

	public void actionPerformed(ActionEvent e){
		if(x<0||x>748){
			velx= -velx;
		}
		if(y<0||y>748){
			vely= -vely;
		}
		x=x+velx;
		y=y+vely;
		repaint();
	}
	//stopped here @10:49 10/23 making file reader
	// @10/26/2020 made working file reader

	public void NBodiesCreator(String fileName) throws IOException{

		String fileInput= fileName;
		storage = new ArrayList<String[]>();
		try{
			BufferedReader read= new BufferedReader(new FileReader(fileInput));
			String line="";
			while((line=read.readLine())!=null){
				storage.add(line.split(","));
			}
		}catch(Exception ex){
			System.out.println("File Cannot be Found");
		}
		System.out.println(storage.get(0)[0]);
		if(storage.get(0)[0].equals("ArrayList")){
			System.out.println("Creating ArrayList");
			eList= new ArrayList<CelestBody>();
		}
		if(storage.get(0)[0].equals("LinkedList")){
			System.out.println("Creating LinkedList");
			eList=new LinkedList1<CelestBody>();
		}
		//for(int i=2; i<storage.size(); i++){

		//}

	}
	



	public static void main(String[] args) throws IOException{
		CelestBody p1= new CelestBody("Earth", 20000, 20, 5,16, 3000, 20000);

		System.out.println(p1.giveName());

		String fileName="nbody_input.txt";

		NBodies t= new NBodies();

		t.NBodiesCreator(fileName);
		
		JFrame jf= new JFrame();

		jf.setTitle("Canvas");
		jf.setSize(768,768);
		jf.add(t);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}