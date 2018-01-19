package com.romullogirardi.wisconsin.view;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
		ImageIcon iconImageIcon = new ImageIcon(Card.class.getResource(Constants.IMAGES_FOLDER + "/" + Constants.ICON_IMAGE_FILE_NAME));
		frame.setIconImage(iconImageIcon.getImage());

		
		//Initializing the wisconsinPanel
		final ImageIcon backgroundImageIcon = new ImageIcon(Card.class.getResource(Constants.IMAGES_FOLDER + "/" + Constants.BACKGROUND_IMAGE_FILE_NAME));
		@SuppressWarnings("serial")
		JPanel wisconsinPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImageIcon.getImage(), 0, 0, null);
            }
        };
		wisconsinPanel.setLayout(null);
		
		//Adding reference cards to wisconsinPanel
		referenceCard1 = new JLabel("");
		referenceCard1.setBounds((int) ((Constants.DISPLAY_WIDTH - (4 * Constants.CARD_WIDTH)) / 5), 
				(int) ((Constants.DISPLAY_HEIGHT - (3 * Constants.CARD_WIDTH)) / 4) - Constants.delta_y, 
				Constants.CARD_WIDTH, Constants.CARD_HEIGHT);
		wisconsinPanel.add(referenceCard1);
		referenceCard2 = new JLabel("");
		referenceCard2.setBounds((int) (2 * ((Constants.DISPLAY_WIDTH - (4 * Constants.CARD_WIDTH)) / 5) + Constants.CARD_WIDTH), 
				(int) ((Constants.DISPLAY_HEIGHT - (3 * Constants.CARD_WIDTH)) / 4) - Constants.delta_y, 
				Constants.CARD_WIDTH, Constants.CARD_HEIGHT);
		wisconsinPanel.add(referenceCard2);
		referenceCard3 = new JLabel("");
		referenceCard3.setBounds((int) (3 * ((Constants.DISPLAY_WIDTH - (4 * Constants.CARD_WIDTH)) / 5) + 2 * Constants.CARD_WIDTH), 
				(int) ((Constants.DISPLAY_HEIGHT - (3 * Constants.CARD_WIDTH)) / 4) - Constants.delta_y, 
				Constants.CARD_WIDTH, Constants.CARD_HEIGHT);
		wisconsinPanel.add(referenceCard3);
		referenceCard4 = new JLabel("");
		referenceCard4.setBounds((int) (4 * ((Constants.DISPLAY_WIDTH - (4 * Constants.CARD_WIDTH)) / 5) + 3 * Constants.CARD_WIDTH), 
				(int) ((Constants.DISPLAY_HEIGHT - (3 * Constants.CARD_WIDTH)) / 4) - Constants.delta_y, 
				Constants.CARD_WIDTH, Constants.CARD_HEIGHT);
		wisconsinPanel.add(referenceCard4);

		//Adding cards positions to wisconsinPanel
		lastCardInPosition1 = new JLabel("");
		lastCardInPosition1.setBounds((int) ((Constants.DISPLAY_WIDTH - (4 * Constants.CARD_WIDTH)) / 5), 
				(int) (2 * ((Constants.DISPLAY_HEIGHT - (3 * Constants.CARD_WIDTH)) / 4) + Constants.CARD_HEIGHT) - Constants.delta_y, 
				Constants.CARD_WIDTH, Constants.CARD_HEIGHT);
		wisconsinPanel.add(lastCardInPosition1);
		lastCardInPosition2 = new JLabel("");
		lastCardInPosition2.setBounds((int) (2 * ((Constants.DISPLAY_WIDTH - (4 * Constants.CARD_WIDTH)) / 5) + Constants.CARD_WIDTH), 
				(int) (2 * ((Constants.DISPLAY_HEIGHT - (3 * Constants.CARD_WIDTH)) / 4) + Constants.CARD_HEIGHT) - Constants.delta_y, 
				Constants.CARD_WIDTH, Constants.CARD_HEIGHT);
		wisconsinPanel.add(lastCardInPosition2);
		lastCardInPosition3 = new JLabel("");
		lastCardInPosition3.setBounds((int) (3 * ((Constants.DISPLAY_WIDTH - (4 * Constants.CARD_WIDTH)) / 5) + 2 * Constants.CARD_WIDTH), 
				(int) (2 * ((Constants.DISPLAY_HEIGHT - (3 * Constants.CARD_WIDTH)) / 4) + Constants.CARD_HEIGHT) - Constants.delta_y, 
				Constants.CARD_WIDTH, Constants.CARD_HEIGHT);
		wisconsinPanel.add(lastCardInPosition3);
		lastCardInPosition4 = new JLabel("");
		lastCardInPosition4.setBounds((int) (4 * ((Constants.DISPLAY_WIDTH - (4 * Constants.CARD_WIDTH)) / 5) + 3 * Constants.CARD_WIDTH), 
				(int) (2 * ((Constants.DISPLAY_HEIGHT - (3 * Constants.CARD_WIDTH)) / 4) + Constants.CARD_HEIGHT) - Constants.delta_y, 
				Constants.CARD_WIDTH, Constants.CARD_HEIGHT);
		wisconsinPanel.add(lastCardInPosition4);

		//Adding cards to be played to wisconsinPanel
		cardToBePlayed = new JLabel("");
		cardToBePlayed.setBounds((int) ((Constants.DISPLAY_WIDTH - Constants.CARD_WIDTH) / 2), 
				(int) (3 * ((Constants.DISPLAY_HEIGHT - (3 * Constants.CARD_WIDTH)) / 4) + 2 * Constants.CARD_HEIGHT) - Constants.delta_y, 
				Constants.CARD_WIDTH, Constants.CARD_HEIGHT);
		wisconsinPanel.add(cardToBePlayed);
		
		//Updating panel
		updateWisconsinPanel();
		
		//Adding mouseListeners to cards
		lastCardInPosition1.addMouseListener(this);
		lastCardInPosition2.addMouseListener(this);
		lastCardInPosition3.addMouseListener(this);
		lastCardInPosition4.addMouseListener(this);
		
		//Adding wisconsinPanel to the window
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
		if(previousMovement.isSuccess()) 
			playSound(Constants.RIGHT_SOUND_FILE_NAME);
		else 
			playSound(Constants.WRONG_SOUND_FILE_NAME);
		
		//Checking if game is finished
		if(Manager.getInstance().isGameFinished())
			onTestFinish();
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
		
        String fileName = Constants.PDF_FILE_NAME_PREFIX + Manager.getInstance().getUserName() + ".pdf";
        String filePath = Constants.PDF_FILE_PATH + "\\" + fileName;
        PDFGenerator mPDFGenerator = new PDFGenerator(filePath);
        mPDFGenerator.generateAndShowPDFFile();
		System.exit(0);
	}
	
	//METHOD TO PLAY A SOUND
	public static synchronized void playSound(final String soundFileName) {
		  
		new Thread(new Runnable() {
		    public void run() {
		      try {
		        Clip clip = AudioSystem.getClip();
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
//		        WisconsinFrame.class.getResourceAsStream(Constants.SOUNDS_FOLDER + "/" + soundFileName));
		        WisconsinFrame.class.getResource(Constants.SOUNDS_FOLDER + "/" + soundFileName));
			        clip.open(inputStream);
			        clip.start(); 
		      } catch (Exception e) {
		        System.err.println(e.getMessage());
		      }
		    }
		}).start();
	}
	
	//METHOD TO FINISH TEST
	private void onTestFinish() {

		Manager.getInstance().setFinalTime(Calendar.getInstance());
		Manager.getInstance().setMovementsPerseverativity();
		
		lastCardInPosition1.addMouseListener(null);
		lastCardInPosition2.addMouseListener(null);
		lastCardInPosition3.addMouseListener(null);
		lastCardInPosition4.addMouseListener(null);
		
		playSound(Constants.FINISHED_TEST_SOUND_FILE_NAME);
		showPDFReport();

//		ImageIcon iconImageIcon = new ImageIcon(Card.class.getResource(Constants.IMAGES_FOLDER + "/" + Constants.ICON_IMAGE_FILE_NAME));
//		ProgressDialog.showProgressDialog(null, iconImageIcon.getImage(), 
//				"", "Teste finalizado. Gerando o relatório do teste...", 5000);
	}
}