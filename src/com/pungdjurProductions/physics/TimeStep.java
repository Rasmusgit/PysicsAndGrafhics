package com.pungdjurProductions.physics;

/**
 * Created by Rasmus on 2015-06-04.
 */
public class TimeStep {
    final float fps = 100;
    final float dt = 1 / fps;
    float accumulator = 0;
    MyFrame myFrame;

    public TimeStep(){
        myFrame = new MyFrame();
        start();
    }

    private void start() {
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
                myFrame.update(dt);
                accumulator -= dt;
                System.out.println(dt);
            }

            myFrame.render();
        }
    }
}
