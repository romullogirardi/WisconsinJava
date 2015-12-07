package com.romullogirardi.wisconsin.model;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.romullogirardi.wisconsin.model.Enums.Strategy;

public class Manager {

	//ATTRIBUTES
	private String userName = new String();
	private Calendar initialTime;
	private Calendar finalTime;
	private Strategy strategy = Constants.INITIAL_STRATEGY;
	private Card referenceCard1 = Constants.REFERENCE_CARD_1;
	private Card referenceCard2 = Constants.REFERENCE_CARD_2;
	private Card referenceCard3 = Constants.REFERENCE_CARD_3;
	private Card referenceCard4 = Constants.REFERENCE_CARD_4;
	private Card lastCardInPosition1 = null;
	private Card lastCardInPosition2 = null;
	private Card lastCardInPosition3 = null;
	private Card lastCardInPosition4 = null;
	private Card cardToBePlayed = null;
	private ArrayList<Card> cardsToBePlayed = new ArrayList<Card>();
//	private boolean lastMovementSuccess = false;
	private ArrayList<Movement> movements = new ArrayList<Movement>();
	private boolean gameFinished = false;
	private int successCounter = 0;
	private Strategy wrongPerseverativeStrategy = null;
	
	//SINGLETON IMPLEMENTATION
	private static Manager instance = null;

	public static Manager getInstance() {
		if (instance == null) {
			instance = new Manager();
		}
		return instance;
	}

	//CONSTRUCTOR
	public Manager() {
		cardsToBePlayed = Constants.generateCardsToBePlayed();
		cardToBePlayed = cardsToBePlayed.get(0);
	}
	
	//GETTERS AND SETTERS
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Calendar getInitialTime() {
		return initialTime;
	}

	public void setInitialTime(Calendar initialTime) {
		this.initialTime = initialTime;
	}

	public Calendar getFinalTime() {
		return finalTime;
	}

	public void setFinalTime(Calendar finalTime) {
		this.finalTime = finalTime;
	}

	public Strategy getStrategy() {
		return strategy;
	}

	public Card getReferenceCard1() {
		return referenceCard1;
	}

	public Card getReferenceCard2() {
		return referenceCard2;
	}

	public Card getReferenceCard3() {
		return referenceCard3;
	}

	public Card getReferenceCard4() {
		return referenceCard4;
	}

	public Card getLastCardInPosition1() {
		return lastCardInPosition1;
	}

	public Card getLastCardInPosition2() {
		return lastCardInPosition2;
	}

	public Card getLastCardInPosition3() {
		return lastCardInPosition3;
	}

	public Card getLastCardInPosition4() {
		return lastCardInPosition4;
	}

	public Card getCardToBePlayed() {
		return cardToBePlayed;
	}

	public ArrayList<Card> getCardsToBePlayed() {
		return cardsToBePlayed;
	}

//	public boolean isLastMovementSuccess() {
//		return lastMovementSuccess;
//	}

	public ArrayList<Movement> getMovements() {
		return movements;
	}
	
	public boolean isGameFinished() {
		return gameFinished;
	}

	//OTHER METHODS
	public void executeMovement(int position) {
		
		Card referenceCard = getReferenceCard(position);
		
		boolean success = false;
		boolean colorSuccess = false;
		boolean shapeSuccess = false;
		boolean numberSuccess = false;
		boolean otherSuccess = false;
		boolean ambiguous = false;
		boolean perseverative = false;
		
		//Referência para o movimento anterior
		Movement previousMovement = null;
		if(movements.size() > 0) {
			previousMovement = movements.get(movements.size() - 1);
		}
		
		//Controle de sucessos
		if(cardToBePlayed.getColor().equals(referenceCard.getColor())) {
			colorSuccess = true;
		}
		if(cardToBePlayed.getShape().equals(referenceCard.getShape())) {
			shapeSuccess = true;
		}
		if(cardToBePlayed.getNumber().equals(referenceCard.getNumber())) {
			numberSuccess = true;
		}
		if((strategy.equals(Strategy.COLOR) && colorSuccess) ||
			(strategy.equals(Strategy.SHAPE) && shapeSuccess) ||
			(strategy.equals(Strategy.NUMBER) && numberSuccess)) {
			success = true;
			if(previousMovement != null && previousMovement.isSuccess())
				this.successCounter++;
			else
				this.successCounter = 1;
		}
		else {
			this.successCounter = 0;
		}
		if(!colorSuccess && !shapeSuccess && !numberSuccess) {
			otherSuccess = true;
		}
		
		//Controle de ambiguidade
		if((colorSuccess && shapeSuccess) || (colorSuccess && numberSuccess) || (shapeSuccess && numberSuccess)) {
			ambiguous = true;
		}

		//Controle de perseveratividade
		//1º caso
		Strategy wrongStrategy = null;
		if(!success && !ambiguous) {
			wrongStrategy = ((colorSuccess) ? Strategy.COLOR : 
										(shapeSuccess) ? Strategy.SHAPE : 
										(numberSuccess) ? Strategy.NUMBER : Strategy.OTHER);
			if(wrongPerseverativeStrategy != null && wrongPerseverativeStrategy.equals(wrongStrategy)) {
				perseverative = true;
			}
			if(!wrongStrategy.equals(Strategy.OTHER)) {
				wrongPerseverativeStrategy = wrongStrategy;
			}
		}
		//2º caso
		if(previousMovement != null && previousMovement.getCurrentStrategy().equals(strategy)) {
			if(wrongStrategy != null && wrongStrategy.equals(previousMovement.getCurrentStrategy())) {
				perseverative = true;
			}
		}
		
		Movement movement = new Movement(strategy, (previousMovement != null) ? previousMovement.isSuccess() : false, 
				success, colorSuccess, shapeSuccess, numberSuccess, otherSuccess, ambiguous, perseverative);
		movements.add(movement);
		changeLastCardInPosition(position);
		nextCardToBePlayed();
		System.out.println(this.successCounter);
		if(this.successCounter >= Constants.SUCCESS_COUNTER_CHANGE_POINT) {
			changeStrategy();
		}
	}
	
	private Card getReferenceCard(int position) {
		
		switch (position) {
			case 1:
				return referenceCard1;
			case 2:
				return referenceCard2;
			case 3:
				return referenceCard3;
			case 4:
				return referenceCard4;
			default:
				return null;
		}
	}
	
	private void changeLastCardInPosition(int position) {

		switch (position) {
			case 1:
				lastCardInPosition1 = cardToBePlayed;
				break;
			case 2:
				lastCardInPosition2 = cardToBePlayed;
				break;
			case 3:
				lastCardInPosition3 = cardToBePlayed;
				break;
			case 4:
				lastCardInPosition4 = cardToBePlayed;
				break;
			default:
				break;
		}
	}
	
	private void changeStrategy() {
		
		switch (strategy) {
			case COLOR:
				strategy = Strategy.SHAPE;
				break;
			case SHAPE:
				strategy = Strategy.NUMBER;
				break;
			case NUMBER:
				strategy = Strategy.COLOR;
				break;
			default:
				break;
		}
		successCounter = 0;
	}
	
	private void nextCardToBePlayed() {
		int index = cardsToBePlayed.indexOf(cardToBePlayed);
		if(index == (cardsToBePlayed.size() - 1)) {
			cardToBePlayed = null;
			gameFinished = true;
		}
		else {
			cardToBePlayed = cardsToBePlayed.get(++index);
		}
	}
	
	public String getTestDate() {
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
		return dateFormat.format(initialTime.getTime());
	}
	
	public String getTestDuration() {
		long durationInMilliseconds = finalTime.getTimeInMillis() - initialTime.getTimeInMillis();
		long durationInSeconds = durationInMilliseconds / 1000;
		long seconds = durationInSeconds % 60;
		long minutes = durationInSeconds / 60;
		long hours = minutes / 60;
		minutes %= 60;
		return ((hours == 0) ? "" : (String.valueOf(hours) + ":")) + 
			((minutes >= 0 && minutes <= 9) ? String.valueOf("0" + minutes) : String.valueOf(minutes)) + ":" +
			((seconds >= 0 && seconds <= 9) ? String.valueOf("0" + seconds) : String.valueOf(seconds));
	}
	
	public int getNumberOfRightMovements() {
		
		int numberOfRightMovements = 0;
		for(Movement movement : movements) {
			if(movement.isSuccess()) {
				numberOfRightMovements++;
			}
		}
		return numberOfRightMovements;
	}
	
	public int getNumberOfWrongMovements() {
		
		int numberOfWrongMovements = 0;
		for(Movement movement : movements) {
			if(!movement.isSuccess()) {
				numberOfWrongMovements++;
			}
		}
		return numberOfWrongMovements;
	}
	
	public int getNumberOfPerseverativeMovements() {

		int numberOfPerseverativeMovements = 0;
		for(Movement movement : movements) {
			if(movement.isPerseverative()) {
				numberOfPerseverativeMovements++;
			}
		}
		return numberOfPerseverativeMovements;
	}

	public int getNumberOfPerseverativeErrors() {

		int numberOfPerseverativeErrors = 0;
		for(Movement movement : movements) {
			if(movement.isPerseverative() && !movement.isSuccess()) {
				numberOfPerseverativeErrors++;
			}
		}
		return numberOfPerseverativeErrors;
	}

	public int getNumberOfConceptualLevelAnswers() {

		int numberOfPerseverativeErrors = 0;
//		for(Movement movement : movements) {
//			if(movement.isPerseverative() && !movement.isSuccess()) {
//				numberOfPerseverativeErrors++;
//			}
//		}
		return numberOfPerseverativeErrors;
	}
}