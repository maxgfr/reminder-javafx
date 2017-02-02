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
public class ActivateNotification implements Command {
    
    Receiver rc;
    String title;
    String message;

    public ActivateNotification (Receiver receiv, String title, String msg){
        this.rc = receiv;
        this.title = title;
        this.message = msg;
    }

    @Override
    public void execute(){
        rc.activationNotif(title,message);
    }
}