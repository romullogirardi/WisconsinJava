package com.romullogirardi.wisconsin.model;

import java.awt.Toolkit;
import java.util.ArrayList;

import com.romullogirardi.wisconsin.model.Enums.Color;
import com.romullogirardi.wisconsin.model.Enums.Number;
import com.romullogirardi.wisconsin.model.Enums.Shape;
import com.romullogirardi.wisconsin.model.Enums.Strategy;

public class Constants {

	public static final Strategy INITIAL_STRATEGY = Strategy.COLOR;
	public static final Card REFERENCE_CARD_1 = new Card(Color.RED, Shape.TRIANGLE, Number.ONE);
	public static final Card REFERENCE_CARD_2 = new Card(Color.GREEN, Shape.STAR, Number.TWO);
	public static final Card REFERENCE_CARD_3 = new Card(Color.YELLOW, Shape.CROSS, Number.THREE);
	public static final Card REFERENCE_CARD_4 = new Card(Color.BLUE, Shape.CIRCLE, Number.FOUR);
	public static final int SUCCESS_COUNTER_CHANGE_POINT = 10;
	public static final String PDF_FILE_PATH = "C:\\Users\\Romullo Girardi\\Desktop\\Wisconsin";
	public static final String PDF_FILE_NAME_PREFIX = "";
	public static String IMAGES_FOLDER = "/com/romullogirardi/wisconsin/images";
	public static String ICON_IMAGE_FILE_NAME = "icone.png";
	public static String HEADER_IMAGE_FILE_NAME = "header.jpg";
	public static String BACKGROUND_IMAGE_FILE_NAME = "bg.jpg";
	public static final int CARD_WIDTH = 150;
	public static final int CARD_HEIGHT = 150;
	public static String SOUNDS_FOLDER = "/com/romullogirardi/wisconsin/sounds";
	public static String RIGHT_SOUND_FILE_NAME = "certo.wav";
	public static String WRONG_SOUND_FILE_NAME = "errado.wav";
	public static String FINISHED_TEST_SOUND_FILE_NAME = "teste_finalizado.wav";
	public static double DISPLAY_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static double DISPLAY_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public static int delta_y = 25;
	
	public static final ArrayList<Card> generateCardsToBePlayed() {
		
		ArrayList<Card> cardsToBePlayed = new ArrayList<Card>();

		ArrayList<Card> oneRoundSetOfCards = new ArrayList<Card>();
		oneRoundSetOfCards.add(new Card(Color.GREEN, Shape.TRIANGLE, Number.ONE));
		oneRoundSetOfCards.add(new Card(Color.RED, Shape.CROSS, Number.FOUR));
		oneRoundSetOfCards.add(new Card(Color.BLUE, Shape.TRIANGLE, Number.TWO));
		oneRoundSetOfCards.add(new Card(Color.RED, Shape.CIRCLE, Number.ONE));
		oneRoundSetOfCards.add(new Card(Color.GREEN, Shape.STAR, Number.FOUR));
		oneRoundSetOfCards.add(new Card(Color.YELLOW, Shape.CROSS, Number.ONE));
		oneRoundSetOfCards.add(new Card(Color.BLUE, Shape.TRIANGLE, Number.FOUR));
		oneRoundSetOfCards.add(new Card(Color.RED, Shape.CIRCLE, Number.THREE));
		oneRoundSetOfCards.add(new Card(Color.GREEN, Shape.CROSS, Number.FOUR));
		oneRoundSetOfCards.add(new Card(Color.YELLOW, Shape.CIRCLE, Number.TWO));
		oneRoundSetOfCards.add(new Card(Color.BLUE, Shape.STAR, Number.ONE));
		oneRoundSetOfCards.add(new Card(Color.RED, Shape.TRIANGLE, Number.THREE));
		oneRoundSetOfCards.add(new Card(Color.BLUE, Shape.CROSS, Number.TWO));
		oneRoundSetOfCards.add(new Card(Color.YELLOW, Shape.STAR, Number.ONE));
		oneRoundSetOfCards.add(new Card(Color.GREEN, Shape.TRIANGLE, Number.THREE));
		oneRoundSetOfCards.add(new Card(Color.BLUE, Shape.CIRCLE, Number.FOUR));
		oneRoundSetOfCards.add(new Card(Color.RED, Shape.STAR, Number.TWO));
		oneRoundSetOfCards.add(new Card(Color.YELLOW, Shape.CIRCLE, Number.THREE));
		oneRoundSetOfCards.add(new Card(Color.RED, Shape.TRIANGLE, Number.FOUR));
		oneRoundSetOfCards.add(new Card(Color.YELLOW, Shape.CIRCLE, Number.ONE));
		oneRoundSetOfCards.add(new Card(Color.BLUE, Shape.STAR, Number.TWO));
		oneRoundSetOfCards.add(new Card(Color.GREEN, Shape.CROSS, Number.THREE));
		oneRoundSetOfCards.add(new Card(Color.YELLOW, Shape.STAR, Number.TWO));
		oneRoundSetOfCards.add(new Card(Color.BLUE, Shape.TRIANGLE, Number.THREE));
		oneRoundSetOfCards.add(new Card(Color.RED, Shape.STAR, Number.FOUR));
		oneRoundSetOfCards.add(new Card(Color.YELLOW, Shape.TRIANGLE, Number.TWO));
		oneRoundSetOfCards.add(new Card(Color.BLUE, Shape.CROSS, Number.THREE));
		oneRoundSetOfCards.add(new Card(Color.RED, Shape.CIRCLE, Number.FOUR));
		oneRoundSetOfCards.add(new Card(Color.GREEN, Shape.STAR, Number.TWO));
		oneRoundSetOfCards.add(new Card(Color.RED, Shape.CROSS, Number.ONE));
		oneRoundSetOfCards.add(new Card(Color.GREEN, Shape.CIRCLE, Number.FOUR));
		oneRoundSetOfCards.add(new Card(Color.RED, Shape.STAR, Number.ONE));
		oneRoundSetOfCards.add(new Card(Color.BLUE, Shape.CIRCLE, Number.THREE));
		oneRoundSetOfCards.add(new Card(Color.YELLOW, Shape.CROSS, Number.FOUR));
		oneRoundSetOfCards.add(new Card(Color.GREEN, Shape.STAR, Number.ONE));
		oneRoundSetOfCards.add(new Card(Color.YELLOW, Shape.TRIANGLE, Number.FOUR));
		oneRoundSetOfCards.add(new Card(Color.BLUE, Shape.CIRCLE, Number.TWO));
		oneRoundSetOfCards.add(new Card(Color.YELLOW, Shape.STAR, Number.THREE));
		oneRoundSetOfCards.add(new Card(Color.GREEN, Shape.CIRCLE, Number.ONE));
		oneRoundSetOfCards.add(new Card(Color.BLUE, Shape.CROSS, Number.FOUR));
		oneRoundSetOfCards.add(new Card(Color.RED, Shape.TRIANGLE, Number.ONE));
		oneRoundSetOfCards.add(new Card(Color.BLUE, Shape.STAR, Number.THREE));
		oneRoundSetOfCards.add(new Card(Color.YELLOW, Shape.CIRCLE, Number.FOUR));
		oneRoundSetOfCards.add(new Card(Color.GREEN, Shape.TRIANGLE, Number.TWO));
		oneRoundSetOfCards.add(new Card(Color.RED, Shape.STAR, Number.THREE));
		oneRoundSetOfCards.add(new Card(Color.YELLOW, Shape.CROSS, Number.TWO));
		oneRoundSetOfCards.add(new Card(Color.BLUE, Shape.CIRCLE, Number.ONE));
		oneRoundSetOfCards.add(new Card(Color.YELLOW, Shape.TRIANGLE, Number.THREE));
		oneRoundSetOfCards.add(new Card(Color.GREEN, Shape.CROSS, Number.TWO));
		oneRoundSetOfCards.add(new Card(Color.YELLOW, Shape.STAR, Number.FOUR));
		oneRoundSetOfCards.add(new Card(Color.BLUE, Shape.TRIANGLE, Number.ONE));
		oneRoundSetOfCards.add(new Card(Color.GREEN, Shape.STAR, Number.THREE));
		oneRoundSetOfCards.add(new Card(Color.RED, Shape.CIRCLE, Number.TWO));
		oneRoundSetOfCards.add(new Card(Color.GREEN, Shape.TRIANGLE, Number.FOUR));
		oneRoundSetOfCards.add(new Card(Color.BLUE, Shape.CROSS, Number.ONE));
		oneRoundSetOfCards.add(new Card(Color.GREEN, Shape.CIRCLE, Number.THREE));
		oneRoundSetOfCards.add(new Card(Color.RED, Shape.CROSS, Number.TWO));
		oneRoundSetOfCards.add(new Card(Color.YELLOW, Shape.TRIANGLE, Number.ONE));
		oneRoundSetOfCards.add(new Card(Color.RED, Shape.CROSS, Number.THREE));
		oneRoundSetOfCards.add(new Card(Color.GREEN, Shape.CIRCLE, Number.TWO));
		oneRoundSetOfCards.add(new Card(Color.BLUE, Shape.STAR, Number.FOUR));
		oneRoundSetOfCards.add(new Card(Color.RED, Shape.TRIANGLE, Number.TWO));
		oneRoundSetOfCards.add(new Card(Color.YELLOW, Shape.CROSS, Number.THREE));
		oneRoundSetOfCards.add(new Card(Color.GREEN, Shape.CROSS, Number.ONE));
		
		cardsToBePlayed.addAll(oneRoundSetOfCards);
		cardsToBePlayed.addAll(oneRoundSetOfCards);
		
		return cardsToBePlayed;
	}
}