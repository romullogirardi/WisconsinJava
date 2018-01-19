package com.romullogirardi.wisconsin.utils;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class ProgressDialog {

	public static void showProgressDialog(JFrame parentFrame, Image icon, String title, String message, int milliseconds) {

		final JDialog dlg = new JDialog(parentFrame, title, true);
		dlg.setIconImage(icon);
	    JProgressBar dpb = new JProgressBar(0, milliseconds);
	    dlg.add(BorderLayout.CENTER, dpb);
	    dlg.add(BorderLayout.NORTH, new JLabel(message));
	    dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	    dlg.setSize(280, 75);
	    dlg.setLocationRelativeTo(parentFrame);
	    Thread t = new Thread(new Runnable() {
	      public void run() {
	        dlg.setVisible(true);
	      }
	    });
	    t.start();
	    for (int i = 0; i <= milliseconds; i++) {
	      dpb.setValue(i);
	      if(dpb.getValue() == milliseconds){
	        dlg.setVisible(false);
	        System.exit(0);
	      }
	      try {
	        Thread.sleep(1);
	      } catch (InterruptedException e) {
	        e.printStackTrace();
	      }
	    }
	    dlg.setVisible(true);
	}
}