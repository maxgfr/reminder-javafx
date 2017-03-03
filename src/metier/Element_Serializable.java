/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author maxime
 */
public class Element_Serializable implements Serializable{
    
    private String name;
    private String desc;
    private LocalDate myDate;
    private LocalTime myTime;
    private boolean bool;
    
    public Element_Serializable (String name, String txt, LocalDate jour, LocalTime temps, boolean notif) {
        this.name= name;
        this.desc = txt;
        this.myDate = jour;
        this.myTime = temps;
        this.bool = notif;
    }
    
    public String getName() { return name;}
    public void setName(String value) { name = value; }

    public String getTexte() { return desc;}
    public void setTexte(String value) { desc = value; }

    public LocalDate getDate() { return myDate;}
    public void setDate(LocalDate value) { myDate = value; }

    public LocalTime getTime() { return myTime;}
    public void setTime(LocalTime value) { myTime = value; }
    
    public boolean getBool() { return bool;}
    public void setBool(boolean value) { bool = value; }

}
