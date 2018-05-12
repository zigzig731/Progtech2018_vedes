package hu.inf.unideb.td.model.managers;

import hu.inf.unideb.td.model.GameObject;

import java.util.ArrayList;
import java.util.List;

public class GameObjectManager {
    public static List<GameObject> gameObjects = new ArrayList<GameObject>();
    public static List<GameObject> gameObjectsToRemove = new ArrayList<GameObject>();

    public static GameObject get(GameObject gameObject)
    {
        if(gameObjects.contains(gameObject))
        {
            return gameObjects.get(gameObjects.indexOf(gameObject));
        }
        return null;
    }

    public static void add(GameObject gameObject)
    {
        gameObjects.add(gameObject);
    }

    public static GameObject getRemoved(GameObject gameObject)
    {
        if(gameObjectsToRemove.contains(gameObject))
        {
            return gameObjectsToRemove.get(gameObjectsToRemove.indexOf(gameObject));
        }
        return null;
    }

    public static void addToRemoved(GameObject gameObject)
    {
        gameObjectsToRemove.add(gameObject);
    }

    public static void clean()
    {
        gameObjects.removeAll(gameObjectsToRemove);
        gameObjectsToRemove.clear();
    }
}
