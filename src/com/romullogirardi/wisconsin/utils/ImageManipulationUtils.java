package com.romullogirardi.wisconsin.utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class ImageManipulationUtils {

	//Implementing as a Singleton
	private static ImageManipulationUtils _instance = null;
	
	private ImageManipulationUtils(){
		
	}
	
	public static ImageManipulationUtils getInstance(){
		if (_instance == null)
			_instance = new ImageManipulationUtils();
		return _instance;
		
	}
	
	//Image manipulation methods
	public ImageIcon mergeImages(ImageIcon[] icons){
		ImageIcon mergedImageIcon = null;
		
		if (icons != null){
			int[] dimension = getLargestDimensions(icons);

			Image mergedImage = new BufferedImage(dimension[0], dimension[1], BufferedImage.TYPE_INT_ARGB);
			Graphics2D graphics = (Graphics2D) mergedImage.getGraphics();

			for (int i = 0; i < icons.length; i++){
				graphics.drawImage(icons[i].getImage(), 0, 0, null);
			}

			mergedImageIcon = new ImageIcon(mergedImage);

			graphics.dispose();
		}
		
		return mergedImageIcon;
	}
	
	private int[] getLargestDimensions(ImageIcon[] icons){
		 int[] dimensions = new int[2];
		
		 dimensions[0] = 0;
		 dimensions[1] = 0; 
		 
		 for (int i = 0; i < icons.length; i++){
			 if (icons[i].getIconWidth() > dimensions[0])
				 dimensions[0] = icons[i].getIconWidth();
			 if (icons[i].getIconHeight() > dimensions[1])
				 dimensions[1] = icons[i].getIconHeight();
		 }
		 
		 return dimensions;
	}
	
	public ImageIcon getScaledImage(ImageIcon srcImageIcon, int weigth, int heigth){
	    Image image = srcImageIcon.getImage();
	    Image scaledImage = image.getScaledInstance(weigth, heigth, Image.SCALE_SMOOTH);
	    return new ImageIcon(scaledImage);
	}
}