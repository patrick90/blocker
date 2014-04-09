/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blocker;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author l
 */
public class Blocker {
    final static Charset ENCODING = StandardCharsets.UTF_8;
    private long blockadeTime=10000;
    public SystemBlock practicalBlock;
  //  private GUI gui;
    
    private String password;
    
    Blocker() throws FileNotFoundException, UnsupportedEncodingException{
     //   this.gui= new GUI();
        this.practicalBlock= new SystemBlock();
        
    }
    
    
    public void start() throws InterruptedException, FileNotFoundException, ExecutionException, TimeoutException, IOException{
       
        practicalBlock.startBlocking(blockadeTime);
    }
    
    public void stop(){
        practicalBlock.stopBlocking();
    }
    
    public void changePassword(int length){
       this.password=Password_Generator.generatePassword(length);
    }
    
     public void addWebpages(List<String> names){
        this.practicalBlock.addWebPages(names);
     }
     
     public void clearWebpages(){
         this.practicalBlock.clearWebPages();
     }
     
     public void addPrograms(List<String> names){
         this.practicalBlock.addPrograms(names);
     }
     
     public void clearPrograms(){
         this.practicalBlock.clearPrograms();
     }
     
     public void changeTimeOfBlockade(long time){
         this.blockadeTime=time;
     }
     
     public void loadSaveFile() throws IOException{ //ładujemy standardowe ustawienia
         List<String> webpages = new ArrayList<String>();
         List<String> programs = new ArrayList<String>();
         String tmp="";
         Scanner webPagesFile = new Scanner(Paths.get("W.txt"));
         Scanner programsFile = new Scanner(Paths.get("P.txt"));
         while(webPagesFile.hasNextLine()){
             tmp=webPagesFile.nextLine();
             webpages.add(tmp);
         }
         
         while(programsFile.hasNextLine()){
             tmp=programsFile.nextLine();
             programs.add(tmp);
         }
         
         this.clearPrograms();
         this.addPrograms(programs);
         this.clearWebpages();
         this.addWebpages(webpages);
         
     }
     
     public void saveOptions() throws IOException{
         
       PrintWriter web = new PrintWriter("W.txt");
       PrintWriter program = new PrintWriter("P.txt");
       for(String tmp: this.practicalBlock.getPrograms()){
           program.println(tmp);
       }
       program.close();
       
       for(String tmp: this.practicalBlock.getWebPages()){
           web.println(tmp);
       }
       web.close();
     }
         
         
    
    public static void main(String[] args) throws InterruptedException, FileNotFoundException, ExecutionException, TimeoutException, IOException {
        // TODO code application logic here
        Blocker a = new Blocker();
      
       if(args.length==0){
           System.out.println(args.length);
           a.loadSaveFile(); 
           a.practicalBlock.startBlocking(50000);
           a.saveOptions();
       // a.stop();
         //System.out.print(a.practicalBlock.w());
        System.out.print(Password_Generator.generatePassword(100));
       } 
       else{
           System.out.println(args[0]);
           long t = Long.parseLong(args[0]);
           a.loadSaveFile(); 
           a.practicalBlock.startBlocking(t);
           a.saveOptions();
       }
       
    }
}
