package hu.inf.unideb.td.model.managers;

import hu.inf.unideb.td.model.GameObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A jelenetben szereplő GameObjectek szervezését, és könnyű elérését teszi lehetővé.
 * @see GameObject
 */
public class GameObjectManager {
    /**
     * A jelenetben pillanatnyilag szereplő GameObjekteket tároló lista.
     * @see GameObject
     */
    public static List<GameObject> gameObjects = new ArrayList<GameObject>();
    /**
     * A jelenetben pillanatnyilag szereplő, de a frame végén kitörlendő GameObjekteket tároló lista.
     * @see GameObject
     */
    public static List<GameObject> gameObjectsToRemove = new ArrayList<GameObject>();

    /**
     * Ezzel a fügvénnyel visszakaphatjuk egy a listában szereplő GameObjectett,  amennyiben az nem szerepel a listában a visszaadott érték null.
     * @param gameObject A lekérdezni kivánt gameObject.
     * @return A lekérdezett gameobject referenciája, amennyiben az nem szerepel a listában, a visszatérési érték NULL.
     * @see GameObject
     */
    public static GameObject get(GameObject gameObject)
    {
        if(gameObjects.contains(gameObject))
        {
            return gameObjects.get(gameObjects.indexOf(gameObject));
        }
        return null;
    }

    /**
     * Ezel a metódussal tudunk hozzáadni a jelenetben lévő GameObjecteket tartalmazó listához.
     * @param gameObject A hozzáadni kivánt GameObject.
     * @see GameObject
     */
    public static void add(GameObject gameObject)
    {
        gameObjects.add(gameObject);
    }

    /**
     * Ezzel a fügvénnyel visszakaphatjuk egy a törlendő GameObjectek listájában szereplő GameObjectett,  amennyiben az nem szerepel a listában a visszaadott érték null.
     * @param gameObject A lekérdezni kivánt törlendő gameObject.
     * @return A lekérdezett gameobject referenciája, amennyiben az nem szerepel a listában, a visszatérési érték NULL.
     * @see GameObject
     */
    public static GameObject getRemoved(GameObject gameObject)
    {
        if(gameObjectsToRemove.contains(gameObject))
        {
            return gameObjectsToRemove.get(gameObjectsToRemove.indexOf(gameObject));
        }
        return null;
    }

    /**
     * Ezel a metódussal tudunk hozzáadni a jelenetben lévő GameObjecteket tartalmazó listához.
     * @param gameObject A hozzáadni kivánt GameObject.
     * @see GameObject
     */
    public static void addToRemoved(GameObject gameObject)
    {
        gameObjectsToRemove.add(gameObject);
    }

    /**
     * Minden frame végén hivjuk ezt a metódust, és törli a listából a feleslegessé vált gameobjecteket.
     */
    public static void clean()
    {
        gameObjects.removeAll(gameObjectsToRemove);
        gameObjectsToRemove.clear();
    }
}
