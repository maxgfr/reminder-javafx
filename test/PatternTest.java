/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import modele.Caretaker;
import modele.Originator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 *
 * @author maxime
 */
public class PatternTest {
    
    public PatternTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void memento() {
      Originator originator = new Originator();
      Caretaker careTaker = new Caretaker();
      
      originator.setState("State #1");
      originator.setState("State #2");
      originator.setNum(1);
      careTaker.add(originator.saveStateToMemento());
      
      originator.setState("State #3");
      careTaker.add(originator.saveStateToMemento());
      
      originator.setState("State #4");
      System.out.println("Current State: " + originator.getState());		
      
      originator.getStateFromMemento(careTaker.get(0,1));
      System.out.println("First saved State: " + originator.getState());
      originator.getStateFromMemento(careTaker.get(1,1));
      System.out.println("Second saved State: " + originator.getState());
    }
}
