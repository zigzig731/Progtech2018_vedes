package hu.inf.unideb.td.model;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Maths {
    private static  final  float FOV = 70;
    private static  final  float NEARPLANE=0.01f;
    private static  final  float FARPLANE=100.0f;

    public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale)
    {
        Matrix4f matrix = new Matrix4f();
        matrix.identity();
        matrix.translate(translation);
        matrix.rotate((float) Math.toRadians(rx),new Vector3f(1,0,0));
        matrix.rotate((float) Math.toRadians(ry),new Vector3f(0,1,0));
        matrix.rotate((float) Math.toRadians(rz),new Vector3f(0,0,1));
        matrix.scale(scale);
        return matrix;
    }

    public static Matrix4f createViewMatrix(Camera camera) {
        Matrix4f viewMatrix = new Matrix4f();
        viewMatrix.identity();
        viewMatrix.rotate((float) Math.toRadians(camera.getPitch()), new Vector3f(1, 0, 0));
        viewMatrix.rotate((float) Math.toRadians(camera.getYaw()), new Vector3f(0, 1, 0));
        Vector3f cameraPos = camera.getPosition();
        Vector3f negativeCameraPos = new Vector3f(-cameraPos.x,-cameraPos.y,-cameraPos.z);
        viewMatrix.translate(negativeCameraPos);
        return viewMatrix;
    }

    public static Matrix4f createProjectionMatrix(){

        float aspectRatio = (float) 1920 / (float) 1080;
        float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
        float x_scale = y_scale / aspectRatio;
        float frustum_length = FARPLANE - NEARPLANE;
        Matrix4f projectionMatrix;
        projectionMatrix = new Matrix4f();
        projectionMatrix.m00(x_scale);
        projectionMatrix.m11(y_scale);
        projectionMatrix.m22(-((FARPLANE + NEARPLANE) / frustum_length));
        projectionMatrix.m23(-1);
        projectionMatrix.m32(-((2 * NEARPLANE * FARPLANE) / frustum_length));
        projectionMatrix.m33(0);
        return projectionMatrix;
    }

}
