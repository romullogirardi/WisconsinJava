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
		Movement movement1 = new Movement(1, Strategy.COLOR, null, 0, false, false, true, false, false, false, false);
		Movement movement2 = new Movement(1, Strategy.COLOR, movement1, 1, true, true, false, false, false, false, false);
		Movement movement3 = new Movement(1, Strategy.COLOR, movement2, 2, true, true, false, false, false, false, false);
		Movement movement4 = new Movement(1, Strategy.COLOR, movement3, 3, true, true, false, false, false, false, false);
		Movement movement5 = new Movement(1, Strategy.COLOR, movement4, 4, true, true, false, false, false, false, false);
		Movement movement6 = new Movement(1, Strategy.COLOR, movement5, 0, false, false, true, false, false, false, true);
		Movement movement7 = new Movement(1, Strategy.COLOR, movement6, 1, true, true, false, false, false, false, false);
		Movement movement8 = new Movement(1, Strategy.COLOR, movement7, 2, true, true, false, false, false, false, false);
		Movement movement9 = new Movement(1, Strategy.COLOR, movement8, 3, true, true, false, false, false, false, false);
		Movement movement10 = new Movement(1, Strategy.COLOR, movement9, 4, true, true, false, false, false, false, false);
		Movement movement11 = new Movement(1, Strategy.COLOR, movement10, 5, true, true, false, false, false, false, false);
		Movement movement12 = new Movement(1, Strategy.COLOR, movement11, 6, true, true, false, false, false, false, false);
		Movement movement13 = new Movement(1, Strategy.COLOR, movement12, 7, true, true, false, false, false, false, false);
		Movement movement14 = new Movement(1, Strategy.COLOR, movement13, 8, true, true, false, false, false, false, false);
		Movement movement15 = new Movement(1, Strategy.COLOR, movement14, 9, true, true, false, false, false, false, false);
		Movement movement16 = new Movement(1, Strategy.COLOR, movement15, 10, true, true, false, false, false, false, false);
		Movement movement17 = new Movement(2, Strategy.SHAPE, movement16, 0, false, true, false, false, false, false, true);
		Movement movement18 = new Movement(2, Strategy.SHAPE, movement17, 0, false, false, false, true, false, false, false);
		Movement movement19 = new Movement(2, Strategy.SHAPE, movement18, 1, true, false, true, false, false, false, false);
		Movement movement20 = new Movement(2, Strategy.SHAPE, movement19, 2, true, false, true, false, false, false, false);
		Movement movement21 = new Movement(2, Strategy.SHAPE, movement20, 3, true, false, true, false, false, false, false);
		Movement movement22 = new Movement(2, Strategy.SHAPE, movement21, 4, true, false, true, false, false, false, false);
		Movement movement23 = new Movement(2, Strategy.SHAPE, movement22, 5, true, false, true, false, false, false, false);
		Movement movement24 = new Movement(2, Strategy.SHAPE, movement23, 6, true, false, true, false, false, false, false);
		Movement movement25 = new Movement(2, Strategy.SHAPE, movement24, 7, true, false, true, false, false, false, false);
		Movement movement26 = new Movement(2, Strategy.SHAPE, movement25, 8, true, false, true, false, false, false, false);
		Movement movement27 = new Movement(2, Strategy.SHAPE, movement26, 9, true, false, true, false, false, false, false);
		Movement movement28 = new Movement(2, Strategy.SHAPE, movement27, 10, true, false, true, false, false, false, false);
		Movement movement29 = new Movement(3, Strategy.NUMBER, movement28, 0, false, false, true, false, false, false, true);
		Movement movement30 = new Movement(3, Strategy.NUMBER, movement29, 0, false, true, false, false, false, false, false);
		Movement movement31 = new Movement(3, Strategy.NUMBER, movement30, 0, false, true, false, false, false, false, true);
		Movement movement32 = new Movement(3, Strategy.NUMBER, movement31, 0, false, true, false, false, false, false, true);
		Movement movement33 = new Movement(3, Strategy.NUMBER, movement32, 0, false, true, false, false, false, false, true);
		Movement movement34 = new Movement(3, Strategy.NUMBER, movement33, 0, false, true, false, false, false, false, true);
		Movement movement35 = new Movement(3, Strategy.NUMBER, movement34, 0, false, true, false, false, false, false, true);
		Movement movement36 = new Movement(3, Strategy.NUMBER, movement35, 0, false, true, false, false, false, false, true);
		Movement movement37 = new Movement(3, Strategy.NUMBER, movement36, 0, false, true, false, false, false, false, true);
		Movement movement38 = new Movement(3, Strategy.NUMBER, movement37, 0, false, true, false, false, false, false, true);
		Movement movement39 = new Movement(3, Strategy.NUMBER, movement38, 0, false, true, false, false, false, false, true);
		Movement movement40 = new Movement(3, Strategy.NUMBER, movement39, 0, false, true, false, false, false, false, true);
		Movement movement41 = new Movement(3, Strategy.NUMBER, movement40, 0, false, true, false, false, false, false, true);
		Movement movement42 = new Movement(3, Strategy.NUMBER, movement41, 0, false, true, false, false, false, false, true);
		Movement movement43 = new Movement(3, Strategy.NUMBER, movement42, 0, false, true, false, false, false, false, true);
		Movement movement44 = new Movement(3, Strategy.NUMBER, movement43, 0, false, true, false, false, false, false, true);
		Movement movement45 = new Movement(3, Strategy.NUMBER, movement44, 0, false, true, false, false, false, false, true);
		Movement movement46 = new Movement(3, Strategy.NUMBER, movement45, 0, false, true, false, false, false, false, true);
		Movement movement47 = new Movement(3, Strategy.NUMBER, movement46, 0, false, true, false, false, false, false, true);
		Movement movement48 = new Movement(3, Strategy.NUMBER, movement47, 0, false, true, false, false, false, false, true);
		Movement movement49 = new Movement(3, Strategy.NUMBER, movement48, 0, false, true, false, false, false, false, true);
		Movement movement50 = new Movement(3, Strategy.NUMBER, movement49, 0, false, true, false, false, false, false, true);
		Movement movement51 = new Movement(3, Strategy.NUMBER, movement50, 0, false, true, false, false, false, false, true);
		Movement movement52 = new Movement(3, Strategy.NUMBER, movement51, 0, false, true, false, false, false, false, true);
		Movement movement53 = new Movement(3, Strategy.NUMBER, movement52, 0, false, true, false, false, false, false, true);
		Movement movement54 = new Movement(3, Strategy.NUMBER, movement53, 0, false, true, false, false, false, false, true);
		Movement movement55 = new Movement(3, Strategy.NUMBER, movement54, 0, false, true, false, false, false, false, true);
		Movement movement56 = new Movement(3, Strategy.NUMBER, movement55, 0, false, true, false, false, false, false, true);
		Movement movement57 = new Movement(3, Strategy.NUMBER, movement56, 0, false, true, false, false, false, false, true);
		Movement movement58 = new Movement(3, Strategy.NUMBER, movement57, 0, false, true, false, false, false, false, true);
		Movement movement59 = new Movement(3, Strategy.NUMBER, movement58, 0, false, true, false, false, false, false, true);
		Movement movement60 = new Movement(3, Strategy.NUMBER, movement59, 0, false, true, false, false, false, false, true);
		Movement movement61 = new Movement(3, Strategy.NUMBER, movement60, 0, false, true, false, false, false, false, true);
		Movement movement62 = new Movement(3, Strategy.NUMBER, movement61, 0, false, true, false, false, false, false, true);
		Movement movement63 = new Movement(3, Strategy.NUMBER, movement62, 0, false, true, false, false, false, false, true);
		Movement movement64 = new Movement(3, Strategy.NUMBER, movement63, 0, false, true, false, false, false, false, true);
		Manager.getInstance().getMovements().add(movement1);
		Manager.getInstance().getMovements().add(movement2);
		Manager.getInstance().getMovements().add(movement3);
		Manager.getInstance().getMovements().add(movement4);
		Manager.getInstance().getMovements().add(movement5);
		Manager.getInstance().getMovements().add(movement6);
		Manager.getInstance().getMovements().add(movement7);
		Manager.getInstance().getMovements().add(movement8);
		Manager.getInstance().getMovements().add(movement9);
		Manager.getInstance().getMovements().add(movement10);
		Manager.getInstance().getMovements().add(movement11);
		Manager.getInstance().getMovements().add(movement12);
		Manager.getInstance().getMovements().add(movement13);
		Manager.getInstance().getMovements().add(movement14);
		Manager.getInstance().getMovements().add(movement15);
		Manager.getInstance().getMovements().add(movement16);
		Manager.getInstance().getMovements().add(movement17);
		Manager.getInstance().getMovements().add(movement18);
		Manager.getInstance().getMovements().add(movement19);
		Manager.getInstance().getMovements().add(movement20);
		Manager.getInstance().getMovements().add(movement21);
		Manager.getInstance().getMovements().add(movement22);
		Manager.getInstance().getMovements().add(movement23);
		Manager.getInstance().getMovements().add(movement24);
		Manager.getInstance().getMovements().add(movement25);
		Manager.getInstance().getMovements().add(movement26);
		Manager.getInstance().getMovements().add(movement27);
		Manager.getInstance().getMovements().add(movement28);
		Manager.getInstance().getMovements().add(movement29);
		Manager.getInstance().getMovements().add(movement30);
		Manager.getInstance().getMovements().add(movement31);
		Manager.getInstance().getMovements().add(movement32);
		Manager.getInstance().getMovements().add(movement33);
		Manager.getInstance().getMovements().add(movement34);
		Manager.getInstance().getMovements().add(movement35);
		Manager.getInstance().getMovements().add(movement36);
		Manager.getInstance().getMovements().add(movement37);
		Manager.getInstance().getMovements().add(movement38);
		Manager.getInstance().getMovements().add(movement39);
		Manager.getInstance().getMovements().add(movement40);
		Manager.getInstance().getMovements().add(movement41);
		Manager.getInstance().getMovements().add(movement42);
		Manager.getInstance().getMovements().add(movement43);
		Manager.getInstance().getMovements().add(movement44);
		Manager.getInstance().getMovements().add(movement45);
		Manager.getInstance().getMovements().add(movement46);
		Manager.getInstance().getMovements().add(movement47);
		Manager.getInstance().getMovements().add(movement48);
		Manager.getInstance().getMovements().add(movement49);
		Manager.getInstance().getMovements().add(movement50);
		Manager.getInstance().getMovements().add(movement51);
		Manager.getInstance().getMovements().add(movement52);
		Manager.getInstance().getMovements().add(movement53);
		Manager.getInstance().getMovements().add(movement54);
		Manager.getInstance().getMovements().add(movement55);
		Manager.getInstance().getMovements().add(movement56);
		Manager.getInstance().getMovements().add(movement57);
		Manager.getInstance().getMovements().add(movement58);
		Manager.getInstance().getMovements().add(movement59);
		Manager.getInstance().getMovements().add(movement60);
		Manager.getInstance().getMovements().add(movement61);
		Manager.getInstance().getMovements().add(movement62);
		Manager.getInstance().getMovements().add(movement63);
		Manager.getInstance().getMovements().add(movement64);
		Manager.getInstance().setCategorySequenceNumber(3);
		
		//Finishing test
		Manager.getInstance().setFinalTime(Calendar.getInstance());
		String fileName = Constants.PDF_FILE_NAME_PREFIX + " - " + Manager.getInstance().getUserName() + ".pdf";
        String filePath = Constants.PDF_FILE_PATH + fileName;
        PDFGenerator mPDFGenerator = new PDFGenerator(filePath);
        mPDFGenerator.generateAndShowPDFFile();
		System.exit(0);
	}
}