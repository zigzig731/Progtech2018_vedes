package hu.inf.unideb.td.model;

import org.joml.Vector3f;

public class SetupScene {
    public static void setup()
    {
        setupMaterials();
        setupModels();
        setupLights();
    }

    public static void setupMaterials(){
        MaterialManager.add("forest");
        MaterialManager.add("gradient");
        MaterialManager.add("metal");
        MaterialManager.add("metal_light");
        MaterialManager.add("Asphalt");
        MaterialManager.add("water");
        MaterialManager.get("water_base").animated=true;
        MaterialManager.add("metal_waypoint",1,1,1,1);
        Material healthbar=new Material(new Vector3f(0,1,0));
        MaterialManager.materials.put("healthbar",healthbar);
        MaterialInstance healthbar_base=new MaterialInstance(new Vector3f(0,1,0));
        MaterialManager.materialsInstances.put("healthbar_base",healthbar_base);

    }

    public static void setupModels(){
        ModelManager.add("bunny");
        ModelManager.add("bunnyHR");
        ModelManager.add("terrainHR");
        ModelManager.add("towergun");
        ModelManager.add("towerbase");
        ModelManager.add("gridunit");
        ModelManager.add("enemy");
        ModelManager.add("waypoint");
        ModelManager.add("rangeindicator");
        ModelManager.add("healthbar");
        ModelManager.add("plane");
        ModelManager.add("terrain_road");
        ModelManager.add("water");
        ModelManager.add("mapbuildings");
        ModelManager.add("muzzleflash");
    }

    public static void setupEntities(){

    }

    public static void setupLights(){
        LightManager.add(new Vector3f(5,5,-10),new Vector3f(1f,0f,0f),2, new Vector3f(8f,0.5f,0f));
        LightManager.add(new Vector3f(0,2,-8),new Vector3f(0f,0f,1f),2,new Vector3f(5f,1f,0f));
        LightManager.add(new Vector3f(-3,2,-12),new Vector3f(0f,1f,0f),3,new Vector3f(5f,0.3f,0f));
        LightManager.add(new Vector3f(0,100,-100),new Vector3f(1f,1f,0.7f),2f,new Vector3f(1000f,1f,0f));
    }
}
