package enemyTests;

import hu.inf.unideb.td.model.gameObjects.Enemy;
import hu.inf.unideb.td.model.mapElements.Path;
import org.joml.Vector3f;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EnemyUpdateTest {
    Enemy enemy;

    @Before
    public void setUp() {
        Path.addWaypoint(new Vector3f(0, 0, 0));
        enemy=new Enemy(true);
        enemy.setPosition(new Vector3f(0,0,4));

    }

    @Test
    public void testMethods() {
        Assert.assertEquals(enemy.getPosition(),new Vector3f(0,0,4));
        enemy.update();
        Assert.assertEquals(enemy.getPosition(),new Vector3f(0,0,4f).sub(0,0,enemy.getSpeed()*0.1f));
    }
}
