package hu.inf.unideb.td.model;

import org.joml.Vector3f;

/**
 * A fények reprezentálására való osztály.
 */
public class Light {
    /**
     * Egy fény poziciója.
     */
    private Vector3f position;
    /**
     * Egy fény szine.
     */
    private Vector3f color;
    /**
     * Egy fény intenzitása.
     */
    private float intensity;
    /**
     * Egy fény elsötétülési mintája.
     */
    private Vector3f attenuation = new Vector3f(0, 0, 0);

    /**
     * A fény pozicióját tudjuk vele lekérni.
     * @return A fény poziciója.
     */
    public Vector3f getPosition() {
        return position;
    }

    /**
     * A fény elsötétülési mintáját tudjuk vele lekérni.
     * @return A fény elsötétülési mintája.
     */
    public Vector3f getAttenuation() {
        return attenuation;
    }

    /**
     * A fény pozicióját tudjuk vele beállitani.
     * @param position A beállitani kivánt pozició.
     */
    public void setPosition(Vector3f position) {
        this.position = position;
    }

    /**
     * A fény szinét tudjuk vele lekérni.
     * @return A fény szine.
     */
    public Vector3f getColor() {
        return new Vector3f(color).mul(intensity);
    }

    /**
     * A fény intenzitását tudjuk beállitani vele.
     * @param intensity A beállitani kivánt intenzitás.
     */
    public void setIntensity(float intensity) {
        this.intensity = intensity;
    }

    /**
     * A fény konstruktora.
     * @param position A létrehozni kivánt fény poziciója.
     * @param color A létrehozni kivánt fény szine.
     * @param intensity A létrehozni kivánt fény intenzitása.
     * @param attenuation A létrehozni kivánt fény elsötétülése.
     */
    public Light(Vector3f position, Vector3f color, float intensity, Vector3f attenuation) {
        this.position = position;
        this.color = color;
        this.intensity = intensity;
        this.attenuation = attenuation;
    }
}
