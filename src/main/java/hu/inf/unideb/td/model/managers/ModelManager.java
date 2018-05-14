package hu.inf.unideb.td.model.managers;

import hu.inf.unideb.td.model.utility.Loader;
import hu.inf.unideb.td.model.Model;
import hu.inf.unideb.td.model.normalMappingObjConverter.NormalMappedObjLoader;

import java.io.InputStream;
import java.util.HashMap;

public class ModelManager {
    public static HashMap<String,Model> models=new HashMap<String, Model>();

    public static Model get(String name)
    {
        return models.get(name);
    }

    public static void add(String name)
    {
        Loader loader = new Loader();
        models.put(name, NormalMappedObjLoader.loadOBJ(name,loader));
    }
}
