package com.example.stickhero_2;



import javafx.scene.shape.Rectangle;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

public class TestClass {

    @Test
    public void testcalculateDistance(){
        //x1 = 0 , width = 5
        Rectangle r1 = new Rectangle(0,0,5,10);
        //x2 = 10 , width = 5
        Rectangle r2 = new Rectangle(10,0,5,10);

        Pillar p = new Pillar();
        double ans = p.calculateDistance(r1,r2);

        //10-5 = 5 (as x1 ends at 5 and x2 starts at 10)
        assertEquals(5.0,ans);

    }

    @Test
    public void testsubCherries(){
        Hero cherry = new Hero();
        int before = Hero.getCherries();
        int subvalue = 2;
        cherry.subCherries(subvalue);
        int after = Hero.getCherries();
        int ans = before - subvalue;
        assertEquals(ans,after);

        //adding for the game to run later properly
        cherry.subCherries(-2);
    }

    @Test
    public void testrandomNumber() throws Exception {
        Cherries c = new Cherries();
        double num = c.randomNumber();
        if (num > 1.0) {
            throw new Exception("Wrong : Need to generate a number less than 1");
        }

    }

    @Test
    public void testisFlip(){
        //initially this flag is set to false
        boolean before = Hero.isFlip();
        assertFalse(before);
    }


}

