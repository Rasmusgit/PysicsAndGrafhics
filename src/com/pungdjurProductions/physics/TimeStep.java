package com.pungdjurProductions.physics;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Observable;

import edu.chl.Game.model.gameobject.entity.player.GameCursor.CursorState;
import edu.chl.Game.view.CharacterSelectionView;
import edu.chl.Game.view.Frame;
import edu.chl.Game.view.FrameGDX;
import edu.chl.Game.view.SubMenuView;
import edu.chl.Game.view.WorldMapView;
import edu.chl.Game.model.sound.*;
import edu.chl.Game.view.graphics.WorldMapAnimator;

/**
 * 
 * @author Mansoor, Alexander
 * @version 1.0
 */
public class TimeStep extends Observable implements Runnable{
	
	//Private Variables
	private Thread thread;

	
	private int frameRate=1;
	private double delta = 0.0;
	private boolean running = false;
	private Boolean inMainMenu = false;
	
	// Swing frame
	private Frame frame;
	

	
	public TimeStep(){

		thread = new Thread(this);
		frame = new Frame();
		
		
		
		start();
	}

	/**
	 * Starts the thread
	 */
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread.start();
	}
	
	/**
	 * When the thread is interrupted
	 * @throws InerrutpedException
	 */
	public synchronized void interrupt(){
		if(running){
			running = false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run() {
		frame.requestFocus();
		timer();
	}
	
	/**
	 * Update Timer, create a Buffer and update the handler.
	 */
	public void update(){
			setChanged();
			render();
			printTimer();
	}

	/**
	 * Create a Buffer with maximum number of 3 and start rendering.
	 */
	public void render(){

			BufferStrategy bs = frame.getBufferStrategy();
			if(bs == null){
				frame.createBufferStrategy(3);
                return;
			}
			renderGraphics(bs);
	
		frame.setVisible(true);
	}
	
	/**
	 * Rendering the Graphics to the screen.
	 * @param b - BufferStrategy to access the methods in BufferStrategy class.
	 */
	public void renderGraphics(BufferStrategy b) {
		Graphics g = b.getDrawGraphics();
		frame.requestFocus();
		
		
			gameHandler.render(g);
			SnotifyObservers((Object)g);
	

		
		b.show();
		g.dispose();

	}
	
	//Counter / Timer
	private void timer(){
		long timeSnap1 = System.nanoTime();
		double nanosec = 1000000000.0;
		long timeSnap2 = System.nanoTime();
		delta = (timeSnap2 - timeSnap1)*60/nanosec;
		while(running){
			timeSnap1 = timeSnap2;
			timeSnap2 = System.nanoTime();
			delta += (timeSnap2 - timeSnap1 )*60/nanosec;
			timeToUpdate(delta);
		}
		interrupt();	
	}
	
	
	//When the time is there, it's goes to the next stage of the updating
	private void timeToUpdate(double d){
		if(d>1){
			delta=delta-1;
			update();
		}
	}
	
	
	//Printing frames per second. 
	private void printTimer(){
		frameRate++;
		if(isFrame()){
			frameRate=1;
		}
	}
	

	//Checking if Frame is equal to 60.
	//If frame is not 60 then return false otherwise true.
	private boolean isFrame() {
		return frameRate==60;
	}
        

}
