package hu.inf.unideb.td.model.gameObjects.enemies;

import hu.inf.unideb.td.model.Entity;
import hu.inf.unideb.td.model.gameObjects.Enemy;
import hu.inf.unideb.td.model.gameObjects.Tower;
import org.joml.Vector3f;

/**
 * Ez az osztály a Enemy osztályból származik.
 * Egy ellenféltipust definiál.
 * @see Enemy
 */
public class Enemy_Runner extends Enemy {
    /**
     * Az enemy runner konstruktora.
     */
    public Enemy_Runner()
    {
            super.setSpeed(2f);
            super.setDamage(20);
            entities.add(new Entity("metal_base","enemy"));
            entities.get(1).setLocalRotation(new Vector3f(0,180,0));
    }
}
