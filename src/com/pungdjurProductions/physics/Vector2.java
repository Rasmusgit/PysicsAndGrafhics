package com.pungdjurProductions.physics;

/**
 * Created by Rasmus on 2015-06-03.
 */
public class Vector2{
    public double x;
    public double y;

    public Vector2(){
        this.x = 0.0;
        this.y = 0.0;
    }

    public Vector2(double x, double y){
        this.x = x;
        this.y = y;
    }

    public boolean equals(Vector2 vec2){

        return(this.x == vec2.x && this.y == vec2.y);

    }


    public Vector2 normalize(){
        double len = getLength();
        double n = 1;
        if(len != 0.0){
            n = 1.0 / len;
        }

        return new Vector2(x*n, y*n);
    }

    public void setVector(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector2 addVector(Vector2 vector2){
        return  new Vector2(x + vector2.x, y +vector2.y);
    }

    public Vector2 addVector(double offx, double offy){
        return  new Vector2(x + offx, y + offy);
    }
    public Vector2 subVector(Vector2 vector2){
        return  new Vector2(x - vector2.x, y - vector2.y);
    }


    public Vector2 perpendicularVector(Vector2 vector2){
        return  new Vector2(-y, x);
    }

    public double getLength(){
        return  Math.sqrt(x*x + y*y);
    }

    public Vector2 scale(double value){

        return new Vector2(this.x * value, this.y *value);
    }

    public double getAngle(){
        return  Math.sqrt(x*x + y*y);
    }

    public void rotate(double rot){
        double len = getLength();
        this.x = Math.cos(rot) *len;
        this.y = Math.sin(rot) * len;
    }

    public static double dotProduct(Vector2 one, Vector2 two){
        return  one.x*two.x + one.y*two.y;
    }

    public static double crossProduct(Vector2 one, Vector2 two){
        return  one.x*two.y - one.y * two.x;
    }

    public static double distance(Vector2 first, Vector2 secound){
        double v0 = first.x -secound.x;
        double v1 = first.y - secound.y;

        return Math.sqrt(v0*v0 + v1*v1);
    }

    public static double angleBetween(Vector2 first, Vector2 secound){
         first = first.clone().normalize();
         secound = secound.clone().normalize();

        return Math.acos(dotProduct(first,secound));
    }

    public Vector2 reset(double x, double y){

        return new Vector2(x,y);
    }

    public Vector2 clone() {
        return new Vector2(this.x, this.y);
    }

}
