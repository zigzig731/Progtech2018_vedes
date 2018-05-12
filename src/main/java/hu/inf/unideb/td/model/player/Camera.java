package hu.inf.unideb.td.model.player;

import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;

public class Camera {
    public static Vector3f position = new Vector3f(0, 15, 10);
    private float pitch = 30, yaw, roll;

    public Camera() {

    }

    public void move(long window) {
        if (glfwGetKey(window, GLFW_KEY_W) == GLFW_PRESS) {
            //  eye += forward * moveSpeed * (float) delta;
            position.z -= 0.3;
        }
        if (glfwGetKey(window, GLFW_KEY_S) == GLFW_PRESS) {
            //  eye -= forward * moveSpeed * (float)delta;
            position.z += 0.3;
        }
        if (glfwGetKey(window, GLFW_KEY_A) == GLFW_PRESS) {
            //  eye += right * moveSpeed * (float)delta;
            position.x -= 0.3;
        }
        if (glfwGetKey(window, GLFW_KEY_D) == GLFW_PRESS) {
            // eye -= right * moveSpeed * (float)delta;
            position.x += 0.3;
        }
        if (glfwGetKey(window, GLFW_KEY_Q) == GLFW_PRESS) {
            //  eye += right * moveSpeed * (float)delta;
            position.y -= 0.3;
        }
        if (glfwGetKey(window, GLFW_KEY_E) == GLFW_PRESS) {
            // eye -= right * moveSpeed * (float)delta;
            position.y += 0.3;
        }
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
