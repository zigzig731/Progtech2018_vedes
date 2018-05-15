package hu.inf.unideb.td.model.mapElements;

import hu.inf.unideb.td.model.Entity;
import hu.inf.unideb.td.view.Renderer;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

/**
 * A pályán az ellenfelek által bejárt út kijelölésére, és tárolására szolgáló osztály.
 */
public class Path {
    /**
     * Ez a lista az út waypointjait tárolja.
     */
    public static List<Vector3f> waypoints = new ArrayList<Vector3f>();

    /**
     * Ezzel a metódussal adhatunk hozzá waypointot az úthoz.
     * @param waypoint A hozzáadni kivánt pont koordinátái.
     */
    public static void addWaypoint(Vector3f waypoint)
    {
        waypoints.add(waypoint);
    }

    /**
     * Ezzel a metódussal kérhetjük le az index-edik waypointot az útból.
     * @param index A lekérni kiván végpont száma.
     * @return A lekérni kiván végpont koordinátái.
     */
    public static Vector3f getWaypoint(int index)
    {
        return waypoints.get(index);
    }

    /**
     * Ez a metódus lehetővéteszi hogy megjelenitsük az útvonalat.
     * @param renderer A renderelő osztály amely a megjelenitést végzi.
     * @param waypointEntity Az entity amit minden waypoint helyén megjelenitünk.
     * @see Renderer
     */
    public static void displayPath(Renderer renderer, Entity waypointEntity)
    {
        for(Vector3f waypoint : waypoints){
            waypointEntity.setPosition(waypoint);
            renderer.render(waypointEntity);
        }
    }
}
