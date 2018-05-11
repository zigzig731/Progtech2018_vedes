package hu.inf.unideb.td.model;

import hu.inf.unideb.td.view.Renderer;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class Path {
    public static List<Vector3f> waypoints = new ArrayList<Vector3f>();

    public static void addWaypoint(Vector3f waypoint)
    {
        waypoints.add(waypoint);
    }

    public static Vector3f getWaypoint(int index)
    {
        return waypoints.get(index);
    }

    public static void displayPath(Renderer renderer, Entity waypointEntity)
    {
        for(Vector3f waypoint : waypoints){
            waypointEntity.setPosition(waypoint);
            renderer.render(waypointEntity);
        }
    }
}
