package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.Scanner;

public class MyClient {  
	  
	   public static void main(String args[]) throws Exception {  
	     Socket client = new Socket("127.0.0.1", 8899);  
	     Scanner scan = new Scanner(System.in);
	     String read = scan.nextLine();  
	     Writer writer = new OutputStreamWriter(client.getOutputStream());  
	      writer.write(read);  
	      writer.write("eof\n");  
	      writer.flush();  
	      BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));  
	      StringBuffer sb = new StringBuffer();  
	      String temp;  
	      int index;  
	      while ((temp=br.readLine()) != null) {  
	         if ((index = temp.indexOf("eof")) != -1) {  
	            sb.append(temp.substring(0, index));  
	            break;  
	         }  
	         sb.append(temp);  
	      }  
	      System.out.println(sb);  
	      writer.close();  
	      br.close();  
	      client.close();  
	   }  
	}  
