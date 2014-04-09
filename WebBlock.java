/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blocker;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 *
 * @author l
 */
public class WebBlock extends Thread implements Runnable {
    private List<String> webPages=new ArrayList<String>();
    private volatile boolean running = true;
    private String webAdres; // adres pliku windowsa
    private PrintWriter webB;
    
    WebBlock(String webAdres) throws FileNotFoundException{
        this.webAdres=webAdres;
        this.webB = new PrintWriter("C:\\Windows\\System32\\drivers\\etc\\hosts");
       
    }
    
    public void addWebPages(List<String> names){
        webPages.addAll(names);
    }
    
    public List<String> getWebPages(){
        return this.webPages;
    }
    
    public void clear(){
        this.webPages.clear();
    }
        
    public void blockingWebPages() throws IOException{ //?? wersja próbna
         this.webB = new PrintWriter("C:\\Windows\\System32\\drivers\\etc\\hosts");
         for(String tmp: this.webPages){
             webB.print("127.0.0.1 ");
             webB.println(tmp);
         }
         this.webB.close();
    }

    @Override
    public void run() {
      // running=true;
        while(running){
            //tutaj dzieje sie magia
            try{
                this.blockingWebPages();
                Thread.sleep(100);
            }
            catch (IOException ex){
                
            }
            catch(InterruptedException e){
                running=false;
            }
           
        }
            
    }
    
    public void kill(){
        running=false;
        webB.close();
    }

    
}
