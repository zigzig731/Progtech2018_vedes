package hu.inf.unideb.td.model.player;

import hu.inf.unideb.td.Main;
import hu.inf.unideb.td.controller.ImputHandler;
import hu.inf.unideb.td.model.gameObjects.Tower;
import hu.inf.unideb.td.model.gameObjects.towers.AlapTorony;
import hu.inf.unideb.td.model.managers.GameObjectManager;
import hu.inf.unideb.td.model.utility.Maths;
import hu.inf.unideb.td.model.player.Camera;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;

import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.*;

/**
 * A MousePicker osztály végzi a számitásokat az egér 3ds térben való poziciójának megállapitásához.
 */
public class MousePicker {

    /**
     * Az egér mutatóvektora.
     */
    private Vector3f currentRay;

    /**
     * A kamera vetitési mátrixa.
     */
    private Matrix4f projectionMatrix;

    /**
     * A view mátrix.
     */
    private Matrix4f viewMatrix;
    /**
     * A kameránkn.
     */
    private Camera camera;

    /**
     * A mousepicker konstruktora.
     * @param cam A jelenlegi kamera objektumunk.
     * @param projection A vetitési mátrixunk.
     */
    public MousePicker(Camera cam, Matrix4f projection) {
        this.camera = cam;
        this.projectionMatrix = projection;
        this.viewMatrix = Maths.createViewMatrix(camera);
    }

    /**
     * A mouse picker update függvénye, minden frameben meghivódik, és frissiti a view mátrixot a kamera alapján.
     * @see Maths
     */
    public void update() {
        viewMatrix = Maths.createViewMatrix(camera);
        currentRay = calculateMouseRay();
    }

    /**
     * Az egér mutatási sugarának kiszámolását végző metódus.
     * @return A vektor ami az egér mutatóvektorával egyenlő.
     */
    private Vector3f calculateMouseRay() {
        Vector2f mouse = ImputHandler.getMouse();
       // System.out.println("x : " + b1.get(0) + ", y = " + b2.get(0));
        Vector2f normalizedCoords = getNormalizedDeviceCoords(mouse.x,mouse.y);
        Vector4f clipCoords = new Vector4f(normalizedCoords.x, normalizedCoords.y, -1f, 1f);
        Vector4f eyeCoords = toEyeCoords(clipCoords);
        Vector3f worldRay = toWorldCoors(eyeCoords);
        return worldRay;
    }

    /**
     * Ezzel a függvénnyel konvertáljuk át a clip koordinátákat eye koordinátákká.
     * @param clipCoors A konvertálni kivánt koordináták.
     * @return A konvertált koorditnáták.
     */
    private Vector4f toEyeCoords(Vector4f clipCoors) {
        Matrix4f invertedProjection = new Matrix4f(projectionMatrix).invert();
        Vector4f eyeCoords = invertedProjection.transform(clipCoors);
        return new Vector4f(eyeCoords.x, eyeCoords.y, -1f, 0f);
    }

    /**
     * Ezzel a függvénnyel konvertáljuk át az eye koordinátákat world koordinátákká.
     * @param eyeCoords A konvertálni kivánt koordináták.
     * @return A konvertált koordináták.
     */
    private Vector3f toWorldCoors(Vector4f eyeCoords) {
        Matrix4f invertedView = new Matrix4f(viewMatrix).invert();
        Vector4f rayWorld = invertedView.transform(eyeCoords);
        return new Vector3f(rayWorld.x, rayWorld.y, rayWorld.z).normalize();
    }

    /**
     * Ezzel a függvényel kapjuk meg a normalizált eszköz koordinátákat az egér poziciójából.
     * @param mouseX Az egér x tengelyen felvett helyzete.
     * @param mouseY Az egér y tengelyen felvett helyzete.
     * @return A kiszámitott koordináták.
     */
    private Vector2f getNormalizedDeviceCoords(float mouseX, float mouseY) {
        float x = (2f * mouseX) / 1280 - 1;
        float y = (2f * mouseY) / 720 - 1;
        return new Vector2f(x, y*-1);
    }

    /**
     * Ez a függvény megad egy pontot az egér mutatási sugarán.
     * @param ray A vektor amin egy pontot keresünk.
     * @param distance A pont távolsága a vektor kezdetétől.
     * @return A pont 3D-s helyzete.
     */

    private Vector3f getPointOnRay(Vector3f ray, float distance) {
        Vector3f camPos = camera.getPosition();
        Vector3f start = new Vector3f(camPos.x, camPos.y, camPos.z);
        Vector3f scaledRay = new Vector3f(ray.x * distance, ray.y * distance, ray.z*distance);
        return start.add(scaledRay);
    }


    /**
     * A sik magassága melyen az egér mutatását nézzük.
     */
    private float gridheight=2;

    /**
     * Ez a metódus adja meg hogy melyik 3ds pontra mutatunk az egerünkel.
     * @return A pont amire az egér mutat.
     */
    public Vector3f get3DPoint()
    {
        for(float i=0;i<10000;i+=0.1)
        {
            if(getPointOnRay(currentRay,i).y<gridheight) return getPointOnRay(currentRay,i);
        }
        return new Vector3f(0,0,0);
    }

    /**
     * A kattintást lekezelő metódus, mely egy tornyot helyez el a kattintás helyén.
     */
    public void click()
    {
        Tower temp = new AlapTorony();
        temp.position=new Vector3f((int)(((float)get3DPoint().x)/4.0f)*4+2,1,(int)(((float)get3DPoint().z)/4f)*4-2);
        GameObjectManager.add(temp);
    }
}
