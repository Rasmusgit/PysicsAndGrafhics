package com.pungdjurProductions.physics;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Rasmus on 2015-06-04.
 */
public class MyFrame extends JFrame {
    Rect r;
    public MyFrame(){
        super("My Frame");

        //you can set the content pane of the frame
        //to your custom class.

        setContentPane(new DrawPane());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(400, 400);

        r = new Rect(20,20,50,50,-4,-8);

        setVisible(true);
    }

    public void render(){
        repaint();

    }

    public void update(double dt){
        r.update(dt);

    }

    //create a component that you can actually draw on.
    class DrawPane extends JPanel{
        public void paintComponent(Graphics g){
            //draw on g here e.g.



            r.render(g);
        }
    }

}
