/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopapplication1.sixpack;

import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class MainThread implements Runnable{
    
    private String tps;
    private String noOfTreads;
    private String noOfAttempts;
    private String url;
    private String logfile;
    private Integer starttime;
    private Thread thread;
    private JDialog aboutBox;
    private int i;
    
    private String request;
    private String URL;
    

   /* public MainThread(String tps,String noOfTreads,String noOfAttempts,String url,String logfile,Integer starttime) {
           thread=new Thread(this,"MainThread");
           JOptionPane.showMessageDialog(aboutBox,"start time is >>>"+starttime);
        this.tps=tps;
        this.noOfTreads=noOfTreads;
        this.noOfAttempts=noOfAttempts;
        this.url=url;
        this.logfile=logfile;
        this.starttime=starttime;
        thread.start();
       
    }*/

      MainThread() {
         
        
        
    }

    public MainThread(String text, String text0) {
        this.URL=text;
        this.request=text0;
           i=1;
            thread=new Thread(this,"MainThread");
            thread.start();
        throw new UnsupportedOperationException("Not yet implemented");
    }
      
    public void passvariables(String request,String URL)
    {
        
        this.request=request;
        this.URL=URL;
        
    }

    
    public static void main(String args[]) throws ParseException
    {
        String tps                              ="20";
        String noOfTreads                       ="100";
        String noOfAttempts                     ="3";
        String url                              ="http://localhost:8180/PentahoAPI/HtpAdatper";
        String logfile                          ="C:/Documents and Settings/Administrator/Desktop/Garbage Put Here/log.txt";
        //new MainThread(tps,noOfTreads,noOfAttempts,url,logfile);
        
        String time1 = "16:00:00";
        String time2 = "19:00:00";

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date1 = format.parse(time1);
        Date date2 = format.parse(time2);
        long difference = date2.getTime() - date1.getTime(); 
        System.out.println("Milliseconds >"+difference);
        
         Calendar calendar =Calendar.getInstance();
         System.out.println("testing "+calendar.get(Calendar.MILLISECOND));
        String am_pm;
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        if(calendar.get(Calendar.AM_PM) == 0)
        am_pm = "AM";
         else
        am_pm = "PM";
        System.out.println("Current Time : " + hour + ":" 
        + minute + ":" + second + " " + am_pm);
        
        new MainThread();
        
  
    }
    
    public void run()
    {
        for(int i=0;i<10;i++)
        {
            Thread thread=new WorkingThread(URL,request);
            System.out.println("Working Thread Started >>>"+i);
           
         }
   
    }
    
    



}
