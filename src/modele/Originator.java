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
public class Originator {
    
    private String state;
    private Integer num;
    
    
    public void setState(String state){
       this.state = state;
    }
    
    public void setNum (Integer num) {
        this.num = num;
    }

    public String getState(){
       return state;
    }
    
    public Integer getNum () {
        return num;
    }

    public Memento saveStateToMemento(){
       return new Memento(state, num);
    }

    public void getStateFromMemento(Memento memento){
       state = memento.getState();
       num = memento.getNum();
    }
   
}
