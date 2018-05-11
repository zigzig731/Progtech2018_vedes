package test;

import hu.inf.unideb.td.model.Enemy;
import hu.inf.unideb.td.model.Enemy_Runner;
import hu.inf.unideb.td.model.Enemy_Slow;
import org.junit.Assert;
import org.junit.Before;

public class Test {

    Enemy_Slow enemy_slow;
    Enemy_Runner enemy_runner;
    @Before
    public void setUp()
    {
        enemy_runner=new Enemy_Runner();
        enemy_slow=new Enemy_Slow();
    }

    @org.junit.Test
    public void testEnemySpeeds(){
    }
}
