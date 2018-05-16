package hu.inf.unideb.td.model.gameObjects.enemies;

import hu.inf.unideb.td.model.Entity;
import hu.inf.unideb.td.model.gameObjects.Enemy;
import hu.inf.unideb.td.model.gameObjects.Tower;
import org.joml.Vector3f;


/**
 * Ez az osztály az Enemy osztályból származik.
 * Egy ellenféltipust definiál.
 * @see Enemy
 */
public class Enemy_Slow extends Enemy {
    /**
     * Az enemy slow konstruktora.
     */
    public Enemy_Slow()
    {
        super.setSpeed(1);
        entities.add(new Entity("metal_waypoint","bunny"));
        entities.get(1).setLocalRotation(new Vector3f(0,180,0));
    }
}
