package com.romullogirardi.wisconsin.model;

import javax.swing.ImageIcon;

import com.romullogirardi.wisconsin.model.Enums.Color;
import com.romullogirardi.wisconsin.model.Enums.Number;
import com.romullogirardi.wisconsin.model.Enums.Shape;

public class Card {
	
	//ATTRIBUTES
	private Color color;
	private Shape shape;
	private Number number;
	
	//CONSTRUCTOR
	public Card(Color color, Shape shape, Number number) {
		this.color = color;
		this.shape = shape;
		this.number = number;
	}

	//GETTERS AND SETTERS
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Shape getShape() {
		return shape;
	}
	
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	
	public Number getNumber() {
		return number;
	}
	
	public void setNumber(Number number) {
		this.number = number;
	}
	
	//OTHER METHODS
	public String toString() {
		return "{" + color.toString() + ", " + shape.toString() + ", " + number.toString() + "}";
	}
	
	public ImageIcon getDrawableId() {
		
		String imageFileName = "";
		
		if(color.equals(Color.BLUE)) {
			if(shape.equals(Shape.CIRCLE)) {
				switch (number) {
					case ONE:
						imageFileName += "blue_circle_one.png";
						break;
					case TWO:
						imageFileName += "blue_circle_two.png";
						break;
					case THREE:
						imageFileName += "blue_circle_three.png";
						break;
					case FOUR:
						imageFileName += "blue_circle_four.png";
						break;
				}
			}
			else if(shape.equals(Shape.CROSS)) {
				switch (number) {
					case ONE:
						imageFileName += "blue_cross_one.png";
						break;
					case TWO:
						imageFileName += "blue_cross_two.png";
						break;
					case THREE:
						imageFileName += "blue_cross_three.png";
						break;
					case FOUR:
						imageFileName += "blue_cross_four.png";
						break;
				}
			}
			else if(shape.equals(Shape.STAR)) {
				switch (number) {
					case ONE:
						imageFileName += "blue_star_one.png";
						break;
					case TWO:
						imageFileName += "blue_star_two.png";
						break;
					case THREE:
						imageFileName += "blue_star_three.png";
						break;
					case FOUR:
						imageFileName += "blue_star_four.png";
						break;
				}
			}
			else if(shape.equals(Shape.TRIANGLE)) {
				switch (number) {
					case ONE:
						imageFileName += "blue_triangle_one.png";
						break;
					case TWO:
						imageFileName += "blue_triangle_two.png";
						break;
					case THREE:
						imageFileName += "blue_triangle_three.png";
						break;
					case FOUR:
						imageFileName += "blue_triangle_four.png";
						break;
				}
			}
		}
		else if(color.equals(Color.GREEN)) {
			if(shape.equals(Shape.CIRCLE)) {
				switch (number) {
					case ONE:
						imageFileName += "green_circle_one.png";
						break;
					case TWO:
						imageFileName += "green_circle_two.png";
						break;
					case THREE:
						imageFileName += "green_circle_three.png";
						break;
					case FOUR:
						imageFileName += "green_circle_four.png";
						break;
				}
			}
			else if(shape.equals(Shape.CROSS)) {
				switch (number) {
					case ONE:
						imageFileName += "green_cross_one.png";
						break;
					case TWO:
						imageFileName += "green_cross_two.png";
						break;
					case THREE:
						imageFileName += "green_cross_three.png";
						break;
					case FOUR:
						imageFileName += "green_cross_four.png";
						break;
				}
			}
			else if(shape.equals(Shape.STAR)) {
				switch (number) {
					case ONE:
						imageFileName += "green_star_one.png";
						break;
					case TWO:
						imageFileName += "green_star_two.png";
						break;
					case THREE:
						imageFileName += "green_star_three.png";
						break;
					case FOUR:
						imageFileName += "green_star_four.png";
						break;
				}
			}
			else if(shape.equals(Shape.TRIANGLE)) {
				switch (number) {
					case ONE:
						imageFileName += "green_triangle_one.png";
						break;
					case TWO:
						imageFileName += "green_triangle_two.png";
						break;
					case THREE:
						imageFileName += "green_triangle_three.png";
						break;
					case FOUR:
						imageFileName += "green_triangle_four.png";
						break;
				}
			}
		}
		else if(color.equals(Color.RED)) {
			if(shape.equals(Shape.CIRCLE)) {
				switch (number) {
					case ONE:
						imageFileName += "red_circle_one.png";
						break;
					case TWO:
						imageFileName += "red_circle_two.png";
						break;
					case THREE:
						imageFileName += "red_circle_three.png";
						break;
					case FOUR:
						imageFileName += "red_circle_four.png";
						break;
				}
			}
			else if(shape.equals(Shape.CROSS)) {
				switch (number) {
					case ONE:
						imageFileName += "red_cross_one.png";
						break;
					case TWO:
						imageFileName += "red_cross_two.png";
						break;
					case THREE:
						imageFileName += "red_cross_three.png";
						break;
					case FOUR:
						imageFileName += "red_cross_four.png";
						break;
				}
			}
			else if(shape.equals(Shape.STAR)) {
				switch (number) {
					case ONE:
						imageFileName += "red_star_one.png";
						break;
					case TWO:
						imageFileName += "red_star_two.png";
						break;
					case THREE:
						imageFileName += "red_star_three.png";
						break;
					case FOUR:
						imageFileName += "red_star_four.png";
						break;
				}
			}
			else if(shape.equals(Shape.TRIANGLE)) {
				switch (number) {
					case ONE:
						imageFileName += "red_triangle_one.png";
						break;
					case TWO:
						imageFileName += "red_triangle_two.png";
						break;
					case THREE:
						imageFileName += "red_triangle_three.png";
						break;
					case FOUR:
						imageFileName += "red_triangle_four.png";
						break;
				}
			}
		}
		else if(color.equals(Color.YELLOW)) {
			if(shape.equals(Shape.CIRCLE)) {
				switch (number) {
					case ONE:
						imageFileName += "yellow_circle_one.png";
						break;
					case TWO:
						imageFileName += "yellow_circle_two.png";
						break;
					case THREE:
						imageFileName += "yellow_circle_three.png";
						break;
					case FOUR:
						imageFileName += "yellow_circle_four.png";
						break;
				}
			}
			else if(shape.equals(Shape.CROSS)) {
				switch (number) {
					case ONE:
						imageFileName += "yellow_cross_one.png";
						break;
					case TWO:
						imageFileName += "yellow_cross_two.png";
						break;
					case THREE:
						imageFileName += "yellow_cross_three.png";
						break;
					case FOUR:
						imageFileName += "yellow_cross_four.png";
						break;
				}
			}
			else if(shape.equals(Shape.STAR)) {
				switch (number) {
					case ONE:
						imageFileName += "yellow_star_one.png";
						break;
					case TWO:
						imageFileName += "yellow_star_two.png";
						break;
					case THREE:
						imageFileName += "yellow_star_three.png";
						break;
					case FOUR:
						imageFileName += "yellow_star_four.png";
						break;
				}
			}
			else if(shape.equals(Shape.TRIANGLE)) {
				switch (number) {
					case ONE:
						imageFileName += "yellow_triangle_one.png";
						break;
					case TWO:
						imageFileName += "yellow_triangle_two.png";
						break;
					case THREE:
						imageFileName += "yellow_triangle_three.png";
						break;
					case FOUR:
						imageFileName += "yellow_triangle_four.png";
						break;
				}
			}
		}
		
		if(imageFileName.isEmpty()) {
			imageFileName = "empty_card.png";
		}
//		return ImageManipulationUtils.getInstance().getScaledImage(new ImageIcon(Card.class.getResource(Constants.IMAGES_FOLDER + "/" + imageFileName)), Constants.CARD_WIDTH, Constants.CARD_HEIGHT);
		return new ImageIcon(Card.class.getResource(Constants.IMAGES_FOLDER + "/" + imageFileName));
	}
	
	public static ImageIcon getEmptyCardIcon() {
//		return ImageManipulationUtils.getInstance().getScaledImage(new ImageIcon(Card.class.getResource(Constants.IMAGES_FOLDER + "/" + "empty_card.png")), Constants.CARD_WIDTH, Constants.CARD_HEIGHT);
		return new ImageIcon(Card.class.getResource(Constants.IMAGES_FOLDER + "/" + "empty_card.png"));
	}
}