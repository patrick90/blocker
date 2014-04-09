/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blocker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author l
 */
public class TimeActual implements ActionListener{
    private JLabel actualTime;
    private int totalTimeInMinutes;
    private int timeInSeconds;

    
    TimeActual(JLabel actualTime, int totalTimeInMinutes ){
        this.actualTime=actualTime;
        this.totalTimeInMinutes=totalTimeInMinutes;
        this.timeInSeconds = this.totalTimeInMinutes*60;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
               
               timeInSeconds=timeInSeconds-1;
               if(timeInSeconds>=0) this.actualTime.setText(Integer.toString(timeInSeconds) + " seconds"); 
    }
    
}
