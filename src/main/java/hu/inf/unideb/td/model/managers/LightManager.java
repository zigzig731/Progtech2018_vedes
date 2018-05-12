package hu.inf.unideb.td.model.managers;

import hu.inf.unideb.td.model.Light;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class LightManager {
    private static List<Light> lights = new ArrayList<Light>();

    public static void add(Vector3f position, Vector3f color, float intensity, Vector3f attenuation){
        lights.add(new Light(position,color,intensity,attenuation));
    }
    public static void add(Light light){
        lights.add(light);
    }

    public static List<Light> getLights(){
        return lights;
    }
}
