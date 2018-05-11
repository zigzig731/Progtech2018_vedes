package hu.inf.unideb.td.model;

import java.util.HashMap;

public class MaterialManager {
    public static HashMap<String, MaterialInstance> materialsInstances = new HashMap<String, MaterialInstance>();
    public static HashMap<String, Material> materials = new HashMap<String, Material>();

    public static MaterialInstance get(String name) {
        return materialsInstances.get(name);
    }

    public static void add(String name, float diffuseIntensity, float specularIntensity, float normalIntensity, float uvTileing) {
        if (materials.containsKey(name.substring(0, name.indexOf('_')))) {
            materialsInstances.put(name,
                    new MaterialInstance(materials.get(name.substring(0, name.indexOf('_'))), diffuseIntensity, specularIntensity, normalIntensity, uvTileing));
        } else {
            materials.put(name.substring(0, name.indexOf('_')), new Material(name.substring(0, name.indexOf('_')), 1, 1, 1, 1));
            materialsInstances.put(name,
                    new MaterialInstance(materials.get(name.substring(0, name.indexOf('_'))), diffuseIntensity, specularIntensity, normalIntensity, uvTileing));
            materialsInstances.put(name + "_base",
                    new MaterialInstance(materials.get(name.substring(0, name.indexOf('_'))), 1, 1, 1, 1));
        }
    }

    public static void add(String name) {
        materials.put(name, new Material(name, 1, 1, 1, 1));
        materialsInstances.put(name + "_base",
                new MaterialInstance(materials.get(name), 1, 1, 1, 1));
    }
}
