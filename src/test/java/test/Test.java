package test;

import hu.inf.unideb.td.model.Texture;
import hu.inf.unideb.td.model.gameObjects.enemies.Enemy_Runner;
import hu.inf.unideb.td.model.gameObjects.enemies.Enemy_Slow;
import hu.inf.unideb.td.model.managers.ModelManager;
import hu.inf.unideb.td.model.mapElements.Path;
import hu.inf.unideb.td.model.normalMappingObjConverter.NormalMappedObjLoader;
import hu.inf.unideb.td.model.utility.Loader;
import org.joml.Vector3f;
import org.junit.Before;

public class Test {

    Enemy_Slow enemy_slow;
    Enemy_Runner enemy_runner;
    @Before
    public void setUp()
    {
    //    Loader loader = new Loader();
    //    Texture texture = new Texture(loader.loadTexture("src/main/resources/Textures/red.png"));
     //   SetupScene.setup();
       // System.out.println(Path.waypoints.size());
       // System.out.println(Path.waypoints.size());

       // Path.waypoints.add(new Vector3f(1,1,1));
      //  Path.addWaypoint(new Vector3f(1,1,1));
       // System.out.println(Path.waypoints.get(0));
       // enemy_runner=new Enemy_Runner();
      //  enemy_slow=new Enemy_Slow();
    }

    @org.junit.Test
    public void testEnemySpeeds(){
    }
}
