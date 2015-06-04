package com.pungdjurProductions.physics;

import java.awt.*;

/**
 * Created by Rasmus on 2015-06-03.
 */
public abstract class Object {

    private Vector2 position;
    private double height,width;

    private Vector2 velocity;
    private double restitution;
    private double mass;
    private double invmass;
    private AABB aabb;

    public Object(double x, double y,double width, double height, double velx, double vely, double mass, double restitution){
        position = new Vector2(x,y);
        velocity = new Vector2(velx,vely);

        this.width = width;
        this.height = height;

        this.mass = mass;
        this.invmass = 1/mass;
        this.restitution = restitution;

        aabb = new AABB(position, position.addVector(width,height));
    }

    public  void render(Graphics g){
        g.drawRect((int)(position.x - width/2), (int)(position.y -height/2), (int)width, (int)height);

    }

    public void update(double dt){
        position.x += velocity.x * dt;
        position.y += velocity.y * dt;
        System.out.println("haha");

        //better solution later

        if(position.x < 0){
            position.x = 0;
            velocity.x = -velocity.x;
        }else if(position.x + width > GameEngine.getWIDTH()){
            position.x = GameEngine.getWIDTH() - width;
            velocity.x = -velocity.x;
        }else if(position.y < 0){
            position.y = 0;
            velocity.y = -velocity.y;
        }else if(position.y + height > GameEngine.getHEIGHT()){
            position.y = GameEngine.getHEIGHT() - height;
            velocity.y = -velocity.y;
        }

        aabb.getMin().reset(position.x,position.y);
        aabb.getMax().reset(position.x +width, position.y +height);

    }

    public void setMass(double newMass){
        this.mass = newMass;
        if(newMass <=0){
            this.invmass = 0;
        }else{
            this.invmass = 1/newMass;
        }

    }


    public Vector2 getVelocity() {
        return velocity;
    }

    public double getRestitution() {
        return restitution;
    }

    public double getMass() {
        return mass;
    }

    public double getinvMass() {
        return invmass;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 v) {
        this.position = v;
    }

    public AABB getAabb() {
        return aabb;
    }

    public Vector2 getMax(){
        return aabb.getMax();
    }

    public  Vector2 getMin(){
        return  aabb.getMin();
    }
}
