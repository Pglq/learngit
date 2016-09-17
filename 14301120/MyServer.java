package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {  
	   
	   public static void main(String args[]) throws IOException {    
	     ServerSocket server = new ServerSocket(3333);  
	      while (true) {  
	         Socket socket = server.accept();  
	         new Thread(new threads(socket)).start();  
	      }  
	   }  
	     
	   static class threads implements Runnable {  
	   
	      private Socket socket;  
	        
	      public threads(Socket socket) {  
	         this.socket = socket;  
	      }  
	        
	      public void run() {  
	         try {  
	            handleSocket();  
	         } catch (Exception e) {  
	            e.printStackTrace();  
	         }  
	      }  
	        
	      private void handleSocket() throws Exception {  
	         BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));  
	         StringBuilder sb = new StringBuilder();  
	         String temp;  
	         int index;  
	         while ((temp=br.readLine()) != null) {    
	            if ((index = temp.indexOf("eof")) != -1) {  
	             sb.append(temp.substring(0, index));  
	                break;  
	            }  
	            sb.append(temp);  
	         }  
	         System.out.println(change(sb.toString()));  
	         Writer writer = new OutputStreamWriter(socket.getOutputStream());  
	         writer.write(change(sb.toString()));  
	         writer.write("eof\n");  
	         writer.flush();  
	         writer.close();  
	         br.close();  
	         socket.close();  
	      }
	      public static String change(String s) {
	    	  int length = s.length();
	    	  String str = "";
	    	  for (int i = 0; i < length; i++)
	    		  str = s.charAt(i) + str;
	    	  return str;
	    	 }
	   }  
	}  
