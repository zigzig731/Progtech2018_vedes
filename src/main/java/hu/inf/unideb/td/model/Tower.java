package hu.inf.unideb.td.model;

import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;
import static org.lwjgl.glfw.GLFW.*;

public class Tower extends GameObject {
    private float fireRate = 0.1f;
    private float damage = 5;
    private float lastShot;
    private Enemy target = null;
    public float range = 10f;

    public List<Enemy> targetsInRange = new ArrayList<Enemy>();

    public Tower() {
        entities.add(new Entity("metal_base", "towergun"));
        entities.add(new Entity("metal_base", "towerbase"));
        entities.add(new Entity( new MaterialInstance(new Vector3f(0, 1, 0)), "rangeindicator"));
        entities.get(2).setScale(range/10);
        entities.get(2).setLocalPosition(new Vector3f(0,1.5f,0));
        lights.add(new Light(position,new Vector3f(1f,0.7f,0f),0, new Vector3f(3f,0.5f,0f)));
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

    public void shoot()
    {
        target.dealDamage(damage);
        lastShot=(float)glfwGetTime();
        lights.get(0).setIntensity(10);
    }

    @Override
    public void update() {
        if (target != null) if(!isValidTarget()) target = null;
        if (target == null) {
            getTargetsInRange();
            if (targetsInRange.size() > 0) {
                target = choseTarget();
            }
        } else {
            updatePose();
            if((float)glfwGetTime()-lastShot>fireRate) shoot();

        }
        if((float)glfwGetTime()-lastShot>0.02) lights.get(0).setIntensity(0);
    }
}
