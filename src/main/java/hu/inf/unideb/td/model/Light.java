package hu.inf.unideb.td.model;

import org.joml.Vector3f;

public class Light {
    private Vector3f position;
    private Vector3f color;
    private float intensity;
    private Vector3f attenuation = new Vector3f(0, 0, 0);

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getAttenuation() {
        return attenuation;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getColor() {
        return new Vector3f(color).mul(intensity);
    }

    public float getIntensity() {
        return intensity;
    }

    public void setIntensity(float intensity) {
        this.intensity = intensity;
    }

    public void setColor(Vector3f color) {
        this.color = color;

    }

    public Light(Vector3f position, Vector3f color, float intensity, Vector3f attenuation) {
        this.position = position;
        this.color = color;
        this.intensity = intensity;
        this.attenuation = attenuation;
    }
}
