package hu.inf.unideb.td.model.gameObjects;

import hu.inf.unideb.td.model.Entity;
import hu.inf.unideb.td.model.GameObject;
import hu.inf.unideb.td.model.Light;
import hu.inf.unideb.td.model.managers.GameObjectManager;
import hu.inf.unideb.td.model.MaterialInstance;
import hu.inf.unideb.td.model.managers.LightManager;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Ez az osztály a GameObject osztályból származik.
 * Általános viselkedést implementál, mely minden toronynak része.
 * @see hu.inf.unideb.td.model.GameObject
 */
public class Tower extends GameObject {

    /**
     * Ez egy torony túzgyorsasága.
     * Minden toronytipusnál más értéket vehet fel.
     */
    private float fireRate = 0.1f;
    /**
     * Ez egy torony sebzése.
     * Minden toronytipusnál más értéket fehet fel
     */
    private float damage = 5;
    /**
     * Ez a változó tárolja az időpontot amikor torony legutoljára lőtt.
     */
    private float lastShot;
    /**
     * Ez a változó tárolja, hogy a torony épp melyik ellenfelet célozta be.
     * @see Enemy
     */
    public Enemy target = null;
    public float range = 10f;

    public List<Enemy> targetsInRange = new ArrayList<Enemy>();

    /**
     * Ez a metódus visszaadja egy torony sebzésének értékét.
     * @return Egy torony sebzése.
     */
    public float getDamage() {
        return damage;
    }

    public Tower() {
        entities.add(new Entity("metal_base", "towergun"));
        entities.add(new Entity("metal_base", "towerbase"));
        entities.add(new Entity(new MaterialInstance(new Vector3f(0, 1, 0)), "rangeindicator"));
        entities.get(2).setScale(range / 10);
        entities.get(2).setLocalPosition(new Vector3f(0, 1.5f, 0));
        lights.add(new Light(position, new Vector3f(1f, 0.7f, 0f), 0, new Vector3f(3f, 0.5f, 0f)));
        LightManager.add(lights.get(0));
    }

    public Tower(boolean test) {
        entities.add(new Entity(true));
        entities.add(new Entity(true));
        entities.add(new Entity(true));
        entities.get(2).setLocalPosition(new Vector3f(0, 1.5f, 0));
        lights.add(new Light(position, new Vector3f(1f, 0.7f, 0f), 0, new Vector3f(3f, 0.5f, 0f)));
        LightManager.add(lights.get(0));
    }

    public void getTargetsInRange() {
        targetsInRange.clear();
        for (GameObject gameObject : GameObjectManager.gameObjects) {
            if (gameObject instanceof Enemy) {
                Vector3f dir = new Vector3f(gameObject.position);
                dir.sub(position);
                if (dir.length() < range) {
                    targetsInRange.add((Enemy) gameObject);
                }
            }
        }
    }

    public boolean isValidTarget() {
        Vector3f distance = new Vector3f(target.position);
        return (GameObjectManager.gameObjects.contains(target) && distance.sub(position).length() < range);
    }

    public Enemy choseTarget() {

        targetsInRange.sort((o1, o2) -> (Float.compare(o1.getFittness(), o2.getFittness())));
        return targetsInRange.get(targetsInRange.size() - 1);
    }

    public void updatePose() {
        Vector3f dir = new Vector3f(position);
        dir.sub(target.position);
        float roty = (float) Math.toDegrees(new Vector2f(dir.x, dir.z).normalize().angle(new Vector2f(1, 0).normalize()));
        entities.get(0).setRotation(new Vector3f(0, roty, 0));
    }

    public void shoot() {
        target.dealDamage(damage);
        lastShot = (float) glfwGetTime();
        lights.get(0).setIntensity(10);
    }

    @Override
    public void update() {
        if (target != null) if (!isValidTarget()) target = null;
        if (target == null) {
            getTargetsInRange();
            if (targetsInRange.size() > 0) {
                target = choseTarget();
            }
        } else {
            updatePose();
            if ((float) glfwGetTime() - lastShot > fireRate) shoot();

        }
        if ((float) glfwGetTime() - lastShot > 0.02) lights.get(0).setIntensity(0);
    }
}
