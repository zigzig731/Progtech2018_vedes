package hu.inf.unideb.td.model.player;

import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Ez a kamera osztály, a renderer osztály ennek segitségével határozza meg hogy honnan nézzük a jelenetet.
 * @see hu.inf.unideb.td.view.Renderer
 */
public class Camera {
    /**
     * A kamera poziciója.
     */
    public static Vector3f position = new Vector3f(10, 15, 10);

    /**
     * A kamera forgási helyzete.
     */
    private float pitch = 30, yaw, roll;


    /**
     * Konstruktor.
     */
    public Camera() {

    }

    /**
     * Ennek segitségével lekérhetjük a karmera pozicióját.
     * @return A kamera poziciója.
     */
    public Vector3f getPosition() {
        return position;
    }

    /**
     * Ezzel a metódussal beállithatjuk a kamera pozicióját.
     * @param position A beállitani kivánt pozició.
     */
    public void setPosition(Vector3f position) {
        this.position = position;
    }
    /**
     * Ennek segitségével lekérhetjük a karmera forgását az x tengelyen.
     * @return A kamera forgási értéke az x tengelyen.
     */
    public float getPitch() {
        return pitch;
    }

    /**
     * Ennek segitségével lekérhetjük a kamera forgását az y tengelyen
     * @return A kamera forgási értéke az y tengelyen.
     */
    public float getYaw() {
        return yaw;
    }
}
