package com.romullogirardi.wisconsin.model;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.romullogirardi.wisconsin.model.Enums.Strategy;

public class Manager {

	//ATTRIBUTES
	private String userName = new String();
	private int userAge = 0;
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
	private ArrayList<Movement> movements = new ArrayList<Movement>();
	private boolean gameFinished = false;
	private int repeatedSuccessCounter = 0;
	private int categorySequenceNumber = 1;
	private int cardToBePlayedIndex = 0;
	private Strategy perseverativeStrategy = null;
	
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

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
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

	public ArrayList<Movement> getMovements() {
		return movements;
	}
	
	public boolean isGameFinished() {
		return gameFinished;
	}

	public int getCategorySequenceNumber() {
		return categorySequenceNumber;
	}

	public void setCategorySequenceNumber(int categorySequenceNumber) {
		this.categorySequenceNumber = categorySequenceNumber;
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
		boolean contextMaintainFail = false;
		boolean conceptualLevelAnswer = false;
		
		//Referência para os dois movimentos anteriores
		Movement previousMovement = null;
		Movement previousPreviousMovement = null;
		if(movements.size() > 0)
			previousMovement = movements.get(movements.size() - 1);
		if(movements.size() > 1)
			previousPreviousMovement = movements.get(movements.size() - 2);
		
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
				this.repeatedSuccessCounter++;
			else
				this.repeatedSuccessCounter = 1;
		}
		else {
			//Controle de falha ao manter o contexto
			if(repeatedSuccessCounter >= 5)
				contextMaintainFail = true;
			this.repeatedSuccessCounter = 0;
		}
		if(!colorSuccess && !shapeSuccess && !numberSuccess) {
			otherSuccess = true;
		}
		
		//Controle de ambiguidade
		if((colorSuccess && shapeSuccess) || (colorSuccess && numberSuccess) || (shapeSuccess && numberSuccess)) {
			ambiguous = true;
		}

		//Controle da estratégia perseverativa
		if(!success && !ambiguous) {
			Strategy wrongStrategy = null;			
			wrongStrategy = ((colorSuccess) ? Strategy.COLOR : 
										(shapeSuccess) ? Strategy.SHAPE : 
										(numberSuccess) ? Strategy.NUMBER : null);
			if(perseverativeStrategy == null) {
				perseverativeStrategy = wrongStrategy;
			}
			else {
				if(wrongStrategy != null && previousMovement != null && previousPreviousMovement != null) {
					Strategy previousMovementWrongStrategy = null;			
					previousMovementWrongStrategy = ((previousMovement.isColorSuccess()) ? Strategy.COLOR : 
												(previousMovement.isShapeSuccess()) ? Strategy.SHAPE : 
												(previousMovement.isNumberSuccess()) ? Strategy.NUMBER : null);
					Strategy previousPreviousMovementWrongStrategy = null;			
					previousPreviousMovementWrongStrategy = ((previousPreviousMovement.isColorSuccess()) ? Strategy.COLOR : 
												(previousPreviousMovement.isShapeSuccess()) ? Strategy.SHAPE : 
												(previousPreviousMovement.isNumberSuccess()) ? Strategy.NUMBER : null);
					if(wrongStrategy.equals(previousMovementWrongStrategy) && wrongStrategy.equals(previousPreviousMovementWrongStrategy)) {
						previousMovement.setPerseverativeStrategy(wrongStrategy);
						perseverativeStrategy = wrongStrategy;
					}
				}
			}
		}
		
		//Controle de resposta de nível conceitual
		if(previousMovement != null && previousPreviousMovement != null) {
			if(success && previousMovement.isSuccess() && previousPreviousMovement.isSuccess()) {
				conceptualLevelAnswer = true;
				previousMovement.setConceptualLevelAnswer(true);
				previousPreviousMovement.setConceptualLevelAnswer(true);
			}
		}
		
		Movement movement = new Movement(categorySequenceNumber, strategy, previousMovement, 
				repeatedSuccessCounter, success, colorSuccess, shapeSuccess, numberSuccess, otherSuccess, 
				ambiguous, perseverativeStrategy, contextMaintainFail, conceptualLevelAnswer);
		movements.add(movement);
		changeLastCardInPosition(position);
		if(this.repeatedSuccessCounter >= Constants.SUCCESS_COUNTER_CHANGE_POINT) {
			changeStrategy();
		}
		nextCardToBePlayed();
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
		repeatedSuccessCounter = 0;
		categorySequenceNumber++;
	}
	
	private void nextCardToBePlayed() {
		
		//If cards to be played are finished
		if(cardToBePlayedIndex == (cardsToBePlayed.size() - 1)) {
			cardToBePlayed = null;
			gameFinished = true;
		}
		else {
			cardToBePlayed = cardsToBePlayed.get(++cardToBePlayedIndex);
			//If 6 categories are completed
			if(categorySequenceNumber >= 7) {
				gameFinished = true;
			}
		}
	}
	
	public void setMovementsPerseverativity() {

		for(int index = 0; index < movements.size(); index++) {

			Movement movement = movements.get(index);
			
			//1º caso
			Strategy wrongStrategy = null;
			if(!movement.isSuccess() && !movement.isAmbiguous()) {
				wrongStrategy = ((movement.isColorSuccess()) ? Strategy.COLOR : 
									(movement.isShapeSuccess()) ? Strategy.SHAPE : 
									(movement.isNumberSuccess()) ? Strategy.NUMBER : null);
				if(movement.getPerseverativeStrategy() != null && 
						movement.getPerseverativeStrategy().equals(wrongStrategy)) {
					movement.setPerseverative(true);
				}
			}
			//2º caso
			if(index > 0) {
				Movement previousMovement = movements.get(index - 1);
				if(previousMovement != null && !previousMovement.getCurrentStrategy().equals(strategy)) {
					if(wrongStrategy != null && wrongStrategy.equals(previousMovement.getCurrentStrategy())) {
						movement.setPerseverative(true);
					}
				}
			}
			//3º caso
			if(movement.isAmbiguous() && movement.getPerseverativeStrategy() != null) {
				
				boolean firstConditionSatisfied = false;
				
				//1º condição
				switch (movement.getPerseverativeStrategy()) {
					case COLOR:
						if(movement.isColorSuccess())
							firstConditionSatisfied = true;
						break;
					case SHAPE:
						if(movement.isShapeSuccess())
							firstConditionSatisfied = true;
						break;
					case NUMBER:
						if(movement.isNumberSuccess())
							firstConditionSatisfied = true;
						break;
					default:
						break;
				}
				//2ª condição
				if(firstConditionSatisfied) {
					Movement previousClosestNoAmbiguousMovement = null;
					Movement nextClosestNoAmbiguousMovement = null;
					int indexTemp = index;
					while(indexTemp > 0 && previousClosestNoAmbiguousMovement == null) {
						indexTemp--;
						if(!movements.get(indexTemp).isAmbiguous())
							previousClosestNoAmbiguousMovement = movements.get(indexTemp);
					}
					indexTemp = index;
					while(indexTemp < (movements.size() - 1) && nextClosestNoAmbiguousMovement == null) {
						indexTemp++;
						if(!movements.get(indexTemp).isAmbiguous())
							nextClosestNoAmbiguousMovement = movements.get(indexTemp);
					}
					if(previousClosestNoAmbiguousMovement != null && nextClosestNoAmbiguousMovement != null &&
							previousClosestNoAmbiguousMovement.isPerseverative() && nextClosestNoAmbiguousMovement.isPerseverative() &&
							previousClosestNoAmbiguousMovement.getPerseverativeStrategy() != null && previousClosestNoAmbiguousMovement.getPerseverativeStrategy().equals(movement.getPerseverativeStrategy()) &&
							nextClosestNoAmbiguousMovement.getPerseverativeStrategy() != null && nextClosestNoAmbiguousMovement.getPerseverativeStrategy().equals(movement.getPerseverativeStrategy()))
								movement.setPerseverative(true);
				}
			}
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

		int numberOfConceptualLevelAnswer = 0;
		for(Movement movement : movements) {
			if(movement.isConceptualLevelAnswer()) {
				numberOfConceptualLevelAnswer++;
			}
		}
		return numberOfConceptualLevelAnswer;
	}

	public int getNumberOfContextMaintainFailures() {

		int numberOfContextMaintainFailures = 0;
		for(Movement movement : movements) {
			if(movement.isContextMaintainFail()) {
				numberOfContextMaintainFailures++;
			}
		}
		return numberOfContextMaintainFailures;
	}

	public int getNumberOfTriesToCompleteFirstCategory() {

		int numberOfTriesToCompleteFirstCategory = 0;
		for(Movement movement : movements) {
			if(movement.getCategorySequenceNumber() == 1) {
				numberOfTriesToCompleteFirstCategory++;
			}
		}
		return numberOfTriesToCompleteFirstCategory;
	}
	
	public int getMovementsByCategory(int categorySequenceNumber) {
		
		int numberOfMovements = 0;
		for(Movement movement : movements) {
			if(movement.getCategorySequenceNumber() == categorySequenceNumber) {
				numberOfMovements++;
			}
		}
		return numberOfMovements;
	}

	public int getErrorsByCategory(int categorySequenceNumber) {
		
		int numberOfErrors = 0;
		for(Movement movement : movements) {
			if(movement.getCategorySequenceNumber() == categorySequenceNumber && !movement.isSuccess()) {
				numberOfErrors++;
			}
		}
		return numberOfErrors;
	}
}