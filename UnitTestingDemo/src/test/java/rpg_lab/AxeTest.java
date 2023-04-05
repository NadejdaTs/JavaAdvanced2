package rpg_lab;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;
//import static rpg_lab.Axe.DURABILITY_LOSS;

public class AxeTest {
    //A -> Arrange
    //A -> Act
    //A -> Assert

    private static final int AX_ATTACK = 10;
    private static final int AX_DURABILITY = 10;
    private static final int DUMMY_HEALTH = 100;
    private static final int DUMMY_EXPERIENCE = 100;
    private static final int DURABILITY_LOSS = 1;

    private Axe axe;
    private Axe brokenAxe;
    private Dummy dummy;

    @Before
    public void setUp(){
        Axe axe = new Axe(AX_ATTACK, AX_DURABILITY);
        Axe brokenAxe = new Axe(AX_ATTACK, 0);
        Dummy dummy = new Dummy(DUMMY_HEALTH, DUMMY_EXPERIENCE);
    }

    @Test
    public void testAxeLosesDurabilityAfterEachAttack(){
        axe.attack(dummy);
        assertEquals(AX_ATTACK - DURABILITY_LOSS, axe.getDurabilityPoints());
        //assertEquals("Failed test", 9, axe.getDurabilityPoints()); - it is not necessary
    }

    @Test(expected = IllegalStateException.class)
    public void testAttackingWithBrokenAxeShouldThrow(){
        brokenAxe.attack(dummy);
    }

}