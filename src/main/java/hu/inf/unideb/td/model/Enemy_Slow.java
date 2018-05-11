package hu.inf.unideb.td.model;

import org.joml.Vector3f;

public class Enemy_Slow extends Enemy{
    public Enemy_Slow()
    {
        super.setSpeed(2);
        entities.add(new Entity("metal_waypoint","bunny"));
        entities.get(1).setLocalRotation(new Vector3f(0,180,0));
    }
}
