/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blocker;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.*;

/**
 *
 * @author l
 */
public class SystemBlock {
    private WebBlock web;
    private ProgramBlock program= new ProgramBlock();
    boolean started=false;
    private AntiKiller zombie;
    
    SystemBlock() throws FileNotFoundException, UnsupportedEncodingException{
       this.web = new WebBlock("");
       this.zombie= new AntiKiller();
    }
        
    public void startBlocking(long time) throws InterruptedException, FileNotFoundException, ExecutionException, TimeoutException, IOException{
        long startTime = System.currentTimeMillis();
        web.start();
        program.start();
         this.zombie.run();
        while(System.currentTimeMillis()-startTime<time){
           Thread.sleep(1000);
        }
            
        program.kill();
        program.join();
        web.kill();
        web.join();
        zombie.kill();
        zombie.join();
    }
    
    public void stopBlocking(){
        web.kill();
        program.kill();
    }
    
    public void addPrograms(List<String> names){
       program.addPrograms(names);
    }
    
    public void addWebPages(List<String> names){
        web.addWebPages(names);
    }
    
    public void clearPrograms(){
        program.clear();
    }
    
    public void clearWebPages(){
        web.clear();
    }

    public List<String> getPrograms(){
        return this.program.getPrograms();
    }
    
    public List<String> getWebPages(){
        return this.web.getWebPages();
    }
    
    
    
}
