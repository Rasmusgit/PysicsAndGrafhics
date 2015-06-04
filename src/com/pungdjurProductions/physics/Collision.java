package com.pungdjurProductions.physics;

/**
 * Created by Rasmus on 2015-06-03.
 */




public class Collision {



    public static boolean AABBvsAABB(AABB one, AABB two) {

        if(one.getMax().x < two.getMin().x || one.getMin().x > two.getMax().x)
            return false;

        if(one.getMax().y < two.getMin().y || one.getMin().y > two.getMax().y)
            return false;

        return true;
    }

    public static Manfold overlapAABB(Object one, Object two){
       Vector2 normal = new Vector2();
        Manfold manfold = new Manfold();
        normal.reset(two.getPosition().x - one.getPosition().x, two.getPosition().y - one.getPosition().y);

        double oneE = (one.getMax().x - one.getMin().x) / 2;
        double twoE = (two.getMax().x - two.getMin().x) / 2;

        double xOverlap = oneE + twoE - Math.abs(normal.x);

        if(xOverlap > 0){
            oneE = (one.getMax().y - one.getMin().y) / 2;
            twoE = (two.getMax().y - two.getMin().y) / 2;

            double yOverlap = oneE + twoE - Math.abs(normal.y);

            if(yOverlap>0){
                if(xOverlap < yOverlap){
                    if(normal.x < 0){
                        manfold.normal = normal.reset(-1,0);
                    }else{
                        manfold.normal = normal.reset(1,0);
                    }

                    manfold.penetration = xOverlap;
                    return manfold;
                }else{
                    if(normal.y < 0){
                      manfold.normal = normal.reset(0,-1);
                    }else{
                        manfold.normal = normal.reset(0,1);
                    }
                    manfold.penetration = yOverlap;
                    return manfold;
                }
            }
        }

        return null;

    }



    public void ResolveCollision(Object one, Object two){


        Vector2 rv = new Vector2();
        Vector2 impulse = new Vector2();
        Manfold m = overlapAABB(one,two);
        Vector2 normal = m.normal;

        rv.reset(two.getVelocity().x - one.getVelocity().x, two.getVelocity().y - one.getVelocity().y);

        double velAlongNormal = Vector2.dotProduct(rv, normal);

        if(velAlongNormal > 0)
            return;

        double e = Math.min(one.getRestitution(), two.getRestitution());

        double j = -(1 + e) * velAlongNormal;
        j /= one.getinvMass() +  two.getinvMass();

        impulse.reset(normal.x*j, normal.y*j);

        one.getVelocity().x -= (one.getinvMass() * impulse.x);
        one.getVelocity().y -= (one.getinvMass() * impulse.y);

        two.getVelocity().x += (two.getinvMass() * impulse.x);
        two.getVelocity().y += (two.getinvMass() * impulse.y);

        double percent = 0.8;
        double slop = 0.01;
        Vector2 c = normal.scale(Math.max(m.penetration - slop, 0) / (one.getinvMass() + two.getinvMass()) * percent);

        one.setPosition(one.getPosition().subVector(c.scale(one.getinvMass())));
        two.setPosition(two.getPosition().addVector( c.scale(two.getinvMass())));

    }

}
