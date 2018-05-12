package test;

import hu.inf.unideb.td.model.*;
import hu.inf.unideb.td.model.gameObjects.Tower;
import hu.inf.unideb.td.model.managers.GameObjectManager;
import org.junit.Before;
import org.junit.Test;

public class TowerTest {
    Tower tower;

    @Before
    public void setUp()
    {
        SetupScene.setupModels();
       // Path.addWaypoint(new Vector3f(0,0,0));
      //  GameObjectManager.add(new Enemy_Runner());
     //   GameObjectManager.gameObjects.get(0).position=new Vector3f(0,1,4);
     //   GameObjectManager.add(new Enemy_Runner());
     //   GameObjectManager.gameObjects.get(1).position=new Vectodr3f(0,2,2);
     //   GameObjectManager.add(new Enemy_Runner());
     //   GameObjectManager.gameObjects.get(2).position=new Vector3f(0,0,11);
        System.out.println(GameObjectManager.gameObjects.size());
       // tower=new Tower();
      //  tower.position=new Vector3f(0,0,0);
    }

    @Test
    public void testMethod_getTargetsInRange()
    {
//        tower.getTargetsInRange();

        //Assert.assertTrue(tower.targetsInRange.contains(GameObjectManager.gameObjects.get(0)));
        //Assert.assertTrue(tower.targetsInRange.contains(GameObjectManager.gameObjects.get(1)));
       // Assert.assertFalse(tower.targetsInRange.contains(GameObjectManager.gameObjects.get(2)));
    }
}
