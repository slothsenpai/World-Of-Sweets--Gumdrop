package wos;

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.io.Serializable;

import wos.*;

public class Space implements Serializable{
	Player players[] = new Player[4];
	String color;
	String label;
	Shape cir;
	boolean start = false;
	boolean goal = false;
	int x, y, width, height;
	int playerIndex = 0;
	int positionA[] = new int[2];
	int positionB[] = new int[2];
	int positionC[] = new int[2];
	int positionD[] = new int[2];
	
	public Space(int x1, int x2, int y1, int y2, boolean start, boolean goal, String color, int labelInt){
		this.x = x1;
		this.y = y1;
		this.start = start;
		this.goal = goal;
		this.color = color;
		int width = x2 - x1;
		int height = y2 - y1;
		label = color + "-" + labelInt;
		if(start){
			cir = new Ellipse2D.Double(x, y, width, height);
			label = "start";
		}else if(goal){
			cir = new Rectangle2D.Double(x, y, width, height);
			label = "goal";
		}
		else{
			cir = new Ellipse2D.Double(x, y, width, height);
		}
		
		if(start){
			positionA[0] = x+width*2/10;
			positionA[1] = y+height*2/10;
			
			positionB[0] = x+width*6/10;
			positionB[1] = y+height*2/10;
			
			positionC[0] = x+width*2/10;
			positionC[1] = y+height*6/10;
			
			positionD[0] = x+width*6/10;
			positionD[1] = y+height*6/10;
		
		}
		else{
			positionA[0] = x+width*(1/3);
			positionA[1] = y+height*(1/3);
			
			positionB[0] = x+width*3/5;
			positionB[1] = y+height*(1/3);
			
			positionC[0] = x+width*(1/3);
			positionC[1] = y+height*3/7;
			
			positionD[0] = x+width*3/5;
			positionD[1] = y+height*3/7;
		}
		
	}
	
	// Will return an xy pair for one of the four free spaces within a space
	public int[] nextFreeSpace(int playerNum){
		int[] ret = new int[2];
		switch(playerNum){
			case 0:
			ret = positionA;
			break;
			
			case 1:
			ret = positionB;
			break;
			
			case 2:
			ret = positionC;
			break;
			
			case 3:
			ret = positionD;
			break;
		}
		int[] jitter = new int[2];
		jitter[0] = ThreadLocalRandom.current().nextInt(0, 5) - 3;
		jitter[1] = ThreadLocalRandom.current().nextInt(0, 5) - 3;
		ret[0] = ret[0] + jitter[0];
		ret[1] = ret[1] + jitter[1];
		return ret;
	}
	
	public boolean contains(int x, int y){
		double dX = (double) x;
		double dY = (double) y;
		return cir.contains(dX, dY);
	}
	
	public int getPIndex(){
		return playerIndex;
	}
	
	// Used in defining the dimensions of the space on the gameboard
	public Shape getSpace(){
		return cir;
	}
	
	public String getColor(){
		return color;
	}
	
	public boolean wasClicked(MouseEvent e){
		return cir.contains(e.getPoint());
	}
	
	public boolean isStart(){
		return start;
	}
	
	public boolean isGoal(){
		return goal;
	}
	
	public String getLabel(){
		return label;
	}
	
}