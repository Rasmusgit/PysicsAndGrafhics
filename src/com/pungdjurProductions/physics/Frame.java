package com.pungdjurProductions.physics;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * The main frame for the whole application.
 * @author Martin Tran
 *
 */
public class Frame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	//Public variables
	public static int scale = 0;
	public final static int WIDTH = 1000 + scale;
	public final static int HEIGHT = 600 + scale;
	public final static String title = "DumBit";
	
	/**
	 * Constructor for the Frame. Sets all the properties for the frame.
	 */
	public Frame(){
        super(title + " 2.1");
        
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
	}
}
