package hu.inf.unideb.td.model.managers;

import hu.inf.unideb.td.model.Material;
import hu.inf.unideb.td.model.MaterialInstance;

import java.util.HashMap;
/**
 * A játékban használt Materialok, és MaterialInstanceok szervezését, és könnyű elérését teszi lehetővé.
 * @see Material
 */
public class MaterialManager {
    /**
     * Ez a hashmap tárolja a programban lévő MaterialInstance-okat.
     * @see MaterialInstance
     */
    public static HashMap<String, MaterialInstance> materialsInstances = new HashMap<String, MaterialInstance>();
    /**
     * Ez a hashmap tárolja a programban lévő Materialokat.
     * @see Material
     */
    public static HashMap<String, Material> materials = new HashMap<String, Material>();

    /**
     * Ezzel a függvénnyel tudunk lekérni egy MaterialInstancet a hashmapból név alapján.
     * @param name A lekérni kivánt MaterialInstance neve.
     * @return A lekért MaterialInstance
     * @see MaterialInstance
     */
    public static MaterialInstance get(String name) {
        return materialsInstances.get(name);
    }

    /**
     * Ez a metódus lehetővé teszi, hogy személyreszabott materialokat adjak hozzá a programhoz.
     * @param name A hozzáadni kivánt material neve.
     * @param diffuseIntensity A hozzáadni kivánt material szinerőssége.
     * @param specularIntensity A hozzáadni kivánt material csillogósságának erőssége.
     * @param normalIntensity A hozzáadni kivánt material normalmapjének erőssége.
     * @param uvTileing A hozzáadni kivánt material ismétlődésének mértéke.
     */
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

    /**
     * Ez a metódus lehetővéteszi, hogy alap materialokat adjak hozzá a programhoz.
     * @param name A hozzáadni kivánt material neve.
     */
    public static void add(String name) {
        materials.put(name, new Material(name, 1, 1, 1, 1));
        materialsInstances.put(name + "_base",
                new MaterialInstance(materials.get(name), 1, 1, 1, 1));
    }
}
