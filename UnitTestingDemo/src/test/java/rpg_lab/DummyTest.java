package rpg_lab;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DummyTest {

    private static final int DUMMY_HEALTH = 100;
    private static final int DUMMY_EXPERIENCE = 100;
    private static final int ATTACKED_POINTS = 10;
    private Dummy dummy;
    private Dummy deadDummy;

    @Before
    public void setUp(){
        this.dummy = new Dummy(DUMMY_HEALTH, DUMMY_EXPERIENCE);
        this.deadDummy = new Dummy(0, DUMMY_EXPERIENCE);
    }

    @Test
    public void testDummyLosesHealthWhenAttacked(){
        dummy.takeAttack(ATTACKED_POINTS);
        assertEquals(DUMMY_HEALTH - ATTACKED_POINTS, dummy.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void testDeadDummyCannotBeAttacked(){
        deadDummy.takeAttack(ATTACKED_POINTS);
    }

    @Test
    public void testDeadDummyGivesExperience(){
        assertEquals(DUMMY_EXPERIENCE, deadDummy.giveExperience());
    }

    @Test(expected = IllegalStateException.class)
    public void testAliveDummyShouldNotGiveExperience(){
        dummy.giveExperience();
    }

}