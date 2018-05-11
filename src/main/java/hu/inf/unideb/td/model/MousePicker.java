package hu.inf.unideb.td.model;

import hu.inf.unideb.td.Main;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;

import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.*;

public class MousePicker {

    private Vector3f currentRay;

    private Matrix4f projectionMatrix;
    private Matrix4f viewMatrix;
    private Camera camera;

    public MousePicker(Camera cam, Matrix4f projection) {
        this.camera = cam;
        this.projectionMatrix = projection;
        this.viewMatrix = Maths.createViewMatrix(camera);
    }

    public Vector3f getCurrentRay() {
        return currentRay;
    }

    public void update() {
        viewMatrix = Maths.createViewMatrix(camera);
        currentRay = calculateMouseRay();
    }

    private Vector3f calculateMouseRay() {
        DoubleBuffer b1 = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer b2 = BufferUtils.createDoubleBuffer(1);
        glfwGetCursorPos(Main.window, b1, b2);
       // System.out.println("x : " + b1.get(0) + ", y = " + b2.get(0));
        Vector2f normalizedCoords = getNormalizedDeviceCoords((float) b1.get(0), (float) b2.get(0));
        Vector4f clipCoords = new Vector4f(normalizedCoords.x, normalizedCoords.y, -1f, 1f);
        Vector4f eyeCoords = toEyeCoords(clipCoords);
        Vector3f worldRay = toWorldCoors(eyeCoords);
        return worldRay;
       // return new Vector3f(0,0,0);

    }

    private Vector4f toEyeCoords(Vector4f clipCoors) {
        Matrix4f invertedProjection = new Matrix4f(projectionMatrix).invert();
        Vector4f eyeCoords = invertedProjection.transform(clipCoors);
        return new Vector4f(eyeCoords.x, eyeCoords.y, -1f, 0f);
    }

    private Vector3f toWorldCoors(Vector4f eyeCoords) {
        Matrix4f invertedView = new Matrix4f(viewMatrix).invert();
        Vector4f rayWorld = invertedView.transform(eyeCoords);
        return new Vector3f(rayWorld.x, rayWorld.y, rayWorld.z).normalize();
    }

    private Vector2f getNormalizedDeviceCoords(float mouseX, float mouseY) {
        float x = (2f * mouseX) / 1280 - 1;
        float y = (2f * mouseY) / 720 - 1;
        return new Vector2f(x, y*-1);
    }

    private Vector3f getPointOnRay(Vector3f ray, float distance) {
        Vector3f camPos = camera.getPosition();
        Vector3f start = new Vector3f(camPos.x, camPos.y, camPos.z);
        Vector3f scaledRay = new Vector3f(ray.x * distance, ray.y * distance, ray.z*distance);
        return start.add(scaledRay);
    }

    private float gridheight=2;

    public Vector3f get3DPoint()
    {
//        if (glfwGetKey(Main.window, GLFW_KEY_R) == GLFW_PRESS) {
//            //  eye += forward * moveSpeed * (float) delta;
//            gridheight += 0.005;
//        }
//        if (glfwGetKey(Main.window, GLFW_KEY_F) == GLFW_PRESS) {
//            //  eye -= forward * moveSpeed * (float)delta;
//            gridheight -= 0.005;
//        }
//        System.out.println(gridheight);


        for(float i=0;i<10000;i+=0.1)
        {
            if(getPointOnRay(currentRay,i).y<gridheight) return getPointOnRay(currentRay,i);
        }
        return new Vector3f(0,0,0);
    }
}
