package com.romullogirardi.wisconsin.view;

import java.util.Calendar;

import com.romullogirardi.wisconsin.model.Constants;
import com.romullogirardi.wisconsin.model.Manager;
import com.romullogirardi.wisconsin.model.Movement;
import com.romullogirardi.wisconsin.model.Enums.Strategy;
import com.romullogirardi.wisconsin.utils.PDFGenerator;

public class MainTest {

	public static void main(String[] args) {
	
		//Setting user name and initial time
		Manager.getInstance().setUserName("Rômullo Girardi Moreira");
		Manager.getInstance().setInitialTime(Calendar.getInstance());
		
		//Setting movements
		Manager.getInstance().getMovements().add(new Movement(Strategy.COLOR, false, true, true, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.COLOR, true, true, false, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.COLOR, true, true, false, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.COLOR, true, false, true, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.SHAPE, false, false, true, true, false, false, true, true));
		Manager.getInstance().getMovements().add(new Movement(Strategy.SHAPE, false, true, true, true, false, false, true, true));
		Manager.getInstance().getMovements().add(new Movement(Strategy.SHAPE, true, true, true, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.SHAPE, true, true, true, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.COLOR, false, true, true, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.COLOR, true, true, false, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.COLOR, true, true, false, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.COLOR, true, false, true, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.SHAPE, false, false, true, true, false, false, true, true));
		Manager.getInstance().getMovements().add(new Movement(Strategy.SHAPE, false, true, true, true, false, false, true, true));
		Manager.getInstance().getMovements().add(new Movement(Strategy.SHAPE, true, true, true, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.SHAPE, true, true, true, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.COLOR, false, true, true, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.COLOR, true, true, false, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.COLOR, true, true, false, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.COLOR, true, false, true, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.SHAPE, false, false, true, true, false, false, true, true));
		Manager.getInstance().getMovements().add(new Movement(Strategy.SHAPE, false, true, true, true, false, false, true, true));
		Manager.getInstance().getMovements().add(new Movement(Strategy.SHAPE, true, true, true, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.SHAPE, true, true, true, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.COLOR, false, true, true, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.COLOR, true, true, false, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.COLOR, true, true, false, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.COLOR, true, false, true, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.SHAPE, false, false, true, true, false, false, true, true));
		Manager.getInstance().getMovements().add(new Movement(Strategy.SHAPE, false, true, true, true, false, false, true, true));
		Manager.getInstance().getMovements().add(new Movement(Strategy.SHAPE, true, true, true, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.SHAPE, true, true, true, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.COLOR, false, true, true, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.COLOR, true, true, false, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.COLOR, true, true, false, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.COLOR, true, false, true, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.SHAPE, false, false, true, true, false, false, true, true));
		Manager.getInstance().getMovements().add(new Movement(Strategy.SHAPE, false, true, true, true, false, false, true, true));
		Manager.getInstance().getMovements().add(new Movement(Strategy.SHAPE, true, true, true, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.SHAPE, true, true, true, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.COLOR, false, true, true, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.COLOR, true, true, false, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.COLOR, true, true, false, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.COLOR, true, false, true, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.SHAPE, false, false, true, true, false, false, true, true));
		Manager.getInstance().getMovements().add(new Movement(Strategy.SHAPE, false, true, true, true, false, false, true, true));
		Manager.getInstance().getMovements().add(new Movement(Strategy.SHAPE, true, true, true, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.SHAPE, true, true, true, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.COLOR, false, true, true, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.COLOR, true, true, false, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.COLOR, true, true, false, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.COLOR, true, false, true, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.SHAPE, false, false, true, true, false, false, true, true));
		Manager.getInstance().getMovements().add(new Movement(Strategy.SHAPE, false, true, true, true, false, false, true, true));
		Manager.getInstance().getMovements().add(new Movement(Strategy.SHAPE, true, true, true, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.SHAPE, true, true, true, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.COLOR, false, true, true, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.COLOR, true, true, false, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.COLOR, true, true, false, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.COLOR, true, false, true, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.SHAPE, false, false, true, true, false, false, true, true));
		Manager.getInstance().getMovements().add(new Movement(Strategy.SHAPE, false, true, true, true, false, false, true, true));
		Manager.getInstance().getMovements().add(new Movement(Strategy.SHAPE, true, true, true, true, false, false, true, false));
		Manager.getInstance().getMovements().add(new Movement(Strategy.SHAPE, true, true, true, true, false, false, true, false));
		
		//Finishing test
		Manager.getInstance().setFinalTime(Calendar.getInstance());
		String fileName = Constants.PDF_FILE_NAME_PREFIX + " - " + Manager.getInstance().getUserName() + ".pdf";
        String filePath = Constants.PDF_FILE_PATH + fileName;
        PDFGenerator mPDFGenerator = new PDFGenerator(filePath);
        mPDFGenerator.generateAndShowPDFFile();
		System.exit(0);
	}
}