package com.pungdjurProductions.physics;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

import javax.swing.JFrame;



/**
 * Created by Rasmus on 2015-06-04.
 */
public class TimeStep2 {
    final float fps = 100;
    final float dt = 1 / fps;
    float accumulator = 0;
    LinkedList<Object> olist;
    JFrame frame;

    public TimeStep2(){
        frame = new JFrame();
        init();
       
    }
    
    public void init(){
    	olist = new LinkedList<Object>();
    	
    	olist.add(new Rect(50, 50, 30, 30, 1, 1));
    	
    	
    }
    
    public void renderGraphics(BufferStrategy b) {
		Graphics g = b.getDrawGraphics();
		frame.requestFocus();
		
		for(Object o : olist){
    		o.render(g);
    	}

		
		b.show();
		g.dispose();

	}
    
    private void update(double dt){
    	for(Object o : olist){
    		o.update(dt);
    	}
    }


    public void start() {
    	
    	
        // In units seconds
        float frameStart = System.currentTimeMillis();

        // main loop
        while (true) {
            float currentTime = System.currentTimeMillis();

            // Store the time elapsed since the last frame began
            accumulator += currentTime - frameStart;

            // Record the starting of this frame
            frameStart = currentTime;

            // Avoid spiral of death and clamp dt, thus clamping
            // how many times the UpdatePhysics can be called in
            // a single game loop.
            if (accumulator > 0.2f) {
                accumulator = 0.2f;
            }

            while (accumulator > dt) {
                //UpdatePhysics(dt);
                update(dt);
                accumulator -= dt;
                
            }

            BufferStrategy bs = frame.getBufferStrategy();
			if(bs == null){
				frame.createBufferStrategy(3);
                return;
			}
			renderGraphics(bs);
        }
    }
    
}
