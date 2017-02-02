/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author maxime
 */
public class Memento {
    
    private final String state;
    private final Integer numNote;
    
    public Memento(String state, Integer num) {
        this.state = state;
        this.numNote = num;
    }
    
    public String getState() {
        return state;
    }
    
    public Integer getNum() {
        return numNote;
    }
    
}
