package com.romullogirardi.wisconsin.model;

import com.romullogirardi.wisconsin.model.Enums.Strategy;

public class Movement {

	//ATTRIBUTES
	private int categorySequenceNumber;
	private Strategy currentStrategy;
	private Movement previousMovement;
	private int repeatedSuccessCounter;
	private boolean success;
	private boolean colorSuccess;
	private boolean shapeSuccess;
	private boolean numberSuccess;
	private boolean otherSuccess;
	private boolean ambiguous;
	private boolean perseverative;
	
	//CONSTRUCTOR
	public Movement(int categorySequenceNumber, Strategy currentStrategy, Movement previousMovement, int repeatedSuccessCounter,
			boolean success, boolean colorSuccess, boolean shapeSuccess, boolean numberSuccess, boolean otherSuccess, boolean ambiguous, boolean perseverative) {
		this.categorySequenceNumber = categorySequenceNumber;
		this.currentStrategy = currentStrategy;
		this.previousMovement = previousMovement;
		this.repeatedSuccessCounter = repeatedSuccessCounter;
		this.success = success;
		this.colorSuccess = colorSuccess;
		this.shapeSuccess = shapeSuccess;
		this.numberSuccess = numberSuccess;
		this.otherSuccess = otherSuccess;
		this.ambiguous = ambiguous;
		this.perseverative = perseverative;
	}

	//GETTERS AND SETTERS
	public int getCategorySequenceNumber() {
		return categorySequenceNumber;
	}

	public void setCategorySequenceNumber(int categorySequenceNumber) {
		this.categorySequenceNumber = categorySequenceNumber;
	}

	public Strategy getCurrentStrategy() {
		return currentStrategy;
	}

	public void setCurrentStrategy(Strategy currentStrategy) {
		this.currentStrategy = currentStrategy;
	}

	public Movement getPreviousMovement() {
		return previousMovement;
	}

	public void setPreviousMovement(Movement previousMovement) {
		this.previousMovement = previousMovement;
	}

	public int getRepeatedSuccessCounter() {
		return repeatedSuccessCounter;
	}

	public void setRepeatedSuccessCounter(int repeatedSuccessCounter) {
		this.repeatedSuccessCounter = repeatedSuccessCounter;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isColorSuccess() {
		return colorSuccess;
	}

	public void setColorSuccess(boolean colorSuccess) {
		this.colorSuccess = colorSuccess;
	}

	public boolean isShapeSuccess() {
		return shapeSuccess;
	}

	public void setShapeSuccess(boolean shapeSuccess) {
		this.shapeSuccess = shapeSuccess;
	}

	public boolean isNumberSuccess() {
		return numberSuccess;
	}

	public void setNumberSuccess(boolean numberSuccess) {
		this.numberSuccess = numberSuccess;
	}
	
	public boolean isOtherSuccess() {
		return otherSuccess;
	}

	public void setOtherSuccess(boolean otherSuccess) {
		this.otherSuccess = otherSuccess;
	}

	public boolean isAmbiguous() {
		return ambiguous;
	}

	public void setAmbiguous(boolean ambiguous) {
		this.ambiguous = ambiguous;
	}

	public boolean isPerseverative() {
		return perseverative;
	}

	public void setPerseverative(boolean perseverative) {
		this.perseverative = perseverative;
	}

	//OTHER METHODS
	public String toString() {
		return "Estratégia corrente - " + currentStrategy.toString() + " / " +
				((success) ? "CERTO" : "ERRADO") + " / " +
				((colorSuccess) ? "COR IGUAL" : "COR DIFERENTE") + " / " +
				((shapeSuccess) ? "FORMA IGUAL" : "FORMA DIFERENTE") + " / " +
				((numberSuccess) ? "NÚMERO IGUAL" : "NÚMERO DIFERENTE");
	}
	
	public String getReportDescription() {
		
		String reportDescription = "";
		
		//Inserir análise de cor
		String colorResult = "C";
		reportDescription += colorResult + "  ";

		//Inserir análise de forma
		String shapeResult = "F";
		reportDescription += shapeResult + "  ";

		//Inserir análise de número
		String numberResult = "N";
		reportDescription += numberResult + "  ";

		//Inserir análise de cor
		String otherResult = "O";
		reportDescription += otherResult;
		
		return reportDescription;
	}
}