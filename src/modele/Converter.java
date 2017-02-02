/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import static java.lang.Math.abs;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author maxime
 */
public class Converter {

    private static Converter instance;
    
    protected Converter () {}
    
    public static Converter getInstance () {
        if(instance==null) {
            instance = new Converter();       
        }      
        return instance;
    }
    
    public long miliSec (LocalDate ld, LocalTime lt){
        LocalDateTime ldt = LocalDateTime.of(ld, lt);
        LocalDateTime actual = LocalDateTime.now();
        long tpsAttMili;

        LocalDateTime tempDateTime = LocalDateTime.from( ldt );
        long years = tempDateTime.until( actual, ChronoUnit.YEARS);
        tempDateTime = tempDateTime.plusYears( years );
        long months = tempDateTime.until( actual, ChronoUnit.MONTHS);
        tempDateTime = tempDateTime.plusMonths( months );
        long days = tempDateTime.until( actual, ChronoUnit.DAYS);
        tempDateTime = tempDateTime.plusDays( days );
        long hours = tempDateTime.until( actual, ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusHours( hours );
        long minutes = tempDateTime.until( actual, ChronoUnit.MINUTES);
        tempDateTime = tempDateTime.plusMinutes( minutes );
        long seconds = tempDateTime.until( actual, ChronoUnit.SECONDS);

        tpsAttMili = seconds*1000 + minutes*60*1000 + hours*60*60*1000 + days*24*60*60*1000 + months*30*24*60*60*1000 + years*12*30*24*60*60*1000;

        return abs(tpsAttMili);
        
    }
}