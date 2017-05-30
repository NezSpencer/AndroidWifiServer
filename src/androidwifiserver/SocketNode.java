/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package androidwifiserver;
import java.awt.AWTException;
import java.net.*;
import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nezspencer
 */
public class SocketNode implements Runnable {
    
    private ServerSocket serverSocket;
    private Thread serverThread;
    private Socket androidClientSocket;
    private PrintWriter serverOutput;
    private BufferedReader serverInput;
    private static final int SERVER_PORT=3334;
    
    private static MouseController controller;
    
    public SocketNode(){
    
        try {
            controller = new MouseController();
        } catch (AWTException ex) {
            Logger.getLogger(SocketNode.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void run() {
        try {
            System.out.println("RUnning");
            InetAddress adress= InetAddress.getByName("127.0.0.1");
            serverSocket=new ServerSocket(SERVER_PORT);
//            serverSocket.bind(new InetSocketAddress("192.168.0.2",3000));
            AndroidWifiServer.textField.setText("Server running at "+serverSocket.getInetAddress());
            System.out.println("RUnning on "+serverSocket.getInetAddress().getHostAddress());
            androidClientSocket=serverSocket.accept();
            System.out.println("RUnning on 1"+serverSocket.isBound());
            serverOutput=new PrintWriter(androidClientSocket.getOutputStream(),true);
            serverInput=new BufferedReader(new InputStreamReader(androidClientSocket.getInputStream()));
            
            serverOutput.println("Connection established! with "+System.getProperty("user.name"));
            serverOutput.flush();
            AndroidWifiServer.textField.setText("Server running at "+serverSocket.getInetAddress());
            
//            serverInput = new BufferedReader(new InputStreamReader(androidClientSocket.getInputStream()));
            
            System.out.println(serverInput.ready());
            
            String receivedMsg=null;
            while(true)
            {
                System.out.println("here");
                receivedMsg = serverInput.readLine();
                if(receivedMsg == null)
                {
                    System.out.println("received message is null");
                    break;
                }
                
                System.out.println(receivedMsg==null);
                    
                System.out.println("received "+receivedMsg);
                controller.passMouseOperation(receivedMsg);
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(SocketNode.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
        
            try {
                androidClientSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(SocketNode.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void startServer(){
    
        serverThread=new Thread(this);
        serverThread.start();
       
    }
    
    public void stopServer(){
    
        try {
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(SocketNode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
