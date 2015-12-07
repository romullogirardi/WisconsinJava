package com.romullogirardi.wisconsin.view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.romullogirardi.wisconsin.model.Card;
import com.romullogirardi.wisconsin.model.Constants;
import com.romullogirardi.wisconsin.model.Manager;
import com.romullogirardi.wisconsin.model.Movement;
import com.romullogirardi.wisconsin.utils.PDFGenerator;

public class WisconsinFrame implements MouseListener {

	//UI ELEMENTS
	public JFrame frame;
	private JLabel referenceCard1;
	private JLabel referenceCard2;
	private JLabel referenceCard3;
	private JLabel referenceCard4;
	private JLabel lastCardInPosition1;
	private JLabel lastCardInPosition2;
	private JLabel lastCardInPosition3;
	private JLabel lastCardInPosition4;
	private JLabel cardToBePlayed;

	//CONSTRUCTOR
	public WisconsinFrame() {
		initialize();
	}
	
	//GETTER
	public JFrame getFrame() {
		return frame;
	}

	//GUI INITIALIZING
	private void initialize() {

		//Initializing the window
		frame = new JFrame();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Initializing the wisconsin panel
		JPanel wisconsinPanel = new JPanel();
		wisconsinPanel.setBackground(new Color(0, 128, 0));
		wisconsinPanel.setLayout(new BoxLayout(wisconsinPanel, BoxLayout.Y_AXIS));
		
		//Adding reference cards panel to wisconsin panel
		JPanel referenceCardsPanel = new JPanel();
		referenceCardsPanel.setLayout(new BoxLayout(referenceCardsPanel, BoxLayout.LINE_AXIS));
		wisconsinPanel.add(referenceCardsPanel, 0);
		//Adding reference cards to reference cards panel
		referenceCard1 = new JLabel("");
		referenceCardsPanel.add(referenceCard1);
		referenceCard2 = new JLabel("");
		referenceCardsPanel.add(referenceCard2);
		referenceCard3 = new JLabel("");
		referenceCardsPanel.add(referenceCard3);
		referenceCard4 = new JLabel("");
		referenceCardsPanel.add(referenceCard4);

		//Adding card positions panel to wisconsin panel
		JPanel cardPositionsPanel = new JPanel();
		cardPositionsPanel.setLayout(new BoxLayout(cardPositionsPanel, 0));
		wisconsinPanel.add(cardPositionsPanel, 1);
		//Adding reference cards to reference cards panel
		lastCardInPosition1 = new JLabel("");
		cardPositionsPanel.add(lastCardInPosition1);
		lastCardInPosition2 = new JLabel("");
		cardPositionsPanel.add(lastCardInPosition2);
		lastCardInPosition3 = new JLabel("");
		cardPositionsPanel.add(lastCardInPosition3);
		lastCardInPosition4 = new JLabel("");
		cardPositionsPanel.add(lastCardInPosition4);

		//Adding cards to be played panel to wisconsin panel
		JPanel cardsToBePlayedPanel = new JPanel();
		cardsToBePlayedPanel.setLayout(new BoxLayout(cardsToBePlayedPanel, 0));
		wisconsinPanel.add(cardsToBePlayedPanel, 2);
		//Adding reference cards to reference cards panel
		cardToBePlayed = new JLabel("");
		cardsToBePlayedPanel.add(cardToBePlayed);
		
		//Updating panel
		updateWisconsinPanel();
		
		//Adding mouseListeners to cards
		lastCardInPosition1.addMouseListener(this);
		lastCardInPosition2.addMouseListener(this);
		lastCardInPosition3.addMouseListener(this);
		lastCardInPosition4.addMouseListener(this);
		
		//Adding wisconsin panel to the window
		frame.setContentPane(wisconsinPanel);
	}
	
	//OVERRIDING MouseListener METHODS
	@Override
	public void mouseClicked(MouseEvent e) {
		
		//Choosing position to execute movement
		int position;
		JLabel sourceClicked = (JLabel) e.getSource();
		if(sourceClicked.equals(lastCardInPosition1)) {
			position = 1;
		}
		else if(sourceClicked.equals(lastCardInPosition2)) {
			position = 2;
		}
		else if(sourceClicked.equals(lastCardInPosition3)) {
			position = 3;
		}
		else if(sourceClicked.equals(lastCardInPosition4)) {
			position = 4;
		}
		else {
			position = 1;
		}
		Manager.getInstance().executeMovement(position);
		
		//Updating UI
		updateWisconsinPanel();;
		
		//Showing feedback about the movement
		Movement previousMovement = Manager.getInstance().getMovements().get(Manager.getInstance().getMovements().size() - 1);
		String feedback = new String();
		if(previousMovement.isSuccess()) {
			feedback = "CERTO";
		}
		else {
			feedback = "ERRADO";
		}
		JOptionPane.showMessageDialog(null, feedback);
		
		//Checking if game is finished
		if(Manager.getInstance().isGameFinished()) {
			Manager.getInstance().setFinalTime(Calendar.getInstance());
			showPDFReport();
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	
	//METHOD TO UPDATE WISCONSIN PANEL
	private void updateWisconsinPanel() {
		referenceCard1.setIcon(Manager.getInstance().getReferenceCard1().getDrawableId());
		referenceCard2.setIcon(Manager.getInstance().getReferenceCard2().getDrawableId());
		referenceCard3.setIcon(Manager.getInstance().getReferenceCard3().getDrawableId());
		referenceCard4.setIcon(Manager.getInstance().getReferenceCard4().getDrawableId());
		lastCardInPosition1.setIcon((Manager.getInstance().getLastCardInPosition1() != null) ?
				Manager.getInstance().getLastCardInPosition1().getDrawableId() : Card.getEmptyCardIcon());
		lastCardInPosition2.setIcon((Manager.getInstance().getLastCardInPosition2() != null) ?
				Manager.getInstance().getLastCardInPosition2().getDrawableId() : Card.getEmptyCardIcon());
		lastCardInPosition3.setIcon((Manager.getInstance().getLastCardInPosition3() != null) ?
				Manager.getInstance().getLastCardInPosition3().getDrawableId() : Card.getEmptyCardIcon());
		lastCardInPosition4.setIcon((Manager.getInstance().getLastCardInPosition4() != null) ?
				Manager.getInstance().getLastCardInPosition4().getDrawableId() : Card.getEmptyCardIcon());
		cardToBePlayed.setIcon((Manager.getInstance().getCardToBePlayed() != null) ?
				Manager.getInstance().getCardToBePlayed().getDrawableId() : Card.getEmptyCardIcon());
	}

	//METHOD TO SHOW PDF REPORT
	private void showPDFReport() {
		
        String fileName = Constants.PDF_FILE_NAME_PREFIX + " - " + Manager.getInstance().getUserName() + ".pdf";
        String filePath = Constants.PDF_FILE_PATH + fileName;
        PDFGenerator mPDFGenerator = new PDFGenerator(filePath);
        mPDFGenerator.generateAndShowPDFFile();
		System.exit(0);
	}
}