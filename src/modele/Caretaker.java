/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author maxime
 */
public class Caretaker {
    
    private Map<Integer,List<Memento>> map = new HashMap<>();
    
    public void add(Memento state){ 
        if (map.containsKey(state.getNum())){
            for (Entry<Integer,List<Memento>> entry : map.entrySet()) {
                if (entry.getKey().equals(state.getNum())) {
                    entry.getValue().add(state);
                }
            }
        }
        else {
            List<Memento> li = new ArrayList<Memento>();
            li.add(state);
            map.put(state.getNum(),li);
        }
    }
    
    public Memento get(int index, Integer emplacement) {
        List<Memento> listeuh = new ArrayList<>();
        for (Entry<Integer,List<Memento>> entry : map.entrySet()) {
            if (entry.getKey().equals(emplacement)) {
                listeuh = entry.getValue();
            }
        }
        return listeuh.get(index);        
    }   
}