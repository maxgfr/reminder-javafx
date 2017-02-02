/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import metier.Element;

/**
 *
 * @author maxime
 */
public class GestionnaireSauvegarde {

	public static ObservableList<Element> charger(Path file) throws FileNotFoundException, IOException, ClassNotFoundException {
            try {
                InputStream in = Files.newInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(in);
                List<Element> list = (List<Element>) ois.readObject() ;
                return FXCollections.observableList(list);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return FXCollections.emptyObservableList();
	}
	
	public static void sauvegarder(ObservableList<Element> ol, Path file) throws FileNotFoundException, IOException {
            try {
                // write object to file
                OutputStream fos = Files.newOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(new ArrayList<Element>(ol));
                oos.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

	}
        
}
