package entityTests;

import hu.inf.unideb.td.model.Entity;
import org.joml.Vector3f;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EntityTest {
    Entity entity;

    @Before
    public void setUp() {
        entity=new Entity(true);
    }


    @Test
    public void testMethods() {
        entity.setScale(1);
        Assert.assertTrue(entity.getScale()==1);
        entity.setLocalPosition(new Vector3f(0,0,0));
        Assert.assertTrue(entity.getLocalPosition().equals(new Vector3f(0,0,0)));
        entity.setLocalRotation(new Vector3f(0,0,0));
        Assert.assertTrue(entity.getLocalRotation().equals(new Vector3f(0,0,0)));
        entity.setRotation(new Vector3f(0,0,0));
        Assert.assertTrue(entity.getRotation().equals(new Vector3f(0,0,0)));
        entity.setPosition(new Vector3f(0,0,0));
        Assert.assertTrue(entity.getPosition().equals(new Vector3f(0,0,0)));
    }
}
