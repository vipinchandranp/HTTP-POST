/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopapplication1.sixpack;

import desktopapplication1.DesktopApplication1View;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class WorkingThread extends Thread{
    
  private static  String  request;
  private static  String  URL;

    WorkingThread(String URL, String request) {
         this.request=request;
        this.URL=URL;
        start();
     
        throw new UnsupportedOperationException("Not yet implemented");
    }
  

    public void run()
    {
        
        new ReminderBeep(1);
       
    }
    
        
public static class ReminderBeep {
  Toolkit toolkit;

  Timer timer;

  public ReminderBeep(int seconds) {
    toolkit = Toolkit.getDefaultToolkit();
    timer = new Timer();
    timer.schedule(new RemindTask(), seconds * 1000);
  }

  class RemindTask extends TimerTask {
    public void run() {
        int i=0;
      
       System.out.println(i);
       Calendar calendar =Calendar.getInstance();
       int second1 = calendar.get(Calendar.SECOND);
        System.out.println(second1);
       int second2;
       while(true)
       {   
           Calendar calendar2 =Calendar.getInstance();
           second2= calendar2.get(Calendar.SECOND);
           System.out.println(i+">>>>>>>>>>"+second2+second1);
           if((second2-second1)==1)
           {
                        try {
                            
                             String resp = WorkingThread.goPost(URL,request);
                           
                             
                             System.out.println("response>>>>"+resp);
                            break;
                        } catch (Exception ex) {
                            Logger.getLogger(WorkingThread.class.getName()).log(Level.SEVERE, null, ex);
                        }
           }
           i++;
       }
          
      toolkit.beep();
      //timer.cancel(); //Not necessary because we call System.exit
      System.exit(0); //Stops the AWT thread (and everything else)
    }
  }

  //public static void main(String args[]) {
    //System.out.println("About to schedule task.");
    //new ReminderBeep(5);
    //System.out.println("Task scheduled.");
  //}
}

public static String goPost(String urlPath, String reqStr) throws Exception {
		String rspStr = null;
		OutputStreamWriter out = null;
		InputStream in = null;
		try {
			URL url = new URL(urlPath.trim());
			URLConnection connection = url.openConnection();
			// connection.setReadTimeout(60000);
			connection.setDoOutput(true);
			connection.setDoInput(true);
			out = new OutputStreamWriter(connection.getOutputStream());
			out.write(reqStr);
			out.flush();
			out.close();

			int i = -1;
			in = connection.getInputStream();
			StringBuffer sb = new StringBuffer();
			while ((i = in.read()) != -1) {
				sb.append((char) i);
			}
			rspStr = sb.toString();
			// logger.debug("RspStr->" + rspStr);
			in.close();

		} catch (IOException e) {
			System.out.println("IOException");
			throw e;

		} catch (Exception e) {
			System.out.println("1");
			throw e;
		} finally {
			if (out != null)
				out.close();
			out = null;
			if (in != null)
				in.close();
			in = null;
		}
		return rspStr;
	}

}
