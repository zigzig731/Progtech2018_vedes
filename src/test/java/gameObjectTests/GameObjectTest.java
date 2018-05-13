package gameObjectTests;

import hu.inf.unideb.td.model.GameObject;
import hu.inf.unideb.td.model.gameObjects.Enemy;
import hu.inf.unideb.td.model.managers.GameObjectManager;
import hu.inf.unideb.td.model.mapElements.Path;
import org.joml.Vector3f;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameObjectTest {
        GameObject gameObject;

        @Before
        public void setUp() {
         gameObject = new GameObject(new Vector3f(0,0,10),new Vector3f(0,0,10),1){
             @Override
             public void update() {

             }
             };
            gameObject = new GameObject(new Vector3f(0,0,10),new Vector3f(0,0,10),1,null,null){
                @Override
                public void update() {

                }
            };
         }


        @Test
        public void testMethods() {
            Assert.assertEquals(gameObject.position,new Vector3f(0,0,10));
            Assert.assertEquals(gameObject.rotation,new Vector3f(0,0,10));
            Assert.assertEquals(gameObject.scale,1,0);
            Assert.assertNull(gameObject.lights);
            Assert.assertNull(gameObject.entities);
            gameObject.destroy();
            Assert.assertTrue(GameObjectManager.gameObjectsToRemove.contains(gameObject));
        }
    }
