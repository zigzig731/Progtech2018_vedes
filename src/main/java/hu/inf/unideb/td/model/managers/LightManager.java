package hu.inf.unideb.td.model.managers;

import hu.inf.unideb.td.model.Light;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;
/**
 * A jelenetben szereplő fények szervezését, és könnyű elérését teszi lehetővé.
 * @see Light
 */
public class LightManager {
    /**
     * A jelenetben pillanatnyilag szereplő Lightok listája.
     * @see Light
     */
    private static List<Light> lights = new ArrayList<Light>();

    /**
     * Ezzel a metódussal hozzáadhatunk a jelenetünkhoz fényeket.
     * @param position A hozzáadni kivánt fény poziciója.
     * @param color A hozzáadni kivánt fény szine.
     * @param intensity A hozzáadni kivánt fény intenzitása.
     * @param attenuation A hozzáadni kivánt fény elsötétülése.
     */
    public static void add(Vector3f position, Vector3f color, float intensity, Vector3f attenuation){
        lights.add(new Light(position,color,intensity,attenuation));
    }

    /**
     * Ezzel a metódussal hozzáadhatunk a jelenetünkhoz fényeket.
     * @param light  Hozzáadni kivánt fény.
     */
    public static void add(Light light){
        lights.add(light);
    }

    /**
     * Ezzel a metódussal le tudjuk kérni a jelenetben szereplő fények listáját.
     * @return A jelenetben szereplő fények listája.
     */
    public static List<Light> getLights(){
        return lights;
    }
}
