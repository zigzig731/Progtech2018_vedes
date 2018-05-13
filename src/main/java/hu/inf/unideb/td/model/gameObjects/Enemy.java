package hu.inf.unideb.td.model.gameObjects;

import hu.inf.unideb.td.model.*;
import hu.inf.unideb.td.model.mapElements.Path;
import hu.inf.unideb.td.model.player.Camera;
import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 * This class is inherited from the GameObject class.
 * Implements basic behaviour that is represented in every enemy.
 * @see hu.inf.unideb.td.model.GameObject
 */
public class Enemy extends GameObject {
    /**
     * This is the movement speed of the enemy.
     */
    private float speed = 3f;
    /**
     * The fitness of an enemy represents how far the enemy has made it on the path towards the end of the map.
     */
    private float fittness;
    /**
     * The enemies follow waypoints on the Path.
     * This variable holds the currently followed waypoint.
     * By default this is set to the first waypoint along the path.
     */
    private Vector3f target = Path.getWaypoint(0);
    private int targetIndex = 0;
    private float healt = 100;
    private float maxHealt = 100;
    private MaterialInstance healthBarMat;


    /**
     * Ez a metódus visszaadja egy enemy életét.
     * @return Egy enemy életereje.
     */
    public float getHealt() {
        return healt;
    }

    public float getMaxHealt() {
        return maxHealt;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getFittness() {
        return fittness;
    }

    public void setFittness(float fittness) {
        this.fittness = fittness;
    }

    public Enemy() {
        healthBarMat = new MaterialInstance(new Vector3f(0, 1, 0));
        entities.add(new Entity(healthBarMat, "healthbar"));
        entities.get(0).setLocalPosition(new Vector3f(0, 3, 0));
        position = new Vector3f(6, 0, 4);
        entities.get(0).setScale(0.2f);
    }

    public Enemy(boolean test) {
        healthBarMat = new MaterialInstance(true);
        entities.add(new Entity(true));
        entities.get(0).setLocalPosition(new Vector3f(0, 3, 0));
        position = new Vector3f(6, 0, 4);
        entities.get(0).setScale(0.2f);
        entities.add(new Entity(true));
        entities.get(1).setLocalRotation(new Vector3f(0, 180, 0));
    }


    /**
     * A Path ról kéri le a waypointokat.
     * @see Path
     */
    private void move() {
        Vector3f dir = new Vector3f(target);
        dir.sub(position);
        if (dir.length() < 0.4) {
            targetIndex++;
            if (targetIndex < Path.waypoints.size()) target = Path.getWaypoint(targetIndex);
            else destroy();
        }
        entities.get(1).setRotation(new Vector3f(0, 180 + (float) Math.toDegrees(new Vector2f(dir.x, dir.z).normalize().angle(new Vector2f(1, 0).normalize())), 0));
        dir.normalize(0.1f);
        dir.mul(speed);
        fittness += dir.length();
        position.add(dir);

    }

    private void rotateHealthBar() {
        Vector3f lookAt = new Vector3f(position);

        lookAt.sub(Camera.position);
        float roty = (float) Math.toDegrees(new Vector2f(lookAt.x, lookAt.z).normalize().angle(new Vector2f(1, 0).normalize()));
        entities.get(0).setRotation(new Vector3f(0, roty, 45));
        healthBarMat.setBaseColor(new Vector3f(1 - (float) healt / (float) maxHealt, (float) healt / (float) maxHealt, 0));
    }

    /**
     * Az adott enemy életét csökkenti a bejövő sebzés értékével.
      * @param damage A bvejövő sebzés értéke.
     */
    public void dealDamage(float damage) {
        healt -= damage;
    }

    @Override
    public void update() {
        if (healt <= 0) destroy();
        move();
        rotateHealthBar();
    }

}
