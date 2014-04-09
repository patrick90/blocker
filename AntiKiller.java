/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blocker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author l
 */
public class AntiKiller extends Thread implements Runnable{
    
    private  String path;
    private volatile boolean running = true;
    AntiKiller() throws UnsupportedEncodingException{
        this.path=AntiKiller.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        this.path = this.path.substring(1);
       //this.path=this.path.replace("/", "\"");
        this.path = this.path.replace("Blocker.jar", "antikiller.exe");
        System.out.println(this.path);
    }
     public boolean isItWorking() throws IOException{
        Process p = Runtime.getRuntime().exec("tasklist");
        String line; 
        System.out.println(this.path + " asf");
        BufferedReader input =
                new BufferedReader(new InputStreamReader(p.getInputStream())); 
         while((line = input.readLine())!= null){
             if(line.contains("antikiller.exe")) return true;
         }
         return false; 
    }
    
    public void resurect() throws IOException{
        Runtime.getRuntime().exec(this.path);
    }
    
    
    public static void main(String[] args) throws UnsupportedEncodingException {
        AntiKiller anti = new AntiKiller();
        anti.run();
    }

    @Override
    public void run() {
       while(running){
            try {
                Thread.sleep(1000);
                if(this.isItWorking()==false) this.resurect();
            } catch (InterruptedException ex) {
                System.out.println("interupted antikiller");
            }
            catch (IOException ex){}
       }
        
    }
    
    public void kill() throws IOException{
        this.setRunning(false);
         Runtime.getRuntime().exec("taskkill /F /IM " + "antikiller.exe");
    }
    
    public void setRunning(boolean value){
        this.running=value;
    }
    
}
