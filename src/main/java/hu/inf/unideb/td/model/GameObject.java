package hu.inf.unideb.td.model;

import hu.inf.unideb.td.model.managers.GameObjectManager;
import hu.inf.unideb.td.view.Renderer;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public abstract class GameObject {
    public Vector3f position = new Vector3f(0, 0, 0), rotation = new Vector3f(0, 0, 0);
    public float scale=1;
    public List<Entity> entities = new ArrayList<Entity>();
    public List<Light> lights = new ArrayList<Light>();

    public GameObject() {
    }

    public GameObject(Vector3f position, Vector3f rotation, float scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public GameObject(Vector3f position, Vector3f rotation, float scale, List<Entity> entities, List<Light> lights) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.entities = entities;
        this.lights = lights;
    }

    public abstract void update();

    public void display(Renderer renderer)
    {

        for(Entity entity:entities)
        {
            entity.setPosition(new Vector3f(this.position).add(entity.getLocalPosition()));
          //  entity.setRotation(new Vector3f(this.rotation).add(entity.getLocalRotation()));
            renderer.render(entity);
        }
    }
    public void increasePosition(float dx, float dy, float dz) {
        this.position.x += dx;
        this.position.y += dy;
        this.position.z += dz;
    }

    public void increasePotation(float dx, float dy, float dz) {
        this.rotation.x += dx;
        this.rotation.y += dy;
        this.rotation.z += dz;
    }

    public void destroy()
    {
        GameObjectManager.addToRemoved(this);
    }
}
