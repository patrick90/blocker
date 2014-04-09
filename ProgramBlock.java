/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blocker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author l
 */
public class ProgramBlock extends Thread implements Runnable{
    private long time;
    private volatile boolean running=true;
    private List<String> programs=new ArrayList<String>();
    
    public void setTime(long time){
        this.time=time;
    }
    public void addPrograms(List<String> names){
        programs.addAll(names);
    }
    
    public List<String> getPrograms(){
        return this.programs;
    }
    
    public void clear(){
        this.programs.clear();
    }
    
   
    public void kill(){
        this.running=false;
    }
    
    public void blockPrograms() throws IOException{
        for(String tmp: this.programs) {
            System.out.println("taskkill /F /IM " + tmp);
            Runtime.getRuntime().exec("taskkill /F /IM " + tmp);
        }
    }

    @Override
    public void run() {
        
        while(running){
            try {
                this.blockPrograms();
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                
            }
            catch (IOException ex){}
        }
        
    }
}
