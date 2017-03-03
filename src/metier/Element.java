/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import java.time.LocalDate;
import java.time.LocalTime;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author maxime
 */
public class Element {
    
    private final StringProperty name = new SimpleStringProperty();
        public String getName() { return name.get();}
        public void setName(String value) { name.set(value); }
        public StringProperty nameProperty() { return name; }
    
    private final StringProperty texte = new SimpleStringProperty();
        public String getTexte() { return texte.get();}
        public void setTexte(String value) { texte.set(value); }
        public StringProperty texteProperty() { return texte; }
        
    
    private final ObjectProperty<LocalDate> myDate = new SimpleObjectProperty<LocalDate>();
        public LocalDate getDate() { return myDate.get();}
        public void setDate(LocalDate value) { myDate.set(value); }
        public ObjectProperty<LocalDate> dateProperty() { return myDate; }
        
    private final ObjectProperty<LocalTime> myTime = new SimpleObjectProperty<LocalTime>();
        public LocalTime getTime() { return myTime.get();}
        public void setTime(LocalTime value) { myTime.set(value); }
        public ObjectProperty<LocalTime> timeuhProperty() { return myTime; }
        
    private final BooleanProperty bool = new SimpleBooleanProperty();
        public boolean getBool() { return bool.get();}
        public void setBool (boolean value) { bool.set(value); }
        public BooleanProperty boolProperty() { return bool; }
        
    private final IntegerProperty nbSauv = new SimpleIntegerProperty();
        public Integer getNombreSauv() { return nbSauv.get(); }
        public void setNombreSauv (Integer value) { nbSauv.set(value); }
        public IntegerProperty nbSauvProperty() { return nbSauv; }
    
    public Element (String name, String txt, LocalDate jour, LocalTime temps, boolean notif, Integer nbSauv) {
        this.name.set(name);
        texte.set(txt);
        myDate.set(jour);
        myTime.set(temps);
        bool.set(notif);
        this.nbSauv.set(nbSauv);
    }

    
}
