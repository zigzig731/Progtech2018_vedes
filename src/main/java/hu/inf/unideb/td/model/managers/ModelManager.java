package hu.inf.unideb.td.model.managers;

import hu.inf.unideb.td.model.GameObject;
import hu.inf.unideb.td.model.utility.Loader;
import hu.inf.unideb.td.model.Model;
import hu.inf.unideb.td.model.normalMappingObjConverter.NormalMappedObjLoader;

import java.io.InputStream;
import java.util.HashMap;

/**
 * A játékban használt Modellek szervezését, és könnyű elérését teszi lehetővé.
 * @see Model
 */
public class ModelManager {
    /**
     * A játékban használt Modellek tárolására szolgáló HashMap.
     * @see Model
     */
    public static HashMap<String,Model> models=new HashMap<String, Model>();

    /**
     * Ezzel a függvénnyel lekérhetünk egy modellt a listából név alapján.
     * @param name A lekérdezni kivánt modell neve.
     * @return A lekérdezett modell.
     */
    public static Model get(String name)
    {
        return models.get(name);
    }

    /**
     * Ezzel a függvénnyel új modellt adhatunk hozzá a modellek listájához, egy név alapján.
     * @param name A beolvasni/hozzáadni kivánt modell neve.
     */
    public static void add(String name)
    {
        Loader loader = new Loader();
        models.put(name, NormalMappedObjLoader.loadOBJ(name,loader));
    }
}
