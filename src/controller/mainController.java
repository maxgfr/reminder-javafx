/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import metier.Element;
import modele.ActivateNotification;
import modele.Caretaker;
import modele.Command;
import modele.Converter;
import modele.GestionnaireSauvegarde;
import modele.Invoker;
import modele.ModeleElement;
import modele.Originator;
import modele.Receiver;

/**
 *
 * @author maxime
 */
public class mainController {
        
    @FXML
    private ListView<Element> listeDesElements;

    @FXML
    private JFXTextArea detail;
    
    @FXML
    private JFXTextField title;
    
    @FXML
    private JFXDatePicker dateSelected;
    
    @FXML
    private JFXDatePicker timeSelected;
    
    private final ObjectProperty<ModeleElement> leModele = new SimpleObjectProperty<>(new ModeleElement());
        public ModeleElement getLeModele() {return leModele.get();};
        public void setLeModele(ModeleElement param) {leModele.set(param);}
        public ObjectProperty<ModeleElement> leModeleProperty() {return leModele;}
        
        
    Originator originator = new Originator();
    Caretaker careTaker = new Caretaker();
    Invoker control = new Invoker();
    Receiver receiver = new Receiver();
    int nbElmtsListe = 1;
        
    @FXML
    public void initialize() {

        dateSelected.setValue(LocalDate.now());
        timeSelected.setTime(LocalTime.now());

        listeDesElements.setCellFactory((param) -> {
            return new ListCell<Element>(){
               @Override
                protected void updateItem(Element item, boolean empty) {
                    super.updateItem(item, empty);
                    if (! empty) {
                        textProperty().bind(item.nameProperty());
                    } else {
                        textProperty().unbind();
                        setText("");
                    }
                }  
            };
        });
        

        
        listeDesElements.getSelectionModel().selectedItemProperty().addListener((o,old,newV)->{
            if (old != null) {
                detail.textProperty().unbindBidirectional(old.texteProperty());
                title.textProperty().unbindBidirectional(old.nameProperty());
                dateSelected.valueProperty().unbindBidirectional(old.dateProperty());
                timeSelected.timeProperty().unbindBidirectional(old.timeuhProperty());
            }
            
            if (newV != null) { 
                detail.textProperty().bindBidirectional(newV.texteProperty());
                title.textProperty().bindBidirectional(newV.nameProperty());
                dateSelected.valueProperty().bindBidirectional(newV.dateProperty());
                timeSelected.timeProperty().bindBidirectional(newV.timeuhProperty());

                newV.texteProperty().addListener((oVal,oldVal,newVal)->{       
                    String texteActuel = listeDesElements.getSelectionModel().getSelectedItem().getTexte();
                    try {
                        if (texteActuel.charAt(texteActuel.length()-1) == ' ') {
                            Integer emplacement = listeDesElements.getSelectionModel().getSelectedIndex();
                            originator.setState(texteActuel);
                            originator.setNum(emplacement);
                            careTaker.add(originator.saveStateToMemento());
                            Integer nbActual = listeDesElements.getSelectionModel().getSelectedItem().getNombreSauv();
                            listeDesElements.getSelectionModel().getSelectedItem().setNombreSauv(nbActual+1);
                        }
                    } catch (IndexOutOfBoundsException e) { 
                        e.getMessage(); 
                    }
                    sauvSerial();
                });         
            }
        });
        
        chargerSerial();
        
    }
    
    
    @FXML
    protected void onCreateButton (ActionEvent event) {
        LocalDate date= LocalDate.now();
        LocalTime temps = LocalTime.now();
        Element n = new Element("Sans Titre "+nbElmtsListe,"",date,temps,false,0);
        listeDesElements.getItems().add(n);
        nbElmtsListe++;   
    }
    
    @FXML
    protected void onDeleteButton (ActionEvent event) {
        
        if (listeDesElements.getSelectionModel().getSelectedIndex() > -1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fenêtre de confirmation");
            alert.setHeaderText("Attention !");
            alert.setContentText("Voulez-vous supprimer l'élement sélectionné ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                listeDesElements.getItems().remove(listeDesElements.getSelectionModel().getSelectedIndex());
                this.title.clear();
                this.detail.clear();
            }
        } else {
            showMessage(Alert.AlertType.ERROR, null, "Element non sélectionné");
        }
        
    }
    
    @FXML
    protected void onNotificationButton (ActionEvent event) {
        long temps;
        LocalTime time = listeDesElements.getSelectionModel().getSelectedItem().getTime();
        LocalDate date = listeDesElements.getSelectionModel().getSelectedItem().getDate();
        boolean notif = listeDesElements.getSelectionModel().getSelectedItem().getBool(); 
        
        if (!notif) {
            Converter cv = Converter.getInstance();
            
            listeDesElements.getSelectionModel().getSelectedItem().setBool(true);

            temps=cv.miliSec(date, time);
            nouvTache(temps);  
        } else {
            showMessage(Alert.AlertType.INFORMATION, null, "Une alarme existe déjà");
        }
    }
       
    @FXML
    protected void onUndo (ActionEvent event) {
        Integer nbActual = listeDesElements.getSelectionModel().getSelectedItem().getNombreSauv();
        if (nbActual >0) {
            listeDesElements.getSelectionModel().getSelectedItem().setNombreSauv(nbActual-1);
            Integer emplcamnt = listeDesElements.getSelectionModel().getSelectedIndex();
            originator.getStateFromMemento(careTaker.get(nbActual-1,emplcamnt));
            listeDesElements.getSelectionModel().getSelectedItem().setTexte(originator.getState());
        }
    }
    
    @FXML
    protected void onRedo (ActionEvent event) {
        Integer nbActual = listeDesElements.getSelectionModel().getSelectedItem().getNombreSauv();
        if (nbActual >0) {
            Integer emplcamnt = listeDesElements.getSelectionModel().getSelectedIndex();
            originator.getStateFromMemento(careTaker.get(nbActual+1,emplcamnt));
            listeDesElements.getSelectionModel().getSelectedItem().setTexte(originator.getState());
        }   
    }
    
    
    private void nouvTache (long milisec) {
        
        String titre = listeDesElements.getSelectionModel().getSelectedItem().getName();
        String msg = listeDesElements.getSelectionModel().getSelectedItem().getTexte();
        int index = listeDesElements.getSelectionModel().getSelectedIndex();
        
         Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(milisec);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
         
        sleeper.setOnSucceeded((WorkerStateEvent event) -> {
            commandNotif(titre, msg);
            listeDesElements.getItems().remove(index);
        });
        
        new Thread(sleeper).start();   
    }
    
    private void commandNotif (String titre, String message) {
        Command cmd = new ActivateNotification(receiver,titre,message);
        control.setCommand(cmd);
        control.pressButton();
    }
    
    private void chargerSerial () {
        List<Element> li = new ArrayList<>();
        li = GestionnaireSauvegarde.charger();
        for (Element e : li){
            listeDesElements.getItems().add(e); 
        }
           
    }
    
    private void sauvSerial () {
        ObservableList<Element> me = leModele.get().getLesElements();
        GestionnaireSauvegarde.sauvegarder(me);
    }
    
    private Optional<ButtonType> showMessage(Alert.AlertType type,String header,String message,ButtonType... lesBoutonsDifferents){
        Alert laFenetre = new Alert(type);
        laFenetre.setHeaderText(header);
        laFenetre.setContentText(message);
        if (lesBoutonsDifferents.length > 0) {
            laFenetre.getButtonTypes().clear();
            laFenetre.getButtonTypes().addAll(lesBoutonsDifferents);
        }
        return laFenetre.showAndWait();
    }
    
    
}
