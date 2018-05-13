package hu.inf.unideb.td.controller;

import hu.inf.unideb.td.Main;
import hu.inf.unideb.td.model.player.MousePicker;
import hu.inf.unideb.td.model.player.Camera;
import org.joml.Vector2f;
import org.lwjgl.BufferUtils;

import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;

public class ImputHandler {
    public static void processInput(long window, MousePicker picker)
    {
        if (glfwGetKey(window, GLFW_KEY_W) == GLFW_PRESS) {
            Camera.position.z -= 0.3;
        }
        if (glfwGetKey(window, GLFW_KEY_S) == GLFW_PRESS) {
            Camera.position.z += 0.3;
        }
        if (glfwGetKey(window, GLFW_KEY_A) == GLFW_PRESS) {
            Camera.position.x -= 0.3;
        }
        if (glfwGetKey(window, GLFW_KEY_D) == GLFW_PRESS) {
            Camera.position.x += 0.3;
        }
        if (glfwGetKey(window, GLFW_KEY_Q) == GLFW_PRESS) {
            Camera.position.y -= 0.3;
        }
        if (glfwGetKey(window, GLFW_KEY_E) == GLFW_PRESS) {
            Camera.position.y += 0.3;
        }
        if(glfwGetMouseButton(window,GLFW_MOUSE_BUTTON_1)==1)
        {
            picker.click();
        }
    }
    public static Vector2f getMouse()
    {
        DoubleBuffer b1 = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer b2 = BufferUtils.createDoubleBuffer(1);
        glfwGetCursorPos(Main.window, b1, b2);
        return new Vector2f((float)b1.get(0),(float)b2.get(0));
    }
}
