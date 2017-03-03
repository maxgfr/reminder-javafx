/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import metier.Element;
import metier.Element_Serializable;

/**
 *
 * @author maxime
 */
public class GestionnaireSauvegarde {

    public static void serialize (List<Element_Serializable> elmt) {
	try {
            FileOutputStream fichier;
            fichier = new FileOutputStream("data.ser");
            try (ObjectOutputStream oos = new ObjectOutputStream(fichier)) {
                oos.writeObject(elmt);
                oos.flush();
                fichier.close();
            }
        }
        catch (IOException e) {
            e.getMessage();
        }
    }
    
    public static List<Element_Serializable> deSerialize () {
       try {
            FileInputStream fichier = new FileInputStream("data.ser");
            ObjectInputStream ois = new ObjectInputStream(fichier);
            List<Element_Serializable> x = (List<Element_Serializable>) ois.readObject();
            return x;
        } catch (IOException | ClassNotFoundException e  ) {
            e.getMessage();
        }
       return null;
    }
    
    public static void sauvegarder (ObservableList<Element> me) {
        int size = me.size();
        List<Element_Serializable> li = new ArrayList<>();
        for (int i=0; i<size; ++i){
            Element e = me.get(i);
            Element_Serializable elmt = new Element_Serializable(e.getName(),e.getTexte(),e.getDate(),e.getTime(),e.getBool());
            li.add(elmt);
        }
        serialize(li);
    }
    
    
    public static List<Element> charger () {
        List<Element> x = new ArrayList<>();
        List<Element_Serializable> li = deSerialize();
        if (li != null){
            for (Element_Serializable elmt : li){
            Element e = new Element(elmt.getName(),elmt.getTexte(),elmt.getDate(),elmt.getTime(),elmt.getBool(),0);
            x.add(e);
            }
        }
        return x;
    }
        
}
