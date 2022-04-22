package view.panel;


import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class BackGroundPanel extends JPanel {
	private Image image = null;

	/**
	 * Create the panel.
	 */
	public BackGroundPanel(Image image) {
		this.image = image;
	}
	protected void paintComponent(Graphics g) {
		g.drawImage(image,0,0,this.getWidth(),this.getHeight(),this);
	}

}
