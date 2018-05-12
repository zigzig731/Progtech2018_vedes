package hu.inf.unideb.td.model;

import hu.inf.unideb.td.model.managers.MaterialManager;
import hu.inf.unideb.td.model.managers.ModelManager;
import org.joml.Vector3f;

public class Entity {
    private MaterialInstance material;
    private Model model;
    private Vector3f position=new Vector3f(0, 0, 0), rotation=new Vector3f(0, 0, 0);
    private Vector3f localPosition=new Vector3f(0, 0, 0), localRotation=new Vector3f(0, 0, 0);
    private float scale=1;

    public Vector3f getLocalPosition() {
        return localPosition;
    }

    public void setLocalPosition(Vector3f localPosition) {
        this.localPosition = localPosition;
    }

    public Vector3f getLocalRotation() {
        return localRotation;
    }

    public void setLocalRotation(Vector3f localRotation) {
        this.localRotation = localRotation;
    }

    public Entity(String material, String model, Vector3f position, Vector3f rotation, float scale) {
        this.material = MaterialManager.get(material);
        this.model = ModelManager.get(model);
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public Entity(String material, String model) {
        this.material = MaterialManager.get(material);
        this.model = ModelManager.get(model);
    }

    public Entity(MaterialInstance mat, String model) {
        this.material = mat;
        this.model = ModelManager.get(model);
    }

    public void increasePosition(float dx, float dy, float dz) {
        this.position.x += dx;
        this.position.y += dy;
        this.position.z += dz;
    }

    public void increaseRotation(float dx, float dy, float dz) {
        this.rotation.x += dx;
        this.rotation.y += dy;
        this.rotation.z += dz;
    }

    public void increaseLocalPosition(float dx, float dy, float dz) {
        this.position.x += dx;
        this.position.y += dy;
        this.position.z += dz;
    }

    public void increaseLocalRotation(float dx, float dy, float dz) {
        this.rotation.x += dx;
        this.rotation.y += dy;
        this.rotation.z += dz;
    }

    public MaterialInstance getMaterial() {
        return material;
    }

    public void setMaterial(MaterialInstance material) {
        this.material = material;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}
