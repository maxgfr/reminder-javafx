/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import metier.Element;

/**
 *
 * @author maxime
 */
public class ModeleElement implements Serializable {
    private final ObservableList<Element> lesElementsObservables = FXCollections.observableArrayList();
    
    private final ListProperty<Element> lesElements = new SimpleListProperty<>(lesElementsObservables);
        public ObservableList<Element> getLesElements() {return lesElements.get();}
        public void setLesElements(ObservableList<Element> value) {lesElements.set(value);}
        public ReadOnlyListProperty<Element> lesElementsProperty() {return lesElements;}

    public ModeleElement(){
        
    }
    
}
