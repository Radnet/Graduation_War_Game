package model;

import java.io.Serializable;

public class Ponto implements Serializable {
	private double x;
	private double y;
	
	public Ponto(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
}
