package com.pungdjurProductions.physics;



/**
 * Created by Rasmus on 2015-06-03.
 */
public class AABB {
    private Vector2 min;
    private Vector2 max;

    AABB(Vector2 a, Vector2 b){
        min = new Vector2(Math.min(a.x,b.x), Math.min(a.y,b.y));
        max = new Vector2(Math.max(a.x, b.x), Math.max(a.y, b.y));
    }

    public void setAABB(Vector2 a, Vector2 b){
        min = new Vector2(Math.min(a.x,b.x), Math.min(a.y,b.y));
        max = new Vector2(Math.max(a.x, b.x), Math.max(a.y, b.y));
    }

    public Vector2 getMin() {
        return min;
    }

    public Vector2 getMax(){
        return max;
    }

    public void setMax(Vector2 max) {
        this.max = max;
    }

    public void setMin(Vector2 min) {
        this.min = min;
    }
}
