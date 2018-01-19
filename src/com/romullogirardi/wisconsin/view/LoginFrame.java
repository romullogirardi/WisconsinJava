package com.romullogirardi.wisconsin.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.romullogirardi.wisconsin.model.Card;
import com.romullogirardi.wisconsin.model.Constants;
import com.romullogirardi.wisconsin.model.Manager;

public class LoginFrame {

	//WINDOW REFERENCE
	private JFrame contentFrame;
	private JTextField ageTextField;

	//LAUNCHING THE APPLICATION
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame window = new LoginFrame();
					window.contentFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//CONSTRUCTOR
	public LoginFrame() {
		initialize();
	}

	//GUI INITIALIZING
	private void initialize() {
		
		//Initializing the window
		contentFrame = new JFrame();
		contentFrame.setSize(460, 295);
		final ImageIcon iconImageIcon = new ImageIcon(Card.class.getResource(Constants.IMAGES_FOLDER + "/" + Constants.ICON_IMAGE_FILE_NAME));
		contentFrame.setIconImage(iconImageIcon.getImage());
		contentFrame.setLocation((int) (Constants.DISPLAY_WIDTH / 2 - contentFrame.getSize().width / 2),
				(int) (Constants.DISPLAY_HEIGHT / 2 - contentFrame.getSize().height / 2));
		contentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Initializing the login panel
		final JPanel loginPanel = new JPanel();
		loginPanel.setLayout(null);
		loginPanel.setBackground(new Color(177, 177, 177));
		
		//Adding header panel
		final ImageIcon headerImageIcon = new ImageIcon(Card.class.getResource(Constants.IMAGES_FOLDER + "/" + Constants.HEADER_IMAGE_FILE_NAME));
		@SuppressWarnings("serial")
		JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(headerImageIcon.getImage(), 0, 0, null);
            }
        };
        headerPanel.setLayout(null);
        headerPanel.setLocation(0, 0);
        headerPanel.setSize(450, 150);
        loginPanel.add(headerPanel);

		//Adding name label
		JLabel nameLabel = new JLabel("NOME");
		nameLabel.setLocation(30, 175);
		nameLabel.setSize(39, 25);
		nameLabel.setForeground(Color.WHITE);
		loginPanel.add(nameLabel);

		//Adding nameTextField
		final JTextField nameTextField = new JTextField();
		nameTextField.setToolTipText("Insira o nome do paciente");
		nameTextField.setSize(332, 25);
		nameTextField.setLocation(72, 175);
		nameTextField.setColumns(35);
		nameTextField.setSelectionStart(10);
		nameTextField.setBorder(null);
		loginPanel.add(nameTextField);
		
		//Adding ageLabel
		JLabel ageLabel = new JLabel("IDADE");
		ageLabel.setBounds(30, 207, 39, 25);
		ageLabel.setForeground(Color.WHITE);
		loginPanel.add(ageLabel);
		
		//Adding ageTextField
		ageTextField = new JTextField();
		ageTextField.setToolTipText("Insira a idade do paciente");
//		ageTextField.setBounds(72, 208, 290, 25);
		ageTextField.setBounds(72, 208, 160, 25);
		ageTextField.setBorder(null);
		loginPanel.add(ageTextField);
		ageTextField.setColumns(10);
		
		//Adding startTestButton
		JButton startTestButton = new JButton("INICIAR TESTE");
		startTestButton.setToolTipText("Clique no botão para iniciar o teste");
//		startTestButton.setLocation(30, 244);
//		startTestButton.setSize(374, 25);
		startTestButton.setLocation(243, 208);
		startTestButton.setSize(161, 25);
		startTestButton.setBackground(new Color(3, 107, 204));
		startTestButton.setForeground(Color.WHITE);
		startTestButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String userName = nameTextField.getText();
				String userAge = ageTextField.getText();
				if(!userName.isEmpty() && !userAge.isEmpty()) {
					if(isParsableToInteger(userAge)) {
						//Setting user name data and initial time
						Manager.getInstance().setUserName(userName);
						Manager.getInstance().setUserAge(Integer.parseInt(userAge));
						Manager.getInstance().setInitialTime(Calendar.getInstance());
						
						//Calling the wisconsin window
						WisconsinFrame wisconsinFrame = new WisconsinFrame();
						wisconsinFrame.getFrame().setVisible(true);
						
						//Disposing the login window
						contentFrame.dispose();
					}
					else {
						JOptionPane.showMessageDialog(loginPanel, "O campo \"Idade\" deve ser preenchido com um número", "", 0);
					}
				}
				else {
					JOptionPane.showMessageDialog(loginPanel, "Preencha os dados do paciente", "", 0);
				}
			}
		});
		loginPanel.add(startTestButton);
		
		//Adding login panel to the window
		contentFrame.setContentPane(loginPanel);
	}
	
	public static boolean isParsableToInteger(String input){
	   
		boolean parsable = true;
	    try{
	        Integer.parseInt(input);
	    }catch(NumberFormatException e){
	        parsable = false;
	    }
	    return parsable;
	}
}