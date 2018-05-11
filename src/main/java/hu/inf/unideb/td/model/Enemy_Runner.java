package hu.inf.unideb.td.model;

import org.joml.Vector3f;

public class Enemy_Runner extends Enemy {
    public Enemy_Runner()
    {
            super.setSpeed(3);
            entities.add(new Entity("metal_base","enemy"));
            entities.get(1).setLocalRotation(new Vector3f(0,180,0));
    }
}
