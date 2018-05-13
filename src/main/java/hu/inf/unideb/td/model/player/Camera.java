package hu.inf.unideb.td.model.player;

import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;

public class Camera {
    public static Vector3f position = new Vector3f(10, 15, 10);
    private float pitch = 30, yaw, roll;

    public Camera() {

    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getRoll() {
        return roll;
    }

    public void setRoll(float roll) {
        this.roll = roll;
    }
}
