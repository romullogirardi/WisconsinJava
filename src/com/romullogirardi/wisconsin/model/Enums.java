package com.romullogirardi.wisconsin.model;

public class Enums {

	public enum Color {
		RED, GREEN, BLUE, YELLOW;
	}
	
	public enum Shape {
		CIRCLE, TRIANGLE, CROSS, STAR; 
	}
	
	public enum Number {
		ONE, TWO, THREE, FOUR;
	}
	
	public enum Strategy {
		COLOR, SHAPE, NUMBER, OTHER;
		
		public String toString() {
			switch (this) {
			case COLOR:
				return "COR";
			case SHAPE:
				return "FORMA";
			case NUMBER:
				return "NÚMERO";
			case OTHER:
				return "OUTRA";
			default:
				return "INDEFINIDO";
			}
		}
		
		public String getFirstLetter() {
			switch (this) {
			case COLOR:
				return "C";
			case SHAPE:
				return "F";
			case NUMBER:
				return "N";
			case OTHER:
				return "O";
			default:
				return "I";
			}
		}
	}
}