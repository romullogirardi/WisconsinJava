package com.romullogirardi.wisconsin.view;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.romullogirardi.wisconsin.model.Manager;

public class LoginFrame {

	//WINDOW REFERENCE
	private JFrame frame;

	//LAUNCHING THE APPLICATION
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame window = new LoginFrame();
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
		//Initializing the login panel
		final JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//Adding the login label
		loginPanel.add(new JLabel("Insira o nome do paciente: "));
		
		//Adding the login textField
		final JTextField userNameTextField = new JTextField();
		userNameTextField.setColumns(35);
		loginPanel.add(userNameTextField);
		
		//Adding the login button
		JButton sendButton = new JButton("Enviar");
		loginPanel.add(sendButton);
		sendButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String userName = userNameTextField.getText();
				if(!userName.isEmpty()) {
					
					//Setting user name and initial time
					Manager.getInstance().setUserName(userName);
					Manager.getInstance().setInitialTime(Calendar.getInstance());
					
					//Calling the wisconsin window
					WisconsinFrame wisconsinFrame = new WisconsinFrame();
					wisconsinFrame.getFrame().setVisible(true);
					
					//Disposing the login window
					frame.dispose();
				}
				else {
					JOptionPane.showMessageDialog(loginPanel, "Preencha o nome do paciente");
				}
			}
		});
		
		//Adding login panel to the window
		frame.setContentPane(loginPanel);
	}
}