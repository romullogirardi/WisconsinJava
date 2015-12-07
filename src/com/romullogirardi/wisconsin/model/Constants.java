package com.romullogirardi.wisconsin.model;

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
	public static final int SUCCESS_COUNTER_CHANGE_POINT = 3;
	public static final int MINIMUM_SCORE_EXPECTED = 5;
	public static final String PDF_FILE_PATH = "generated_file/";
	public static final String PDF_FILE_NAME_PREFIX = "Teste de Wisconsin"; 
	
	public static final ArrayList<Card> generateCardsToBePlayed() {
		
		ArrayList<Card> cardsToBePlayed = new ArrayList<Card>();
		cardsToBePlayed.add(new Card(Color.RED, Shape.STAR, Number.THREE));
		cardsToBePlayed.add(new Card(Color.BLUE, Shape.TRIANGLE, Number.ONE));
		cardsToBePlayed.add(new Card(Color.GREEN, Shape.CROSS, Number.THREE));
		cardsToBePlayed.add(new Card(Color.YELLOW, Shape.STAR, Number.FOUR));
		cardsToBePlayed.add(new Card(Color.BLUE, Shape.TRIANGLE, Number.TWO));
		cardsToBePlayed.add(new Card(Color.RED, Shape.CROSS, Number.THREE));
		cardsToBePlayed.add(new Card(Color.BLUE, Shape.STAR, Number.ONE));
		cardsToBePlayed.add(new Card(Color.GREEN, Shape.CIRCLE, Number.THREE));
		cardsToBePlayed.add(new Card(Color.YELLOW, Shape.TRIANGLE, Number.FOUR));
		cardsToBePlayed.add(new Card(Color.BLUE, Shape.CROSS, Number.TWO));
		return cardsToBePlayed;
	}
}
