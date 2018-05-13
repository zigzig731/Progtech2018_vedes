package test;

import hu.inf.unideb.td.model.gameObjects.Enemy;
import hu.inf.unideb.td.model.gameObjects.Tower;
import hu.inf.unideb.td.model.managers.GameObjectManager;
import hu.inf.unideb.td.model.mapElements.Path;
import org.joml.Vector3f;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TowerTest {
    Tower tower;

    @Before
    public void setUp()
    {
        tower = new Tower(true);
        tower.position=new Vector3f(0,0,0);

        Path.addWaypoint(new Vector3f(0,0,0));

        GameObjectManager.add(new Enemy(true));
        GameObjectManager.gameObjects.get(0).position=new Vector3f(0,1,4);
        ((Enemy)GameObjectManager.gameObjects.get(0)).setFittness(10);
        GameObjectManager.add(new Enemy(true));
        GameObjectManager.gameObjects.get(1).position=new Vector3f(0,2,2);
        ((Enemy)GameObjectManager.gameObjects.get(1)).setFittness(23);
        GameObjectManager.add(new Enemy(true));
        GameObjectManager.gameObjects.get(2).position=new Vector3f(0,0,11);
        ((Enemy)GameObjectManager.gameObjects.get(2)).setFittness(40);
    }

    @Test
    public void testMethods()
    {
        tower.getTargetsInRange();

        Assert.assertTrue(tower.targetsInRange.contains(GameObjectManager.gameObjects.get(0)));
        Assert.assertTrue(tower.targetsInRange.contains(GameObjectManager.gameObjects.get(1)));
        Assert.assertFalse(tower.targetsInRange.contains(GameObjectManager.gameObjects.get(2)));

        tower.target=tower.choseTarget();

        Assert.assertTrue(GameObjectManager.gameObjects.get(1).equals(tower.target));


        Assert.assertTrue(tower.isValidTarget());

        GameObjectManager.gameObjects.get(1).position=new Vector3f(0,10,10);
        Assert.assertFalse(tower.isValidTarget());
        GameObjectManager.gameObjects.get(1).position=new Vector3f(0,2,2);

        Assert.assertTrue(tower.isValidTarget());

        GameObjectManager.gameObjects.remove(tower.target);
        Assert.assertFalse(tower.isValidTarget());

        tower.getTargetsInRange();
        tower.choseTarget();

        Assert.assertTrue(tower.targetsInRange.contains(GameObjectManager.gameObjects.get(0)));

      //  tower.shoot();
   //     Assert.assertEquals(tower.target.getHealt(),tower.target.getMaxHealt()-tower.getDamage(),tower.getDamage());
    }

}
