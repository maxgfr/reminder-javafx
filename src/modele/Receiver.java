/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.time.LocalDate;
import java.time.LocalTime;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import tray.animations.AnimationType;
import tray.notification.*;

/**
 *
 * @author maxime
 */
public class Receiver {
    
    public void activationNotif(String title, String message){    
        Image img = new Image("/assets/clock-icon.png");    
        
        NotificationType notification = NotificationType.ERROR;
        TrayNotification tray = new TrayNotification();
        
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(notification);
        tray.setRectangleFill(Paint.valueOf("#2A9A84"));
        tray.setAnimationType(AnimationType.POPUP);
        tray.setImage(img);
        tray.showAndWait();
        
    }
    
}
