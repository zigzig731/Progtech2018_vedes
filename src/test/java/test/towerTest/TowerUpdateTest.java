package test.towerTest;

import hu.inf.unideb.td.model.gameObjects.Enemy;
import hu.inf.unideb.td.model.gameObjects.Tower;
import hu.inf.unideb.td.model.managers.GameObjectManager;
import hu.inf.unideb.td.model.mapElements.Path;
import org.joml.Vector3f;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TowerUpdateTest {
    Tower tower;

    @Before
    public void setUp() {
        tower = new Tower(true);
        tower.position = new Vector3f(0, 0, 0);

        Path.addWaypoint(new Vector3f(0, 0, 0));

        GameObjectManager.add(new Enemy(true));
        GameObjectManager.gameObjects.get(0).position = new Vector3f(4, 0, 4);
        ((Enemy) GameObjectManager.gameObjects.get(0)).setFittness(10);
    }

    @Test
    public void testMethods() {
        tower.update();
        Assert.assertTrue(tower.target.equals(GameObjectManager.gameObjects.get(0)));
    }
}
